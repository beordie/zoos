<template>
  <main id='sign'>
    <!-- Container START -->
    <div class="container">
      <div id="main-body" class="card card-body text-center">
        <form>
          <!-- New password -->
          <div class="mb-3 input-group-lg">
            <input v-model="sign.username" class="form-control" type="text" placeholder="输入用户名">
          </div>
          <div class="mb-3 position-relative">
            <!-- Input group -->
            <div class="input-group input-group-lg">
              <input v-model="sign.password" class="form-control fakepassword" type="password" id="psw-input"
                placeholder="输入密码">
              <span class="input-group-text p-0">
                <i class="fakepasswordicon fa-solid fa-eye-slash cursor-pointer p-2 w-40px"></i>
              </span>
            </div>
            <!-- Pswmeter -->
            <div id="pswmeter" class="mt-2"></div>
          </div>
          <!-- Confirm password -->
          <div class="mb-3 input-group-lg">
            <input v-model="newPassword" class="form-control" type="password" placeholder="确认密码">
          </div>
          <!-- Button -->
          <div class="d-grid"><button @click="register()" type="button" class="btn btn-lg btn-primary">注册</button>
          </div>
          <!-- Copyright -->
          <p class="mb-0 mt-3">©2022 <a target="_blank" href="https://www.webestica.com/">黔ICP备2021008357号.</a> </p>
        </form>
      </div>
    </div>
    <!-- Container END -->
  </main>
</template>

<script>
  export default {
    data() {
      return {
        sign: {
          username: '',
          password: ''
        },
        newPassword: ''
      };
    },
    methods: {
      getCode() {
        this.$http.post('http://127.0.0.1:83/email/code', {
          email: this.email
        }).then(res => {
          if (res.data.code != 200) {
            this.$message.error('当前服务异常，请稍后再试');
          } else {
            this.$message({
              message: res.data.message,
              type: 'success',
              showClose: true
            });
          }
        })
      },
      register() {
        if(this.sign.username == '' || this.sign.password == '') {
          this.$message.warning('用户名密码不能为空')
          return;
        }
        if(this.newPassword !== this.sign.password) {
          this.$message.warning('密码不一致')
          return;
        }
        this.$http.post(this.$host + '/user/register', this.sign).then(res => {
          if (res.data.code != 200) {
            this.$message.error(res.data.message);
          } else {
            this.$message({
              message: '注册成功',
              type: 'success',
              showClose: true
            });
            this.$parent.handleClose();
          }
        })
      }
    }
  }
</script>

<style>
  #sign {
    margin: 0px;
    padding: 0px;
  }

  #sign .container {
    padding: 0px;
  }
</style>
