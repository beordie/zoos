<template>
    <main class="pt-5">
        <div class="resource-body">
            <div>
                <div style="text-align:center;color:gainsboro">
                    <h2><b>动物分类列表</b></h2>
                </div>
                <div style="text-align:center;">
                    <el-select @change="getSubclass(queryParams.classSpecies)" class="select-body" v-model="queryParams.classSpecies" clearable placeholder="纲">
                        <el-option v-for="(name, index) in classSpecies" :key="index" :label="name" :value="name"></el-option>
                    </el-select>
                    <el-select @change="getOrders(queryParams.subclass)" class="select-body" v-model="queryParams.subclass" clearable placeholder="亚纲">
                        <el-option v-for="(name, index) in subclass" :key="index" :label="name" :value="name"></el-option>
                    </el-select>
                    <el-select @change="getFamily(queryParams.orders)" class="select-body" v-model="queryParams.orders" clearable placeholder="目">
                        <el-option v-for="(name, index) in orders" :key="index" :label="name" :value="name"></el-option>
                    </el-select>
                    <el-select @change="getGenus(queryParams.family)" class="select-body" v-model="queryParams.family" clearable placeholder="科">
                        <el-option v-for="(name, index) in family" :key="index" :label="name" :value="name"></el-option>
                    </el-select>
                    <el-select class="select-body" v-model="queryParams.genus" clearable placeholder="属">
                        <el-option v-for="(name, index) in genus" :key="index" :label="name" :value="name"></el-option>
                    </el-select>
                    <el-input style="width: 20%;" placeholder="请输入动物名" v-model="queryParams.chineseName">
                        <el-button @click="query" slot="append" icon="el-icon-search"></el-button>
                    </el-input>
                </div>
            </div>
            <div style="padding:20px 50px 20px 50px;margin:10px;text-align:center;">
                <el-row :gutter="50">
                    <el-col :span="6" v-for="(zoo, index) in zoos" :key="index">
                        <el-card class="box-card center hots" shadow="always" style="margin:10px">
                            <el-descriptions :column="2" :title="zoo.chineseName" :colon="false">
                                <template slot="extra">
                                    <el-tag type="danger" v-if="zoo.protectionClass !== ''">{{ zoo.protectionClass}}</el-tag>
                                </template>
                                <el-descriptions-item >
                                    <el-tag>{{ zoo.classSpecies }} | {{ zoo.subclass }} | {{ zoo.orders }} | {{ zoo.family }} | {{ zoo.genus }}</el-tag>
                                </el-descriptions-item>
                                <el-descriptions-item >
                                    
                                </el-descriptions-item>
                                <el-descriptions-item ><div v-html="zoo.historySpeciology"></div></el-descriptions-item>
                                <el-descriptions-item :contentStyle="{'margin-left': '40px'}">
                                    <el-image @click="jump(index)" style="width: 100px; height: 100px" :src="host + zoo.upperGenusClassification"></el-image>
                                </el-descriptions-item>
                            </el-descriptions>
                        </el-card>
                    </el-col>
                </el-row>
            </div>
        </div>

        <el-dialog :visible.sync="detail" width="50%" :before-close="handleClose">
            <section class="section">
                <div class="container">
                    <section class="section">
                        <div class="container">
                            <el-row :gutter="20" style="margin: 0px; text-align: left;">
                                <el-col :span="14" style="padding-left: 0px;">
                                    <img :src="host + showZoo.upperGenusClassification" alt=""/>
                                </el-col>
                                <el-col :span="10" style="text-align: left; padding-right: 0px;">
                                    <el-row style="margin: 0px;">
                                        <el-col :span="24" style="margin-bottom: 8px;">
                                            <span class="detail">中文名:&ensp;</span>{{ showZoo.chineseName }}
                                        </el-col>
                                        <el-col :span="24" style="margin-bottom: 8px;">
                                            <span class="detail">拉丁文名:&ensp;</span>{{ showZoo.latinName }}
                                        </el-col>
                                        <el-col :span="24" style="margin-bottom: 8px;">
                                            <span class="detail">物种学史:&ensp;</span>{{ showZoo.historySpeciology }}
                                        </el-col>
                                        <el-col :span="24" style="margin-bottom: 8px;">
                                            <span class="detail">形态特征:&ensp;</span>{{ showZoo.morphologicalCharacteristics }}
                                        </el-col>
                                        <el-col :span="24" style="margin-bottom: 8px;">
                                            <span class="detail">生活习性:&ensp;</span>{{ showZoo.life }}
                                        </el-col>
                                        <el-col :span="24" style="margin-bottom: 8px;">
                                            <span class="detail">分布范围:&ensp;</span>{{ showZoo.distributionRange }}
                                        </el-col>
                                        <el-col :span="24" style="margin-bottom: 8px;">
                                            <span class="detail">物种分类:&ensp;</span>
                                            <el-tag type="primary" style="margin: 2px;">{{ showZoo.classSpecies }}</el-tag>
                                            <el-tag type="primary" style="margin: 2px;">{{ showZoo.subclass }}</el-tag>
                                            <el-tag type="primary" style="margin: 2px;">{{ showZoo.orders }}</el-tag>
                                            <el-tag type="primary" style="margin: 2px;">{{ showZoo.family }}</el-tag>
                                            <el-tag type="primary" style="margin: 2px;">{{ showZoo.genus }}</el-tag>
                                        </el-col>
                                    </el-row>
                                    <el-descriptions class="margin-top" :column="2">
                                        <template slot="extra">
                                            <el-button :icon="showZoo.favorite == 0 ? 'el-icon-like-before' : 'el-icon-like-after'" 
                                            size="small" circle @click="favorite(showZoo.id)"></el-button>
                                        </template>
                                    </el-descriptions>
                                </el-col>
                                <span class="detail" style="font-size: medium;">详情:</span>
                                <el-col :span="24" style="padding-left: 0px;">
                                    <div v-html="showZoo.details"></div>
                                </el-col>
                            </el-row>
                            <div class="row row-30">
                                <div class="progress-linear-outer wow-outer">
                                    <article class="progress-linear wow slideInDown" data-wow-delay=".2s">
                                        <div class="progress-header">
                                            <el-image v-for="(picture, index) in showZoo.smallPicture" :key="index"
                                                style="width: 300px;height: 200px;"
                                                :src="host + picture" 
                                                :preview-src-list="[host + picture]">
                                            </el-image>
                                        </div>
                                    </article>
                                </div>
                            </div>
                        </div>
                    </section>
                </div>
            </section>
        </el-dialog>
    </main>
  </template>

