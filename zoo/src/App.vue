<template>
  <div id="app">
    <el-container>
      <el-header class="header-color">
        <el-row type="flex">
          <el-col :span="10">
            
          </el-col>
          <el-col :span="13" class="header-color">
            <el-menu mode="horizontal" router>
              <el-submenu index="1">
                <template slot="title">分类查询</template>
                <el-menu-item index="1-1">国家一级保护动物</el-menu-item>
                <el-menu-item index="1-2">国家二级保护动物</el-menu-item>
                <el-menu-item index="1-3">哺乳动物</el-menu-item>
              </el-submenu>
            </el-menu>
          </el-col>
          <el-col :span="1">
            <div class="header-avatar">
              <el-dropdown :hide-on-click="false">
                <span class="el-dropdown-link">
                  <el-avatar style="margin: 10px;" src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
                </span>
                <el-dropdown-menu slot="dropdown">
                  <el-dropdown-item @click.native="login">登陆</el-dropdown-item>
                  <el-dropdown-item @click.native="register">注册</el-dropdown-item>
                  <el-dropdown-item disabled>信息</el-dropdown-item>
                  <el-dropdown-item divided>退出</el-dropdown-item>
                </el-dropdown-menu>
              </el-dropdown>
            </div>
          </el-col>
        </el-row>
      </el-header>
      <el-main>
        <router-view />
      </el-main>
      <el-footer class="footer-color">
        <div class="footer-standard-main footer-color">
          <div class="container">
            <div class="row row-50">
              <div class="col-lg-4">
                <div class="inset-right-1">
                  <h4>About Me</h4>
                  <p>My name is Jonathan Davis and I’m professional photographer and retoucher. I’m offering my services to individual and corporate clients throughout the USA. Make your favorite life moment or event last and remain in your memory!</p>
                </div>
              </div>
              <div class="col-sm-6 col-md-5 col-lg-4">
                <div class="box-1">
                  <h4>Contact Information</h4>
                  <ul class="list-sm">
                    <li class="object-inline"><span class="icon icon-md mdi mdi-map-marker text-gray-700"></span><a class="link-default" href="#">2130 Fulton Street <br> San Diego, CA 94117-1080 USA</a></li>
                    <li class="object-inline"><span class="icon icon-md mdi mdi-phone text-gray-700"></span><a class="link-default" href="tel:#">1-800-1234-678</a></li>
                    <li class="object-inline"><span class="icon icon-md mdi mdi-email text-gray-700"></span><a class="link-default" href="mailto:#">info@demolink.org</a></li>
                  </ul>
                </div>
              </div>
              <div class="col-sm-6 col-md-7 col-lg-4">
                <h4>Newsletter</h4>
                <p>Sign up to my newsletter and be the first to know about the latest news, special offers, events, and discounts.</p>
                <!-- RD Mailform-->
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

      <el-dialog title="注册" :visible.sync="RegisterVisible" :width="width" :before-close="handleClose">
        <Register />
      </el-dialog>
      <el-dialog title="登陆" :visible.sync="LoginVisible" :width="width" :before-close="handleClose">
        <Login />
      </el-dialog>
    </el-container>
  </div>
</template>

<script>
import Register from './components/plugin/register-in.vue'
import Login from './components/plugin/sing-in.vue'

export default {
  name: 'App',
  components: {
    Register, Login
  },
  data() {
    return {
      RegisterVisible: false,
      LoginVisible: false,
      width: '30%',
    }
  },
  methods: {
    handleClose(done) {
       done();
    },
    register() {
      this.RegisterVisible = true
    },
    login() {
      this.LoginVisible = true
    }
  }
}
</script>

<style>
.header-avatar>.el-dropdown {
  height: 60px;
}
.header-avatar {
  height: 60px;
  line-height: 60px;
  margin: auto;
  text-align: center;
  align-items: center;
}

.header-color {
  background-color: rgb(62, 95, 133);
  color: white;
  height: 60px;
}

.footer-color {
  background-color: rgb(62, 95, 133);
  color: white;
}

.el-footer {
  padding: 0px;
}

.header-color>.el-menu {
  background-color: rgb(62, 95, 133);
}

.header-color>.el-menu--horizontal>.el-submenu .el-submenu__title {
  color: white;
  font-weight: bold;
}

.header-color>.el-menu.el-menu--horizontal {
  border-bottom: none
}

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

body {
  margin: 0px;
}

.el-menu .el-menu-item:hover{
    outline: 0 !important;
    color: #2E95FB !important;
    background: linear-gradient(270deg, #F2F7FC 0%, #FEFEFE 100%)!important;
}
.el-menu .el-menu-item.is-active {
    color: #2E95FB !important;
    background: linear-gradient(270deg, #F2F7FC 0%, #FEFEFE 100%)!important;
}
.el-submenu .el-submenu__title:hover {
  color: #2E95FB !important;
  background: linear-gradient(270deg, #F2F7FC 0%, #FEFEFE 100%)!important;
}

</style>
