<template>
    <div>
        <el-breadcrumb separator="/">
            <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item><a href="/">摄影管理</a></el-breadcrumb-item>
        </el-breadcrumb>
        <el-divider></el-divider>
        <el-card class="box-card">
          <el-form :model="queryParams" ref="queryParams" :inline="true">
              <el-form-item prop="photoName">
                  <el-select v-model="queryParams.photoName" filterable remote clearable reserve-keyword placeholder="搜索摄影名">
                      
                  </el-select>
              </el-form-item>

              <el-form-item>
                <el-button type="primary" icon="el-icon-search">查询</el-button>
                <el-button type="primary" icon="el-icon-refresh">清空</el-button>
                <el-button type="primary" icon="el-icon-plus" @click="editClick()">新增</el-button>
              </el-form-item>
            </el-form>
            <el-table :data="photo" border style="width: 100%">
                <el-table-column prop="id" v-if="false"></el-table-column>
                <el-table-column prop="chineseName" label="中文名" width="150"></el-table-column>
                <el-table-column prop="latinName" label="拉丁文名" width="150"></el-table-column>
                <el-table-column prop="photographer" label="拍摄人" width="150"></el-table-column>
                <el-table-column prop="shootingTime" :formatter="dateFormat" label="拍摄时间" width="200"></el-table-column>
                <el-table-column prop="shootingLocation" label="拍摄地点" width="300"></el-table-column>
                <el-table-column  label="图片" align="center">
                    <template slot-scope="scope">
                        <div class="demo-image__preview">
                            <el-image style="width: 100px; height: 100px" :src="host + scope.row.picture" :preview-src-list="[host + scope.row.picture]">
                            </el-image>
                        </div>
                    </template>
                </el-table-column>
                
                <el-table-column label="操作">
                    <template slot-scope="scope">
                        <el-button size="small" type="primary" @click="editClick(scope.row)">编辑</el-button>
                        <el-button size="small" type="danger" @click="deleteClick(scope.row.id)">删除</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <div class="pagination" style="margin-top:20px;text-align:center;">
          <el-pagination background @size-change="handleSizeChange" @current-change="handleCurrentChange" layout="total,prev, sizes, pager, next,jumper"
              :current-page.sync="pageParams.offset" :page-sizes="[1, 2, 5, 10]" :page-size="5" :total="total">
          </el-pagination>
        </div>
        </el-card>

        <el-dialog class="classify" title="修改增加" :visible.sync="editVisible" :width="width" :before-close="handleClose">
            <el-card style="text-align: left;">
                <el-input class="vertical-interval" type="text" placeholder="请输入中文名" v-model="editParam.chineseName" maxlength="10" show-word-limit>
                    <template slot="append">中文名</template>
                </el-input>
                <el-input class="vertical-interval" type="text" placeholder="请输入拉丁文名" v-model="editParam.latinName" maxlength="10" show-word-limit>
                    <template slot="append">拉丁文</template>
                </el-input>
                <el-input class="vertical-interval" type="text" placeholder="请输入拍摄者名" v-model="editParam.photographer" maxlength="10" show-word-limit>
                    <template slot="append">拍摄者</template>
                </el-input>
                <el-date-picker class="vertical-interval" v-model="editParam.shootingTime" type="datetime" placeholder="选择拍摄时间" align="right" :picker-options="pickerOptions"></el-date-picker>
                <el-input class="vertical-interval" type="text" placeholder="请输入拍摄者名" v-model="editParam.shootingLocation" maxlength="30" show-word-limit>
                    <template slot="append">拍摄地点</template>
                </el-input>
                <el-upload class="avatar-uploader" action="" :show-file-list="false"
                    :on-success="handleAvatarSuccess" :http-request="uploadSectionFile">
                    <img v-if="editParam.picture" :src="host + editParam.picture" class="avatar">
                    <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                </el-upload>
                <el-button type="primary" v-if="editParam.id == null" @click="add">增加</el-button>
                <el-button type="primary" v-if="editParam.id != null" @click="update">更新</el-button>
            </el-card>
        </el-dialog>
    </div>
</template>

<script>