<script>
export default {
  data() {
    return {
      queryParams: {
        classSpecies: null,
        subclass: null,
        orders: null,
        family: null,
        genus: null,
        chineseName: null,
        offset: 1,
        limit: 10
      },
      detail: false,
      gang: '',
      gangs: [],
      condition: '',
      zoos: [
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
            genus: '小哈巴狗属',
            favorite: 0
        }
      ],
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
        showZoo: {},
        host: this.$host
    };
  },
  methods: {
    favorite(aid) {
        if (localStorage.getItem("userId") == null) {
          this.$notify({
                    title: '成功',
                    message: '请登录',
                    type: 'danger'
                });
            return
        }
        if (this.showZoo.favorite == 1) {
            this.showZoo.favorite = 0
        } else {
            this.showZoo.favorite = 1
        }
        this.$http.post(this.$host + 'favorites', {
            aid: aid,
            isFavorite: this.showZoo.favorite
        }, {headers: {'userId': localStorage.getItem("userId")}}
        ).then(res => {
            if (res.data.code == 200) {
                this.$notify({
                    title: '成功',
                    message: '',
                    type: 'success'
                });
            } else {
                console.log(res)
            }
        }).catch(({err}) => {
            window.alert(err)
        })
    },
    handleClose(done) {
        this.showZoo = {}
        done();
    },
    jump(index) {
        this.showZoo = this.zoos[index]
        this.detail = true
    },
    query() {
        this.$http.post(this.$host + 'animal/select', this.queryParams,
        {headers: {'userId': localStorage.getItem("userId")}}
        ).then(res => {
            if (res.data.code == 200) {
                this.$notify({
                    title: '成功',
                    message: '查询成功',
                    type: 'success'
                });
                this.zoos = res.data.data
            } else {
                console.log(res)
            }
        }).catch(({err}) => {
            window.alert(err)
        })
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
    getDetail () {
        let id = this.$route.query.id;
        if (id === undefined) {
            return
        }
        this.$http.get(this.$host + '/animal/select/' + id,
        {headers: {'userId': localStorage.getItem("userId")}}).then(res => {
            if (res.data.code == 200) {
                this.showZoo = res.data.data
                this.detail = true
                console.log(this.showZoo)
            } else {
                this.$notify({
                    title: '失败',
                    message: '查询详情失败',
                    type: 'failed'
                });
            }
        }).catch(({err}) => {
            window.alert(err)
        })
    }
  },
  filters: {
        ellipsis(value){
            if (!value) return '';
            if (value.length > 50) {
            return value.slice(0,40) + '...'
            }
            return value
        }
    },

  created() {

  },
  mounted() {
    this.getClassSpecies()
    this.query()
    this.getDetail()
  }

}
</script>

<style>
.select-body {
    margin: 10px 5px;
    width: 5%
}
.detail {
    font-weight: bold;
}
</style>