<template>
    <div>
        <el-breadcrumb separator="/">
            <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item><a href="/">照片管理</a></el-breadcrumb-item>
        </el-breadcrumb>
        <el-divider></el-divider>
        <el-card class="box-card">
          <el-form :model="queryParams" ref="queryParams" :inline="true">
              <el-form-item prop="pictureName">
                  <el-select v-model="queryParams.pictureName" filterable remote clearable reserve-keyword placeholder="搜索图片名">
                      
                  </el-select>
              </el-form-item>

              <el-form-item>
                <el-button type="primary" icon="el-icon-search" @click="query">查询</el-button>
                <el-button type="primary" icon="el-icon-refresh">清空</el-button>
              </el-form-item>
            </el-form>
            <el-table :data="picture" border style="width: 100%">
                <el-table-column prop="id" v-if="false"></el-table-column>
                <el-table-column prop="name" label="图片名" width="200"></el-table-column>
                <el-table-column prop="description" label="描述" width="500"></el-table-column>
                <el-table-column  label="图片预览" align="center">
                    <template slot-scope="scope">
                        <div class="demo-image__preview">
                            <el-image style="width: 100px; height: 100px" :src="host + scope.row.pictureAddress" :preview-src-list="[host + scope.row.pictureAddress]"></el-image>
                        </div>
                    </template>
                </el-table-column>
                <el-table-column label="是否主图" width="150" align="center">
                    <template slot-scope="scope">
                        <el-tag :type="scope.row.mainPicture === '1' ? 'primary' : 'success'" disable-transitions>{{scope.row.mainPicture == 0 ? '非主图' : '主图'}}</el-tag>
                    </template>
                </el-table-column>
                <el-table-column label="是否轮播" width="150" align="center">
                    <template slot-scope="scope">
                        <el-tooltip :content="scope.row.rotated === '1' ? '开启轮播' : '关闭轮播'" placement="top">
                            <el-switch v-model="scope.row.rotated" @change="selectRolated(scope.row)" active-color="#13ce66" inactive-color="#ff4949"
                            :active-value="1" :inactive-value="0"></el-switch>
                        </el-tooltip>
                    </template>
                </el-table-column>
                <el-table-column label="是否绑定动物" width="150" align="center">
                    <template slot-scope="scope">
                        <el-tag :type="scope.row.aid !== null ? 'primary' : 'success'" disable-transitions>{{scope.row.aid == null ? '未绑定' : '已绑定'}}</el-tag>
                    </template>
                </el-table-column>
            </el-table>
            <div class="pagination" style="margin-top:20px;text-align:center;">
                <el-pagination background @size-change="handleSizeChange" @current-change="handleCurrentChange" layout="total,prev, sizes, pager, next,jumper"
                :current-page.sync="pageParams.offset" :page-sizes="[5, 10]" :page-size="5" :total="pageParams.total">
            </el-pagination>
        </div>
        </el-card>
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
            picture: [
                {
                    id: 1,
                    name: '小狗撒料图',
                    description: '一只小狗在撒尿',
                    mainPicture: 1,
                    rotated: 1,
                    aid: 1,
                    pictureAddress: ''
                }
            ],
            queryParams: {
                pictureName: ''
            },
            pageParams: {
                offset: 1,
                limit: 5,
                total: 10
            },
            host: this.$host
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
        selectRolated(picture) {
            console.log(picture)
            this.$http.post(this.$host + 'pictures/update?pictureId=' + picture.id
                + "&rotated=" + picture.rotated
            ).then(res => {
                if (res.data.code == 200) {
                    this.$notify({
                        title: '成功',
                        message: '设置轮播成功',
                        type: 'success'
                    });
                    
                } else {
                    console.log(res)
                }
            }).catch(({err}) => {
                window.alert(err)
            })
        },
        query () {
            this.selectList()
        },
        selectList () {
            this.getTotal()
            this.$http.get(this.$host + 'pictures/list?offset=' + this.pageParams.offset
                + "&limit=" + this.pageParams.limit
            ).then(res => {
                if (res.data.code == 200) {
                    this.$notify({
                        title: '成功',
                        message: '查询成功',
                        type: 'success'
                    });
                    this.picture = res.data.data
                } else {
                    console.log(res)
                }
            }).catch(({err}) => {
                window.alert(err)
            })
        },
        getTotal () {
            this.$http.get(this.$host + '/pictures/count').then(res => {
                if (res.data.code == 200) {
                    this.pageParams.total = res.data.data
                } else {
                    console.log(res)
                }
            }).catch(({err}) => {
                window.alert(err)
            })
        },
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
