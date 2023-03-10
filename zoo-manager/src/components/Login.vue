<template>
  <div id="sign">
      <el-card style="text-align: center;">
          <div slot="header" style="text-align: center;">
              <span style="font-size: large; font-weight: bold;">登陆</span>
          </div>
          <el-input class="login-edit" type="text" placeholder="请输入用户名" v-model="user.username" maxlength="10" show-word-limit></el-input>
          <el-input class="login-edit" type="password" placeholder="请输入密码" v-model="user.password"  maxlength="18" show-word-limit>
              <template slot="append"><i class="el-icon-key"></i></template>
          </el-input>
          <el-row :gutter="20" style="margin: 0px;">
              <el-col :span="14" style="padding-left: 0px;">
                  <canvas @click="createCode" id="code"></canvas>
              </el-col>
              <el-col :span="10" style="padding: 0px;">
                  <el-input class="login-edit" type="text" placeholder="请输入校验码" v-model="codeValue" show-word-limit>
                      <template slot="append"><el-button circle>验证</el-button></template>
                  </el-input>
              </el-col>
          </el-row>
          <el-row :gutter="20" style="margin: 0px;">
              <el-col :span="18" style="padding-left: 0px;">
                <el-input class="login-edit" type="email" placeholder="请输入邮箱" v-model="user.email" show-word-limit>
                      <template slot="append"><i class="el-icon-message"></i></template>
                  </el-input>
              </el-col>
              <el-col :span="6" style="padding: 0px;">
                <el-button type="primary" @click="getCode" :disabled="noCode" >获取验证码</el-button>
              </el-col>
              <el-col :span="24" style="padding-left: 0px;">
                <el-input class="login-edit" type="text" placeholder="请输入验证码" v-model="user.code" show-word-limit>
                      <template slot="append"><i class="el-icon-key"></i></template>
                  </el-input>
              </el-col>
          </el-row>
          <div class="login-edit">
              <el-checkbox v-model="user.remember">记住我？</el-checkbox>
              <span><a href="/">忘记密码</a></span>
          </div>
          <el-button type="primary" @click="login">用户登陆</el-button>
          <p class="">©2022 <a target="_blank" href="https://www.webestica.com/">黔ICP备2021008357号.</a> </p>
      </el-card>
  </div>
</template>

<script>
export default {
  data () {
    return {
      user: {
        username: '',
        password: '',
        email: null,
        code: null
      },
      remember: 0,
      codeValue: '',
      code: {
        width: 250,
        height: 40,
        value: ''
      },
      noCode: false
    }
  },
  methods: {
    login () {
      if(this.codeValue !== this.code.value) {
          this.$message.warning('验证码校验失败');
          return;
        }
      if(this.user.username == '' || this.user.password == '') {
          this.$message.warning('用户名密码不能为空');
          return;
        }
        console.log(this.user)
      this.$http.post(this.$host+'/user/login/admin', this.user).then(res => {
        if (res.data.code != 200) {
          console.log(res)
          this.$message.error('当前服务异常，请稍后再试');
        } else {
          this.$message({
            message: '登陆成功',
            type: 'success',
            showClose: true
          });
          localStorage.setItem("role", this.user.username)
          this.$router.push('/');
        }
      })
    },
    getCode() {
      let emailReg = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;            
        if (!emailReg.test(this.user.email)) {
          this.$message.warning('邮箱校验失败');
          return;              
        }
        this.noCode = true
      this.$http.get(this.$host+'/user/login/code?email='+this.user.email).then(res => {
          if (res.data.code != 200) {
            this.noCode = false
            this.$message.error('当前服务异常，请稍后再试');
          } else {
            this.$message({
              message: '请查收邮箱',
              type: 'success',
              showClose: true
            });
            this.noCode = false
          }
        })
    },
    produceCode () {
      var code = ''
      var random = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z']
      for (var i = 0; i < 4; i++) {
        var index = Math.floor(Math.random() * 62)
        code += random[index]
      }
      this.code.value = code
    },
    randomNum (min, max) {
      return Math.floor(Math.random() * (max - min) + min)
    },
    randomColor (min, max) {
      var r = this.randomNum(min, max)
      var g = this.randomNum(min, max)
      var b = this.randomNum(min, max)
      return 'rgb(' + r + ',' + g + ',' + b + ')'
    },
    createCode () {
      this.produceCode()
      var canvas = document.getElementById('code')
      canvas.width = this.code.width
      canvas.height = this.code.height
      var ctx = canvas.getContext('2d')
      ctx.textBaseline = 'middle'
      ctx.fillStyle = this.randomColor(60, 255)
      ctx.fillRect(0, 0, this.code.width, this.code.height)
      for (var i = 0; i < this.code.value.length; i++) {
        var txt = this.code.value.charAt(i)
        ctx.font = '50px SimHei'
        ctx.fillStyle = this.randomColor(60, 180)
        ctx.shadowOffsetY = this.randomNum(-3, 3)
        ctx.shadowBlur = this.randomNum(-3, 3)
        ctx.shadowColor = 'rgba(0, 0, 0, 0.3)'
        var x = this.code.width / (this.code.value.length + 1) * (i + 1)
        var y = this.code.height / 2
        var deg = this.randomNum(-30, 30)
        ctx.translate(x, y)
        ctx.rotate(deg * Math.PI / 180)
        ctx.fillText(txt, 0, 0)
        ctx.rotate(-deg * Math.PI / 180)
        ctx.translate(-x, -y)
      }
      for (i = 0; i < this.randomNum(1, 4); i++) {
        ctx.strokeStyle = this.randomColor(40, 180)
        ctx.beginPath()
        ctx.moveTo(this.randomNum(0, this.code.width), this.randomNum(0, this.code.height))
        ctx.lineTo(this.randomNum(0, this.code.width), this.randomNum(0, this.code.height))
        ctx.stroke()
      }
      for (i = 0; i < this.code.width / 6; i++) {
        ctx.fillStyle = this.randomColor(0, 255)
        ctx.beginPath()
        ctx.arc(this.randomNum(0, this.code.width), this.randomNum(0, this.code.height), 1, 0, 2 * Math.PI)
        ctx.fill()
      }
    }
  },
  mounted () {
    this.createCode()
  }
}
</script>

<style>
#sign {
    margin: auto;
    padding-top: 200px;
}
#sign .container {
    padding: 0px;
}

.login-edit {
    margin-bottom: 20px;
}
</style>
