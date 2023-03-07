<template>
    <div>
        <el-breadcrumb separator="/">
            <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>物种管理</el-breadcrumb-item>
        </el-breadcrumb>
        <el-divider></el-divider>
        <el-card class="box-card">
            <el-form :model="queryParams" ref="queryParams" :inline="true">
                <el-form-item prop="classSpecies">
                    <el-select @change="getSubclass(queryParams.classSpecies)" v-model="queryParams.classSpecies" filterable remote clearable reserve-keyword placeholder="选择纲种名">
                        <el-option v-for="(name, index) in classSpecies" :key="index" :label="name" :value="name"></el-option>
                    </el-select>
                </el-form-item>

                <el-form-item prop="subclass">
                    <el-select @change="getOrders(queryParams.subclass)" v-model="queryParams.subclass" filterable remote clearable reserve-keyword placeholder="选择亚纲名">
                        <el-option v-for="(name, index) in subclass" :key="index" :label="name" :value="name"></el-option>
                    </el-select>
                </el-form-item>

                <el-form-item prop="orders">
                    <el-select @change="getFamily(queryParams.orders)" v-model="queryParams.orders" filterable remote clearable reserve-keyword placeholder="选择目名">
                        <el-option v-for="(name, index) in orders" :key="index" :label="name" :value="name"></el-option>
                    </el-select>
                </el-form-item>

                <el-form-item prop="family">
                    <el-select @change="getGenus(queryParams.family)" v-model="queryParams.family" filterable remote clearable reserve-keyword placeholder="选择科名">
                        <el-option v-for="(name, index) in family" :key="index" :label="name" :value="name"></el-option>
                    </el-select>
                </el-form-item>

                <el-form-item prop="genus">
                    <el-select v-model="queryParams.genus" filterable remote clearable reserve-keyword placeholder="选择属名">
                        <el-option v-for="(name, index) in genus" :key="index" :label="name" :value="name"></el-option>
                    </el-select>
                </el-form-item>

                <el-form-item prop="chineseName">
                    <el-select v-model="queryParams.chineseName" filterable remote clearable reserve-keyword placeholder="输入动物名"></el-select>
                </el-form-item>

                <el-form-item>
                    <el-button type="primary" icon="el-icon-search" @click="query">查询</el-button>
                    <el-button type="primary" icon="el-icon-refresh">清空</el-button>
                    <el-button type="primary" icon="el-icon-plus" @click="editClick()">新增</el-button>
                </el-form-item>
            </el-form>

            <el-table :data="zoo" border style="width: 100%">
                <el-table-column type="expand">
                    <template slot-scope="scope">
                        <el-form label-position="left" inline class="demo-table-expand">
                            <el-form-item label="生活习性">
                                <span>{{ scope.row.life }}</span>
                            </el-form-item>
                            <el-form-item label="形态特征">
                                <span>{{ scope.row.morphologicalCharacteristics }}</span>
                            </el-form-item>
                            <el-form-item label="物种学史">
                                <span>{{ scope.row.historySpeciology }}</span>
                            </el-form-item>
                            <el-form-item label="详细介绍">
                                <div v-html="scope.row.details"></div>
                            </el-form-item>
                            <el-form-item label="录入时间">
                                <span>{{ formatDateMethod(scope.row.ctime) }}</span>
                            </el-form-item>
                        </el-form>
                    </template>
                </el-table-column>
                <el-table-column prop="id" v-if="false"></el-table-column>
                <el-table-column prop="chineseName" label="中文名" width="150"></el-table-column>
                <el-table-column prop="latinName" label="拉丁文名" width="150"></el-table-column>
                <el-table-column label="保护等级" width="150">
                    <template slot-scope="scope">
                        <el-tag :type="scope.row.protectionClass === '一级保护动物' ? 'primary' : 
                        scope.row.protectionClass === '二级保护动物' ? 'warning' : 'success'" disable-transitions>{{ scope.row.protectionClass }}</el-tag>
                    </template>
                </el-table-column>
                <el-table-column label="种属" width="300">
                    <template slot-scope="scope">
                        <el-tag type="primary" style="margin: 2px;">{{ scope.row.classSpecies }}</el-tag>
                        <el-tag type="primary" style="margin: 2px;">{{ scope.row.subclass }}</el-tag>
                        <el-tag type="primary" style="margin: 2px;">{{ scope.row.orders }}</el-tag>
                        <el-tag type="primary" style="margin: 2px;">{{ scope.row.family }}</el-tag>
                        <el-tag type="primary" style="margin: 2px;">{{ scope.row.genus }}</el-tag>
                    </template>
                </el-table-column>
                <el-table-column  label="图片" align="center">
                    <template slot-scope="scope">
                        <div class="demo-image__preview">
                            <el-image style="width: 100px; height: 100px" :src="host + scope.row.upperGenusClassification" :preview-src-list="[host + scope.row.upperGenusClassification]">
                            </el-image>
                        </div>
                    </template>
                </el-table-column>
                <el-table-column label="是否推荐" width="150" align="center">
                    <template slot-scope="scope">
                        <el-tooltip :content="scope.row.recommend === '1' ? '开启推荐' : '关闭推荐'" placement="top">
                            <el-switch v-model="scope.row.recommend" @change="selectRecommend(scope.row.recommend, scope.row.id)" active-color="#13ce66" inactive-color="#ff4949"
                            :active-value="1" :inactive-value="0"></el-switch>
                        </el-tooltip>
                    </template>
                </el-table-column>
                
                <el-table-column label="操作">
                    <template slot-scope="scope">
                        <el-button size="small" type="primary" @click="editClick(scope.row)">编辑</el-button>
                        <el-button size="small" type="danger" @click="editImage(scope.row)">相关图片</el-button>
                        <el-button size="small" type="danger" @click="deleteClick(scope.row.id)">删除</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <div class="pagination" style="margin-top:20px;text-align:center;">
                <el-pagination background @size-change="handleSizeChange" @current-change="handleCurrentChange" layout="total,prev, sizes, pager, next,jumper"
                :current-page.sync="queryParams.offset" :page-sizes="[5, 10]" :page-size="5" :total="total">
            </el-pagination>
        </div>
        </el-card>

        <el-dialog class="classify" title="修改增加" :visible.sync="editVisible" :width="width" :before-close="handleClose">
            <el-card style="text-align: left;">
                <el-row :gutter="20" style="margin: 0px;">
                    <el-col :span="5" style="padding-left: 0px;">
                        <div class="el-input el-input--medium el-input-group el-input-group--prepend vertical-interval">
                            <div class="el-input-group__prepend">纲种</div>
                            <el-select @change="getSubclass(editParam.classSpecies)" class="" v-model="editParam.classSpecies" filterable remote clearable reserve-keyword placeholder="选择纲种名">
                                <el-option v-for="(name, index) in classSpecies" :key="index" :label="name" :value="name"></el-option>
                            </el-select>
                        </div>
                    </el-col>
                    <el-col :span="5" style="padding-left: 0px;">
                        <div class="el-input el-input--medium el-input-group el-input-group--prepend vertical-interval">
                            <div class="el-input-group__prepend">亚纲</div>
                            <el-select @change="getOrders(editParam.subclass)" v-model="editParam.subclass" filterable remote clearable reserve-keyword placeholder="选择亚纲名">
                                <el-option v-for="(name, index) in subclass" :key="index" :label="name" :value="name"></el-option>
                            </el-select>
                        </div>
                    </el-col>
                    <el-col :span="5" style="padding-left: 0px;">
                        <div class="el-input el-input--medium el-input-group el-input-group--prepend vertical-interval">
                            <div class="el-input-group__prepend">目名</div>
                            <el-select @change="getFamily(editParam.orders)" v-model="editParam.orders" filterable remote clearable reserve-keyword placeholder="选择目名">
                                <el-option v-for="(name, index) in orders" :key="index" :label="name" :value="name"></el-option>
                            </el-select>
                        </div>
                    </el-col>
                    <el-col :span="5" style="padding-left: 0px;">
                        <div class="el-input el-input--medium el-input-group el-input-group--prepend vertical-interval">
                            <div class="el-input-group__prepend">科名</div>
                            <el-select @change="getGenus(editParam.family)" v-model="editParam.family" filterable remote clearable reserve-keyword placeholder="选择科名">
                                <el-option v-for="(name, index) in family" :key="index" :label="name" :value="name"></el-option>
                            </el-select>
                        </div>
                    </el-col>
                    <el-col :span="4" style="padding-left: 0px;">
                        <div class="el-input el-input--medium el-input-group el-input-group--prepend vertical-interval">
                            <div class="el-input-group__prepend">属名</div>
                            <el-select v-model="editParam.genus" filterable remote clearable reserve-keyword placeholder="选择科名">
                                <el-option v-for="(name, index) in genus" :key="index" :label="name" :value="name"></el-option>
                            </el-select>
                        </div>
                    </el-col>

                    <el-col :span="8" style="padding-left: 0px;">
                        <el-input class="vertical-interval" type="text" placeholder="请输入中文名" v-model="editParam.chineseName" maxlength="10" show-word-limit>
                        </el-input>
                    </el-col>
                    <el-col :span="8" style="padding-left: 0px;">
                        <el-input class="vertical-interval" type="text" placeholder="请输入拉丁文名" v-model="editParam.latinName" maxlength="10" show-word-limit>
                        </el-input>
                    </el-col>
                    <el-col :span="8" style="padding-left: 0px;">
                        <el-input class="vertical-interval" type="text" placeholder="请输入保护等级" v-model="editParam.protectionClass" maxlength="10" show-word-limit>
                        </el-input>
                    </el-col>

                    <el-col :span="20" style="padding-left: 0px;">
                        <el-row :gutter="20" style="margin: 0px;">
                            <el-col :span="24" style="padding-left: 0px;">
                                <el-input class="vertical-interval" type="text" placeholder="请输入分布范围" v-model="editParam.distributionRange" maxlength="30" show-word-limit>
                                </el-input>
                            </el-col>

                            <el-col :span="24" style="padding-left: 0px;">
                                <el-input class="vertical-interval" type="text" placeholder="请输入形态特征" v-model="editParam.morphologicalCharacteristics" maxlength="50" show-word-limit>
                                </el-input>
                            </el-col>

                            <el-col :span="24" style="padding-left: 0px;">
                                <el-input class="vertical-interval" type="text" placeholder="请输入物种学史" v-model="editParam.historySpeciology" maxlength="50" show-word-limit>
                                </el-input>
                            </el-col>

                            <el-col :span="24" style="padding-left: 0px;">
                                <el-input class="vertical-interval" type="text" placeholder="请输入生活习性" v-model="editParam.life" maxlength="50" show-word-limit>
                                </el-input>
                            </el-col>
                        </el-row>
                    </el-col>


                    <el-col :span="4" style="padding-left: 0px;">
                        <el-upload class="avatar-uploader" action="" :show-file-list="false"
                            :on-success="handleAvatarSuccess" :http-request="uploadSectionFile">
                            <img v-if="editParam.upperGenusClassification" :src="host + editParam.upperGenusClassification" class="avatar">
                            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                        </el-upload>
                    </el-col>

                    <el-col :span="24" style="padding-left: 0px;">
                        <div style="height: 280px;">
                            <quill-editor class="editor vertical-interval" v-model="editParam.details" ref="customQuillEditor" :options="editorOption" >
                            </quill-editor>
                        </div>
                    </el-col>
                </el-row>
                <el-button type="primary" v-if="editParam.id == null" @click="add">增加</el-button>
                <el-button type="primary" v-if="editParam.id != null" @click="update">更新</el-button>
            </el-card>
        </el-dialog>

        <el-dialog class="classify" title="相关小图" :visible.sync="imageVisible" :width="imageWidth" :before-close="imageClose">
            <el-card style="text-align: center;">
                <el-row :gutter="20" style="margin: 0px;">
                    <el-col :span="8" style="padding-left: 0px;">
                        <div style="margin-bottom: 5px;">第一张图</div>
                        <el-upload class="avatar-uploader" action="" :show-file-list="false"
                            :on-success="handleFirstSuccess" :http-request="uploadSectionFile">
                            <img v-if="smallpicture.first" :src="host + smallpicture.first" class="avatar">
                            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                        </el-upload>
                    </el-col>
                    <el-col :span="8" style="padding-left: 0px;">
                        <div style="margin-bottom: 5px;">第二张图</div>
                        <el-upload class="avatar-uploader" action="" :show-file-list="false"
                            :on-success="handleSecondSuccess" :http-request="uploadSectionFile">
                            <img v-if="smallpicture.second" :src="host + smallpicture.second" class="avatar">
                            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                        </el-upload>
                    </el-col>
                    <el-col :span="8" style="padding-left: 0px;">
                        <div style="margin-bottom: 5px;">第三张图</div>
                        <el-upload class="avatar-uploader" action="" :show-file-list="false"
                            :on-success="handleThirdSuccess" :http-request="uploadSectionFile">
                            <img v-if="smallpicture.third" :src="host + smallpicture.third" class="avatar">
                            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                        </el-upload>
                    </el-col>
                </el-row>
                <el-button type="primary" @click="updatePicture">更新子图</el-button>
            </el-card>
        </el-dialog>
    </div>
