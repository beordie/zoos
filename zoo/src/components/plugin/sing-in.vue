<template>
  <main id='sign'>
    <!-- Container START -->
    <div class="container">
        <div id="main-body" class="card card-body text-center">
          <!-- Form START -->
          <form class="mt-sm-4">
            <!-- Email -->
            <div class="mb-3 input-group-lg">
              <input v-model="name" type="text" class="form-control" placeholder="用户名">
            </div>
            <!-- New password -->
            <div class="mb-3 position-relative">
              <!-- Password -->
              <div class="input-group input-group-lg">
                <input v-model="password" class="form-control fakepassword" type="password" id="psw-input"
                  placeholder="密码">
                <span class="input-group-text p-0">
                  <i class="fakepasswordicon fa-solid fa-eye-slash cursor-pointer p-2 w-40px"></i>
                </span>
              </div>
              <!-- Pswmeter -->
              <div id="pswmeter" class="mt-2"></div>
              <div class="d-flex mt-1">
                <div id="pswmeter-message" class="rounded"></div>
                <!-- Password message notification -->
                <div class="ms-auto">
                  <i class="bi bi-info-circle ps-1" data-bs-container="body" data-bs-toggle="popover"
                    data-bs-placement="top"
                    data-bs-content="Include at least one uppercase, one lowercase, one special character, one number and 8 characters long."
                    data-bs-original-title="" title=""></i>
                </div>
              </div>
            </div>
            <!-- Remember me -->
            <div class="mb-3 d-sm-flex justify-content-between">
              <div style="padding: 0px 30px;">
                <input type="checkbox" class="form-check-input" id="rememberCheck">
                <label class="form-check-label" for="rememberCheck">记住我?</label>
              </div>
              <a href="#!">忘记密码?</a>
            </div>
            <!-- Button -->
            <div class="d-grid"><button type="button" @click="login()" class="btn btn-lg btn-primary">登陆</button>
            </div>
            <!-- Copyright -->
            <p class="mb-0 mt-3">©2022 <a target="_blank" href="https://www.webestica.com/">黔ICP备2021008357号.</a> </p>
          </form>
          <!-- Form END -->
        </div>
    </div>
    <!-- Container END -->
  </main>
</template>

<script>
  export default {
    data() {
      return {
        name: '',
        password: '',
      }
    },
    methods: {
      login() {
        if(this.name == '' || this.password == '') {
          this.$message.warning('用户名密码不能为空');
          return;
        }
        this.$http.post('http://127.0.0.1:83/user/login', {
          name: this.name,
          password: this.password
        }).then(res => {
          if (res.data.code != 200) {
            this.$message.error('当前服务异常，请稍后再试');
          } else {
            this.$message({
              message: '登陆成功, 请手动关闭登录页',
              type: 'success',
              showClose: true
            });
            localStorage.setItem('token', res.data.data.token);
            localStorage.setItem('username', res.data.data.name);
            localStorage.setItem('avatar', res.data.data.image);
            this.$forceUpdate();
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
