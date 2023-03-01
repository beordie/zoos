<template>
  <div class="hello">
    <section class="section section-overlap bg-decorate">
      <div style="widows: 1000px" class="section-overlap-image">
        <el-carousel style="height: 680px">
          <el-carousel-item style="height: 680px" class="section-overlap-image" v-for="(image, index) in main_image" :key="index">
            <img style="height: 680px" :src="image"/>
          </el-carousel-item>
        </el-carousel>
      </div>
      <div class="section-overlap-content">
        <div class="container">
          <div class="row">
            <div class="col-md-6 col-lg-5 col-xl-4">
              <div class="wow-outer">
                <h6 class="font-weight-sbold text-primary wow slideInDown">I'm Jonathan Davis</h6>
              </div>
            </div>
            <div class="col-md-8 col-lg-7 col-xl-6">
              <h1 class="wow-outer"><span class="font-weight-bold wow-outer"><span class="wow slideInUp">Photographer</span></span><span class="font-weight-exlight wow-outer"><span class="wow slideInUp" data-wow-delay=".1s">& Retoucher</span></span></h1>
            </div>
            <div class="col-md-6 col-lg-5 col-xl-4 col-offset-1">
              <div class="wow-outer">
                <h4 class="font-weight-light wow slideInUp" data-wow-delay=".2s">Based in San Diego, I specialize in various kinds of photography</h4>
              </div>
              <div class="wow-outer button-outer"><a class="button button-lg button-primary button-winona wow slideInUp" href="#" data-wow-delay=".3s">View Portfolio</a></div>
            </div>
          </div>
        </div>
      </div>
    </section>
    <section class="section section-lg bg-default">
      <div class="container">
        <div class="row row-50">
          <div class="col-12 text-center">
            <h3 class="section-title wow-outer"><span class="wow slideInUp">部分动物</span></h3>
          </div>
          <div class="col-12 isotope-wrap">
            <div class="isotope offset-top-2" data-isotope-layout="masonry" data-lightgallery="group" data-lg-thumbnail="false">
              <div class="row row-30">
                <div class="col-12 col-sm-6 col-lg-4 isotope-item wow-outer" :class="zoo.horizontal ? 'thumbnail-corporate-lg' : ''" v-for="(zoo, index) in part_zoos" :key="index">
                  <!-- Thumbnail Corporate-->
                  <article class="thumbnail-corporate wow slideInDown">
                    <img class="thumbnail-corporate-image" :src="zoo.image" :width="zoo.width" :height="zoo.height"/>
                    <div class="thumbnail-corporate-caption">
                      <p class="thumbnail-corporate-title"><a href="#">{{ zoo.name }}</a></p>
                      <p>{{ zoo.info }}</p><a class="thumbnail-corporate-link" href="images/gallery-original-1.jpg" data-lightgallery="item"><span class="icon mdi mdi-magnify"></span><span class="icon mdi mdi-magnify"></span></a>
                    </div>
                    <div class="thumbnail-corporate-dummy"></div>
                  </article>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>

    <section class="section section-lg bg-gray-100 text-center">
      <div class="container">
        <h3 class="section-title wow-outer"><span class="wow slideInDown">推荐动物</span></h3>
        <div class="row row-50">
          <div class="col-sm-6 col-lg-4 wow-outer" v-for="(zoo, index) in recommend_zoos" :key="index">
            <!-- Post Classic-->
            <article class="post-classic wow slideInLeft"><a class="post-classic-media" href="#"><img :src="zoo.image" style="width: 370px; height: 264px;"/></a>
              <ul class="post-classic-meta">
                <li><a class="button-winona" href="#"> {{ zoo.name }} </a></li>
                <li>
                  <el-tag>{{ zoo.classify }}</el-tag>
                </li>
              </ul>
              <h4 class="post-classic-title"><a href="#"> {{ zoo.info }} </a></h4>
            </article>
          </div>
        </div>
      </div>
    </section>

    <section class="section section-lg">
      <div class="charts">
        <div class="row row-50 justify-content-lg-between offset-top-1">
          <div class="col-12">
            <h3 class="section-title text-center wow-outer"><span class="wow slideInDown">分类占比</span></h3>
          </div>
          <div class="col-lg-9 col-xl-6">
            <!-- Bootstrap collapse-->
            <Chart/>
          </div>
          <div class="col-lg-5">
            <div class="progress-linear-outer wow-outer">
              <article class="progress-linear wow slideInDown" data-wow-delay=".2s">
                <div class="progress-header">
                  <Gang />
                </div>
                <div class="progress-bar-linear-wrap">
                  <div class="progress-bar-linear progress-green"></div>
                </div>
              </article>
            </div>
            <div class="progress-linear-outer wow-outer">
              <article class="progress-linear wow slideInDown" data-wow-delay=".2s">
                <div class="progress-header">
                  <Mu />
                </div>
              </article>
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import Chart from './plugin/echart.vue'
import Gang from './plugin/gang.vue'
import Mu from './plugin/mu.vue'
export default {
  name: 'HelloWorld',
  components: {
    Chart, Gang, Mu
  },
  props: {
    msg: String
  },
  data() {
    return {
      main_image: [require('../assets/images/screens-1-1046x720.jpg'), require('../assets/images/screens-1-1046x720.jpg')],
      part_zoos: [
        {
          name: '熊猫',
          image: require('../assets/images/sidebar-blog-1-370x264.jpg'),
          info: '巴拉巴拉巴拉',
          horizontal: true
        },
        {
          name: '狗熊',
          image: require('../assets/images/team-1-336x336.jpg'),
          info: '巴拉巴拉巴拉',
          horizontal: false
        }
      ],
      recommend_zoos: [
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
    }
  },
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style>
.charts {
  margin: 0px 100px;
}
.el-card__body, .el-main {
  padding: 0px;
}
</style>
