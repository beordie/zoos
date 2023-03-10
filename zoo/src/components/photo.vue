<template>
    <main class="">
        <div class="ie-panel"><img src="../assets/images/banner/background-03-1920x310.jpg"></div>
        <div class="page">
            <section class="section section-sm" v-for="(photo, index) in photos" :key="index" :class="index % 2 == 0 ? 'section-last': ''">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-10 col-xl-8">
                          <h6>{{ index % 2 == 0 ? 'Right aligned image': 'Left aligned image'}}</h6>
                            <div class="row row-30" :class="index % 2 == 0 ? 'flex-md-row-reverse': ''">
                                <div class="col-md-6"><img :src="host + photo.picture" alt="" width="770" height="456"/>
                                </div>
                                <div class="col-md-6">
                                    <el-descriptions class="margin-top" title="详细信息" :column="2">
                                        <template slot="extra">
                                          <el-button :icon="photo.liked === 0 ? 'el-icon-favorite' : 'el-icon-favorite-after'" 
                                          size="small" circle @click="like(photo.id, photo.liked)"></el-button>
                                        </template>
                                        <el-descriptions-item label="中文名">{{ photo.chineseName }}</el-descriptions-item>
                                        <el-descriptions-item label="拉丁文名">{{ photo.latinName }}</el-descriptions-item>
                                        <el-descriptions-item label="拍摄人">{{ photo.photographer }}</el-descriptions-item>
                                        <el-descriptions-item label="拍摄时间">{{ dateFormat(photo.shootingTime) }}</el-descriptions-item>
                                        <el-descriptions-item label="拍摄地点">{{ photo.shootingLocation }}</el-descriptions-item>
                                    </el-descriptions>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <el-button type="primary" v-if="pageParams.isLoad" icon="el-icon-loading" @click="loading">加载更多</el-button>
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
      photos: [
        {
          id: 1,
          chineseName: '小狗撒料图',
          latinName: '一只小狗在撒尿',
          photographer: '李四',
          shootingTime: 1677899495000,
          shootingLocation: '北京市',
          picture: '',
          liked: 0
        }
      ],
      pageParams: {
        offset: 1,
        limit: 10,
        isLoad: true
      },
      host: this.$host
    };
  },
  methods: {
    like(pid, isLike) {
        if (localStorage.getItem("userId") == null) {
          this.$notify({
                    title: '成功',
                    message: '请登录',
                    type: 'danger'
                });
        }
        isLike = isLike == 0 ? 1 : 0
        this.$http.post(this.$host + 'like', {
            pid: pid,
            isLike: isLike
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
    selectList () {
      this.$http.get(this.$host + 'photographs/list?offset=' + this.pageParams.offset
          + "&limit=" + this.pageParams.limit, {headers: {'userId': localStorage.getItem("userId")}}
      ).then(res => {
          if (res.data.code == 200) {
              this.$notify({
                  title: '成功',
                  message: '查询成功',
                  type: 'success'
              });
              console.log(res)
              this.photos = res.data.data
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
        return this.$moment(date).format("YYYY年MM月DD日 HH:mm:ss"); 
    },
    loading () {

    }
  },

  created() {

  },
  mounted() {
    this.selectList()
  }

}
</script>

<style>
.select-body {
    margin: 10px 5px;
    width: 5%
}
</style>