</template>

<script>
const toolbarOptions = [
  ['bold', 'italic', 'underline', 'strike'],        // toggled buttons
  ['blockquote', 'code-block'],

  [{'header': 1}, {'header': 2}],               // custom button values
  [{'list': 'ordered'}, {'list': 'bullet'}],
  [{'script': 'sub'}, {'script': 'super'}],      // superscript/subscript
  [{'indent': '-1'}, {'indent': '+1'}],          // outdent/indent
  [{'direction': 'rtl'}],                         // text direction

  [{'size': ['small', false, 'large', 'huge']}],  // custom dropdown
  [{'header': [1, 2, 3, 4, 5, 6, false]}],

  [{'color': []}, {'background': []}],          // dropdown with defaults from theme
  [{'font': []}],
  [{'align': []}],
  ['link', 'image', 'video'],
  ['clean']                                         // remove formatting button
];
const clearParam = {
    id: null,
    chineseName: null,
    latinName: null,
    upperGenusClassification: null,
    ctime: null,
    smallPicture: [],
    protectionClass: null,
    recommend: 0,
    distributionRange: null,
    life: null,
    morphologicalCharacteristics: null,
    historySpeciology: null,
    details: null,
    classSpecies: null,
    subclass: null,
    orders: null,
    family: null,
    genus: null
}
const clearSmall = {
    first: null,
    second: null,
    third: null,
    animalName: null,
    animalId: null
}
export default {
    name: 'HelloWorld',
    props: {
        msg: String
    },
    data() {
        return {
            zoo: [
                {
                    id: 1,
                    chineseName: '小狗撒料图',
                    latinName: '一只小狗在撒尿',
                    upperGenusClassification: '',
                    ctime: 1677899495000,
                    smallPicture: [],
                    protectionClass: '一级保护动物',
                    recommend: 0,
                    distributionRange: '浙江省',
                    life: '喜欢乱跑',
                    morphologicalCharacteristics: '长得像狗',
                    historySpeciology: '无',
                    details: '无',
                    classSpecies: '哺乳纲',
                    subclass: '动物纲',
                    orders: '狗目',
                    family: '哈巴狗科',
                    genus: '小哈巴狗属'
                }
            ],
            smallpicture: {
                first: null,
                second: null,
                third: null,
                animalName: null,
                animalId: null
            },
            classSpecies: [
                '哺乳纲'
            ],
            subclass: [
                '动物纲'
            ],
            orders: [
                '狗目'
            ],
            family: [
                '哈巴狗科'
            ],
            genus: [
                '小哈巴狗属'
            ],
            queryParams: {
                classSpecies: null,
                subclass: null,
                orders: null,
                family: null,
                genus: null,
                chineseName: null,
                offset: 1,
                limit: 5
            },
            editParam: {
                id: null,
                chineseName: null,
                latinName: null,
                upperGenusClassification: null,
                ctime: null,
                smallPicture: null,
                protectionClass: null,
                recommend: 0,
                distributionRange: null,
                life: null,
                morphologicalCharacteristics: null,
                historySpeciology: null,
                details: null,
                classSpecies: null,
                subclass: null,
                orders: null,
                family: null,
                genus: null
            },
            total: 10,
            host: this.$host,
            formatDateMethod: this.dateFormat,
            editVisible: false,
            imageVisible: false,
            imageWidth: '30%',
            width: '80%',
            editorOption:{
                placeholder:'输入详细介绍',
                modules: {
                    toolbar: {
                        container: toolbarOptions,  // 工具栏
                        handlers: {
                        }
                    }
                }
            }
        }
    },
    methods: {
        goBack() {

        },
        editClick(row) {
            if (row !== undefined) {
                this.editParam = row
            }
            this.editVisible = true
        },
        handleCurrentChange(val) {
            this.queryParams.offset = val;
            this.selectList()
        },
        handleSizeChange(val) {
            this.queryParams.limit = val
            this.selectList()
        },
        selectRecommend(recommend, id) {
            this.$http.post(this.$host + '/animal/update/recommend?animalId='+id+'&recommend='+recommend).then(res => {
                if (res.data.code == 200) {
                    this.$notify({
                        title: '成功',
                        message: '推荐成功',
                        type: 'success'
                    });
                } else {
                    console.log(res)
                }
            }).catch(({err}) => {
                window.alert(err)
            })
        },
        dateFormat(date) { 
            if (date == undefined) { 
                return ""; 
            } 
            return this.$moment(date).format("YYYY年MM月DD日 HH时mm分ss秒"); 
        },
        deleteClick (id) {
            this.$http.post(this.$host + '/animal/delete?animalId=' + id).then(res => {
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
        },
        handleClose (done) {
            this.editParam = clearParam
            done()
        },
        imageClose (done) {
            done()
        },
        getClassSpecies() {
            this.$http.get(this.$host + '/animal/select/classSpecies').then(res => {
            if (res.data.code == 200) {
                this.classSpecies = res.data.data
            } else {
                console.log(res)
            }
            }).catch(({err}) => {
                window.alert(err)
            })
        },
        getSubclass(classSpecies) {
            this.$http.get(this.$host + '/animal/select/subclass?classSpecies=' + classSpecies).then(res => {
                if (res.data.code == 200) {
                this.subclass = res.data.data
            } else {
                console.log(res)
            }
            }).catch(({err}) => {
                window.alert(err)
            })
        },
        getOrders(subclass) {
            this.$http.get(this.$host + '/animal/select/order?subclass=' + subclass).then(res => {
                if (res.data.code == 200) {
                this.orders = res.data.data
            } else {
                console.log(res)
            }
            }).catch(({err}) => {
                window.alert(err)
            })
        },
        getFamily(orders) {
            this.$http.get(this.$host + '/animal/select/family?order=' + orders).then(res => {
                if (res.data.code == 200) {
                this.family = res.data.data
            } else {
                console.log(res)
            }
            }).catch(({err}) => {
                window.alert(err)
            })
        },
        getGenus(family) {
            this.$http.get(this.$host + '/animal/select/genus?family=' + family).then(res => {
                if (res.data.code == 200) {
                this.genus = res.data.data
            } else {
                console.log(res)
            }
            }).catch(({err}) => {
                window.alert(err)
            })
        },
        selectList () {
            this.getTotal()
            this.$http.post(this.$host + 'animal/select', this.queryParams
            ).then(res => {
                if (res.data.code == 200) {
                    this.$notify({
                        title: '成功',
                        message: '查询成功',
                        type: 'success'
                    });
                    this.zoo = res.data.data
                } else {
                    console.log(res)
                }
            }).catch(({err}) => {
                window.alert(err)
            })
        },
        getTotal () {
            this.$http.post(this.$host + '/animal/count', this.queryParams).then(res => {
                console.log(res)
                if (res.data.code == 200) {
                    this.total = res.data.data
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
        add () {
            this.$http.post(this.$host + '/animal/create', this.editParam).then(res => {
                if (res.data.code == 200) {
                    this.$notify({
                        title: '成功',
                        message: '新增成功',
                        type: 'success'
                    });
                    this.selectList()
                    this.editVisible = false;
                    this.editParam = clearParam
                } else {
                    console.log(res)
                }
            }).catch(({err}) => {
                window.alert(err)
            })
        },
        update () {
            this.$http.post(this.$host + '/animal/update', this.editParam).then(res => {
                if (res.data.code == 200) {
                    this.$notify({
                        title: '成功',
                        message: '更新成功',
                        type: 'success'
                    });
                    this.selectList()
                    this.editVisible = false;
                    this.editParam = clearParam
                } else {
                    console.log(res)
                }
            }).catch(({err}) => {
                window.alert(err)
            })
        },
        handleAvatarSuccess(res) {
            this.editParam.upperGenusClassification = res.data.data;
        },
        handleFirstSuccess(res) {
            this.smallpicture.first = res.data.data;
        },
        handleSecondSuccess(res) {
            this.smallpicture.second = res.data.data;
        },
        handleThirdSuccess(res) {
            this.smallpicture.third = res.data.data;
        },
        uploadSectionFile(param){
            var fileObj = param.file;
            var form = new FormData();
            form.append('photo', fileObj);
            form.append('type', 'zoo')
            this.$http.post(this.$host + '/image/upload',form).then(res => {
                param.onSuccess(res)
            }).catch(({err}) => {
                param.onError(err)
            })
        },
        editImage (row) {
            let pictures = row.smallPicture
            if (pictures !== null && pictures.length > 0) {
                this.smallpicture.first = pictures[0]
            } else {
                this.smallpicture.first = null
            }
            if (pictures !== null && pictures.length > 1) {
                this.smallpicture.second = pictures[1]
            } else {
                this.smallpicture.second = null
            }
            if (pictures !== null && pictures.length > 2) {
                this.smallpicture.third = pictures[2]
            } else {
                this.smallpicture.third = null
            }
            this.smallpicture.animalId = row.id
            this.smallpicture.animalName = row.chineseName
            this.imageVisible = true
        },
        updatePicture () {
            this.$http.post(this.$host + '/pictures/update/small', this.smallpicture).then(res => {
                if (res.data.code == 200) {
                    this.$notify({
                        title: '成功',
                        message: '更新成功',
                        type: 'success'
                    });
                    this.selectList()
                    this.imageVisible = false;
                    this.smallpicture = clearSmall
                } else {
                    console.log(res)
                }
            }).catch(({err}) => {
                window.alert(err)
            })
        }
    },
    mounted () {
        this.getClassSpecies()
        this.selectList()
    }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style>
.editor {
    line-height: normal !important;
    height: 200px;
}
.demo-table-expand {
    font-size: 0;
    margin: 0px 20px;
}
.demo-table-expand label {
    width: 90px;
    color: #0256ff;
}
.demo-table-expand .el-form-item {
    margin-right: 0;
    margin-bottom: 0;
    width: 50%;
}
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