export default {
    name: 'HelloWorld',
    props: {
        msg: String
    },
    data() {
        return {
            photo: [
                {
                    id: 1,
                    chineseName: '小狗撒料图',
                    latinName: '一只小狗在撒尿',
                    photographer: '李四',
                    shootingTime: 1677899495000,
                    shootingLocation: '北京市',
                    picture: '',
                }
            ],
            queryParams: {
                photoName: ''
            },
            pageParams: {
                offset: 1,
                limit: 5
            },
            total: 20,
            host: this.$host,
            pickerOptions: {
                shortcuts: [{
                    text: '今天',
                    onClick(picker) {
                    picker.$emit('pick', new Date());
                    }
                }, {
                    text: '昨天',
                    onClick(picker) {
                    const date = new Date();
                    date.setTime(date.getTime() - 3600 * 1000 * 24);
                    picker.$emit('pick', date);
                    }
                }, {
                    text: '一周前',
                    onClick(picker) {
                    const date = new Date();
                    date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
                    picker.$emit('pick', date);
                    }
                }]
            },
            editParam: {
                id: null,
                chineseName: '',
                latinName: '',
                photographer: '',
                shootingTime: null,
                shootingLocation: '',
                picture: '',
            },
            editVisible: false,
            width: '30%'
        }
    },
    methods: {
        goBack() {

        },
        handleClick(row) {
            console.log(row);
        },
        handleCurrentChange(val) {
            this.pageParams.offset = val;
            this.selectList()
        },
        handleSizeChange(val) {
            this.pageParams.limit = val
            this.selectList()
        },
        dateFormat(row, column) { 
            var date = row[column.property]; 
            if (date == undefined) { 
                return ""; 
            } 
            return this.$moment(date).format("YYYY年MM月DD日 HH:mm:ss"); 
        },
        uploadSectionFile(param){
            var fileObj = param.file;
            var form = new FormData();
            form.append('photo', fileObj);
            form.append('type', 'photo')
            this.$http.post(this.$host + '/image/upload',form).then(res => {
                param.onSuccess(res)
            }).catch(({err}) => {
                param.onError(err)
            })
        },
        handleAvatarSuccess(res) {
            this.editParam.picture = res.data.data;
        },
        editClick(row) {
            if (row !== undefined) {
                this.editParam = row
            }
            this.editVisible = true
        },
        handleClose (done) {
            this.editParam = {
                id: null,
                chineseName: '',
                latinName: '',
                photographer: '',
                shootingTime: null,
                shootingLocation: '',
                picture: ''
            }
            done()
        },
        add () {
            this.$http.post(this.$host + '/photographs/insert', this.editParam).then(res => {
                if (res.data.code == 200) {
                    this.$notify({
                        title: '成功',
                        message: '新增成功',
                        type: 'success'
                    });
                    this.selectList()
                    this.editVisible = false;
                    this.editParam = {
                        id: null,
                        chineseName: '',
                        latinName: '',
                        photographer: '',
                        shootingTime: null,
                        shootingLocation: '',
                        picture: ''
                    }
                } else {
                    console.log(res)
                }
            }).catch(({err}) => {
                window.alert(err)
            })
        },
        update () {
            this.$http.post(this.$host + '/photographs/update', this.editParam).then(res => {
                if (res.data.code == 200) {
                    this.$notify({
                        title: '成功',
                        message: '更新成功',
                        type: 'success'
                    });
                    this.editVisible = false;
                    this.editParam = {
                        id: null,
                        chineseName: '',
                        latinName: '',
                        photographer: '',
                        shootingTime: null,
                        shootingLocation: '',
                        picture: ''
                    }
                } else {
                    console.log(res)
                }
            }).catch(({err}) => {
                window.alert(err)
            })
        },
        selectList () {
            this.getTotal()
            this.$http.get(this.$host + 'photographs/list?offset=' + this.pageParams.offset
                + "&limit=" + this.pageParams.limit
            ).then(res => {
                if (res.data.code == 200) {
                    this.$notify({
                        title: '成功',
                        message: '查询成功',
                        type: 'success'
                    });
                    console.log(res)
                    this.photo = res.data.data
                } else {
                    console.log(res)
                }
            }).catch(({err}) => {
                window.alert(err)
            })
        },
        getTotal () {
            this.$http.get(this.$host + '/photographs/total').then(res => {
                if (res.data.code == 200) {
                    this.total = res.data.data
                } else {
                    console.log(res)
                }
            }).catch(({err}) => {
                window.alert(err)
            })
        },
        deleteClick (id) {
            console.log(id)
            this.$http.post(this.$host + '/photographs/delete?photoId=' + id).then(res => {
                if (res.data.code == 200) {
                    this.$notify({
                        title: '成功',
                        message: '删除',
                        type: 'success'
                    });
                    this.selectList()
                } else {
                    console.log(res)
                }
            }).catch(({err}) => {
                window.alert(err)
            })
        }
    },
    mounted () {
        this.selectList()
    }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
