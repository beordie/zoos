<template>
    <main class="">
        <div class="ie-panel"><img src="../assets/images/banner/background-03-1920x310.jpg"></div>
        <div class="page">
            <section class="section section-sm">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-10 col-xl-8">
                          <h6>Left aligned image</h6>
                            <div class="row row-30">
                                <div class="col-md-6"><img src="../assets/images/team-1-336x336.jpg" alt="" width="770" height="456"/>
                                </div>
                                <div class="col-md-6">
                                    <el-descriptions class="margin-top" title="详细信息" :column="2" :size="size">
                                        <template slot="extra">
                                          <el-button icon="el-icon-favorite" size="small" circle=""></el-button>
                                        </template>
                                        <el-descriptions-item label="中文名">kooriookami</el-descriptions-item>
                                        <el-descriptions-item label="拉丁文名">18100000000</el-descriptions-item>
                                        <el-descriptions-item label="拍摄人">苏州市</el-descriptions-item>
                                        <el-descriptions-item label="拍摄时间"></el-descriptions-item>
                                        <el-descriptions-item label="拍摄地点">江苏省苏州市吴中区吴中大道 1188 号</el-descriptions-item>
                                    </el-descriptions>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>

            <section class="section section-sm section-last">
        <div class="container">
          <div class="row">
            <div class="col-lg-10 col-xl-8">
              <h6>Right aligned image</h6>
              <div class="row row-30 flex-md-row-reverse">
                <div class="col-md-6"><img src="../assets/images/team-1-336x336.jpg"/>
                </div>
                <div class="col-md-6">
                    <el-descriptions class="margin-top" title="详细信息" :column="2" :size="size">
                        <template slot="extra">
                          <el-button icon="el-icon-favorite" size="small" circle=""></el-button>
                        </template>
                        <el-descriptions-item label="中文名">kooriookami</el-descriptions-item>
                        <el-descriptions-item label="拉丁文名">18100000000</el-descriptions-item>
                        <el-descriptions-item label="拍摄人">苏州市</el-descriptions-item>
                        <el-descriptions-item label="拍摄时间"></el-descriptions-item>
                        <el-descriptions-item label="拍摄地点">江苏省苏州市吴中区吴中大道 1188 号</el-descriptions-item>
                    </el-descriptions>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>
        </div>
    </main>
  </template>

<script>
export default {
  data() {
    return {
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
      ]
    };
  },
  methods: {
    
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