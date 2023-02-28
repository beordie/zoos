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
                <div class="col-12 col-sm-6 col-lg-4 isotope-item wow-outer">
                  <!-- Thumbnail Corporate-->
                  <article class="thumbnail-corporate wow slideInDown"><img class="thumbnail-corporate-image" src="images/gallery-masonry-1-370x256.jpg" alt="" width="370" height="256"/>
                    <div class="thumbnail-corporate-caption">
                      <p class="thumbnail-corporate-title"><a href="#">Mary Williams</a></p>
                      <p>I offer high-quality photography &amp; retouch services to individual and corporate clients all over the US.</p><a class="thumbnail-corporate-link" href="images/gallery-original-1.jpg" data-lightgallery="item"><span class="icon mdi mdi-magnify"></span><span class="icon mdi mdi-magnify"></span></a>
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
      main_image: [require('../assets/images/screens-1-1046x720.jpg'), require('../assets/images/screens-1-1046x720.jpg')],
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style>
.el-card__body, .el-main {
  padding: 0px;
}
</style>
