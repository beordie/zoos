<template>
    <main class="pt-5">
        <div class="resource-body">
            <div>
                <div style="text-align:center;color:gainsboro">
                    <h2><b>动物分类列表</b></h2>
                </div>
                <div style="text-align:center;">
                    <el-select class="select-body" v-model="gang" clearable placeholder="纲">
                        <el-option v-for="item in gangs" :key="item.value" :label="item.label" :value="item.value"></el-option>
                    </el-select>
                    <el-select class="select-body" v-model="gang" clearable placeholder="亚纲">
                        <el-option v-for="item in gangs" :key="item.value" :label="item.label" :value="item.value"></el-option>
                    </el-select>
                    <el-select class="select-body" v-model="gang" clearable placeholder="目">
                        <el-option v-for="item in gangs" :key="item.value" :label="item.label" :value="item.value"></el-option>
                    </el-select>
                    <el-select class="select-body" v-model="gang" clearable placeholder="科">
                        <el-option v-for="item in gangs" :key="item.value" :label="item.label" :value="item.value"></el-option>
                    </el-select>
                    <el-select class="select-body" v-model="gang" clearable placeholder="属">
                        <el-option v-for="item in gangs" :key="item.value" :label="item.label" :value="item.value"></el-option>
                    </el-select>
                    <el-select class="select-body" v-model="gang" clearable placeholder="种">
                        <el-option v-for="item in gangs" :key="item.value" :label="item.label" :value="item.value"></el-option>
                    </el-select>
                </div>
            </div>
            <div style="padding:20px 50px 20px 50px;margin:10px;text-align:center;">
                <el-row :gutter="50">
                    <el-col :span="6" v-for="(zoo, i) in zoos" :key="i">
                        <el-card class="box-card center hots" shadow="always" style="margin:10px">
                            <el-descriptions :column="2" :title="zoo.name" :colon="false">
                                <el-descriptions-item >
                                    <el-tag size="medium">{{zoo.classify}}</el-tag>
                                </el-descriptions-item>
                                <el-descriptions-item >
                                    
                                </el-descriptions-item>
                                <el-descriptions-item >{{ zoo.info }}</el-descriptions-item>
                                <el-descriptions-item :contentStyle="{'margin-left': '40px'}">
                                    <el-image @click="jump" style="width: 100px; height: 100px" :src="zoo.image" :fit="fit"></el-image>
                                </el-descriptions-item>
                            </el-descriptions>
                        </el-card>
                    </el-col>
                </el-row>
            </div>
        </div>

        <el-dialog :visible.sync="detail" width="50%" :before-close="handleClose">
            <section class="section section-sm">
                <div class="container">
                    <div class="row">
                        <div class="row row-30">
                            <div class="col-md-6"><img src="../assets/images/team-1-336x336.jpg" alt="" width="300" height="400"/>
                            </div>
                            <div class="col-md-6">
                                <div class="progress-linear-outer wow-outer">
                                    <article class="progress-linear wow slideInDown" data-wow-delay=".2s">
                                        <div class="progress-header">
                                            <el-descriptions class="margin-top" title="详细信息" :column="2">
                                                <template slot="extra">
                                                    <el-button icon="el-icon-like-before" size="small" circle=""></el-button>
                                                </template>
                                                <el-descriptions-item label="中文名">kooriookami</el-descriptions-item>
                                                <el-descriptions-item label="拉丁文名">18100000000</el-descriptions-item>
                                                <el-descriptions-item label="物种学史">物种学史</el-descriptions-item>
                                                <el-descriptions-item label="形态特征">形态特征</el-descriptions-item>
                                                <el-descriptions-item label="栖息环境">栖息环境</el-descriptions-item>
                                                <el-descriptions-item label="生活习性">生活习性</el-descriptions-item>
                                                <el-descriptions-item label="分布范围">分布范围</el-descriptions-item>
                                                <el-descriptions-item label="分布范围">分布范围</el-descriptions-item>
                                                <el-descriptions-item label="物种分类">
                                                    <el-tag size="small">分类</el-tag>
                                                </el-descriptions-item>
                                            </el-descriptions>
                                        </div>
                                        <div class="progress-bar-linear-wrap">
                                            <div class="progress-bar-linear progress-green"></div>
                                        </div>
                                    </article>
                                </div>
                                <div class="progress-linear-outer wow-outer">
                                    <article class="progress-linear wow slideInDown" data-wow-delay=".2s">
                                        <div class="progress-header">
                                            <el-image 
                                                style="width: 30%"
                                                :src="url" 
                                                :preview-src-list="srcList">
                                            </el-image>
                                            <el-image 
                                                style="width: 30%"
                                                :src="url" 
                                                :preview-src-list="srcList">
                                            </el-image>
                                            <el-image 
                                                style="width: 30%"
                                                :src="url" 
                                                :preview-src-list="srcList">
                                            </el-image>
                                        </div>
                                    </article>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </el-dialog>
    </main>
  </template>

<script>
export default {
  data() {
    return {
      detail: false,
      gang: '',
      gangs: [],
      condition: '',
      zoos: [
      {
          name: '熊猫',
          image: require('../assets/images/team-1-336x336.jpg'),
          info: '巴拉巴拉巴拉',
          classify: '哺乳类'
        },
        {
          name: '狗熊',
          image: require('../assets/images/team-1-336x336.jpg'),
          info: '巴拉巴拉巴拉',
          classify: '哺乳类'
        },
      ],
      url: 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg',
      srcList: [
          'https://fuss10.elemecdn.com/8/27/f01c15bb73e1ef3793e64e6b7bbccjpeg.jpeg',
          'https://fuss10.elemecdn.com/1/8e/aeffeb4de74e2fde4bd74fc7b4486jpeg.jpeg'
        ]
    };
  },
  methods: {
    handleClose(done) {
        done();
    },
    jump() {
        this.detail = true
    }
  },

  created() {

  },
  mounted() {
  }

}
</script>

<style>
.select-body {
    margin: 10px 5px;
    width: 5%
}
</style>