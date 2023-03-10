<template>
    <el-container>
        <el-header class="header-color">
            <el-row type="flex">
                <el-col :span="10">
                    <div>
                        <img class="img-center" style="height: 60px;" :src="logo"/>
                        <img class="img-center" style="height: 60px;" :src="logo1"/>
                        <img class="img-center" style="height: 60px;" :src="logo2"/>
                        <img class="img-center" style="height: 60px;" :src="logo3"/>
                        <img class="img-center" style="height: 60px;" :src="logo4"/>
                        <img class="img-center" style="height: 60px;" :src="logo5"/>
                        <img class="img-center" style="height: 60px;" :src="logo6"/>
                    </div>
                </el-col>
                <el-col :span="13">
                </el-col>
                <el-col :span="1">
                    <div class="header-avatar">
                        <el-dropdown :hide-on-click="false">
                            <span class="el-dropdown-link">
                                <el-avatar style="margin: 10px;" src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
                            </span>
                            <el-dropdown-menu slot="dropdown">
                                <el-dropdown-item >登陆</el-dropdown-item>
                                <el-dropdown-item >注册</el-dropdown-item>
                                <el-dropdown-item disabled>信息</el-dropdown-item>
                                <el-dropdown-item divided @click.native="report">举报</el-dropdown-item>
                                <el-dropdown-item @click.native="loginOut">退出</el-dropdown-item>
                              </el-dropdown-menu>
                        </el-dropdown>
                    </div>
                </el-col>
            </el-row>
        </el-header>
        <el-container>
            <el-aside width="220px">
                <el-menu :default-openeds="['1', '3']" router>
                    <el-submenu index="1">
                        <template slot="title"><i class="el-icon-zoo"></i>物种管理</template>
                        <el-menu-item-group>
                            <el-menu-item index="1-1" route="/zoo">动物管理</el-menu-item>
                        </el-menu-item-group>
                    </el-submenu>
                    <el-submenu index="2">
                        <template slot="title"><i class="el-icon-image"></i>图片管理</template>
                        <el-menu-item-group>
                            <el-menu-item index="2-1" route="/picture">图片列表</el-menu-item>
                        </el-menu-item-group>
                    </el-submenu>
                    <el-submenu index="3">
                        <template slot="title"><i class="el-icon-classify"></i>分类管理</template>
                        <el-menu-item-group>
                            <el-menu-item index="3-1" route="/kinds">纲种</el-menu-item>
                            <el-menu-item index="3-2" route="/subclass">亚纲</el-menu-item>
                            <el-menu-item index="3-3" route="/orders">目</el-menu-item>
                            <el-menu-item index="3-4" route="/family">科</el-menu-item>
                            <el-menu-item index="3-5" route="/genus">属</el-menu-item>
                        </el-menu-item-group>
                    </el-submenu>
                    <el-submenu index="4">
                        <template slot="title"><i class="el-icon-photo"></i>摄影管理</template>
                        <el-menu-item-group>
                            <el-menu-item index="4-1" route="/photo">作品管理</el-menu-item>
                        </el-menu-item-group>
                    </el-submenu>
                    <el-submenu index="5">
                        <template slot="title"><i class="el-icon-user"></i>用户管理</template>
                        <el-menu-item-group>
                            <el-menu-item index="5-1" route="/user">用户列表</el-menu-item>
                        </el-menu-item-group>
                    </el-submenu>
                    <el-submenu index="6">
                        <template slot="title"><i class="el-icon-report"></i>事件管理</template>
                        <el-menu-item-group>
                            <el-menu-item index="6-1" route="/event">事件列表</el-menu-item>
                        </el-menu-item-group>
                    </el-submenu>
                  </el-menu>
            </el-aside>
            <el-container>
                <el-main>
                    <router-view name="info"/>
                </el-main>
                <el-footer style="padding: 0px;" class="footer-color">
                    <div class="footer-standard-main footer-color">
                        <div class="container">
                            <div class="row row-50">
                                <div class="col-lg-4">
                                    <div class="inset-right-1">
                                        <h4>关于我们</h4>
                                        <p>17 组</p>
                                    </div>
                                </div>
                                <div class="col-sm-6 col-md-5 col-lg-4">
                                    <div class="box-1">
                                      <h4>联系方式</h4>
                                      <ul class="list-sm">
                                        <li class="object-inline"><span class="icon icon-md mdi mdi-map-marker text-gray-700"></span><a class="link-default" href="#">临潼区 <br> 西安科技大学</a></li>
                                        <li class="object-inline"><span class="icon icon-md mdi mdi-phone text-gray-700"></span><a class="link-default" href="tel:#">（+86）9090-900</a></li>
                                        <li class="object-inline"><span class="icon icon-md mdi mdi-email text-gray-700"></span><a class="link-default" href="mailto:#">beordie.cloud@gmail.com</a></li>
                                    </ul>
                                    </div>
                                </div>
                                <div class="col-sm-6 col-md-7 col-lg-4">
                                    <h4>加入我们</h4>
                                    <p>通过邮件加入我们，但不一定会处理。</p>
                                    <form class="rd-form rd-mailform form-inline" data-form-output="form-output-global" data-form-type="subscribe" method="post" action="bat/rd-mailform.php">
                                        <div class="form-wrap">
                                            <input class="form-input" id="subscribe-form-2-email" type="email" name="email" data-constraints="@Email @Required">
                                            <label class="form-label" for="subscribe-form-2-email">E-mail</label>
                                        </div>
                                        <div class="form-button">
                                            <button class="button button-primary button-icon button-icon-only button-winona" type="submit" aria-label="submit"><span class="icon mdi mdi-email-outline"></span></button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </el-footer>
            </el-container>
        </el-container>
    </el-container>
</template>

<script>
export default {
  name: 'App',
  components: {
    
  },
  data() {
    return {
      logo: require('../assets/images/logo.png'),
      logo1: require('../assets/images/logo1.png'),
      logo2: require('../assets/images/logo2.png'),
      logo3: require('../assets/images/logo3.png'),
      logo4: require('../assets/images/logo4.png'),
      logo5: require('../assets/images/logo5.png'),
      logo6: require('../assets/images/logo6.png'),
    }
  },
  methods: {
    loginOut() {
      localStorage.removeItem("role")
      this.$router.push('/login');
    },
  },
  mounted () {

  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
.header-color {
  background-color: rgb(62, 95, 133);
  color: white;
  height: 60px;
}

.footer-color {
  background-color: rgb(62, 95, 133);
  color: white;
  padding: 0px;
}

.el-footer {
  padding: 0px;
}
</style>
