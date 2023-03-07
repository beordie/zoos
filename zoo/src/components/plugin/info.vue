<template>
  <main id='sign'>
    <!-- Container START -->
    <div class="container">
        <div id="main-body" class="card card-body text-center">
            <el-row style="margin: 0px;">
              <el-col :span="8" style="margin-bottom: 8px;">
                  <span class="detail">用户姓名:&ensp;</span>
              </el-col>
              <el-col :span="16" style="padding-left: 0px;">
                  <el-input :disabled="!isUser" class="vertical-interval" type="text" v-model="user.username" maxlength="20" show-word-limit>
                  </el-input>
              </el-col>
              <el-col :span="8" style="margin-bottom: 8px;">
                  <span class="detail">用户电话:&ensp;</span>
              </el-col>
              <el-col :span="16" style="padding-left: 0px;">
                  <el-input :disabled="!isUser" class="vertical-interval" type="text" v-model="user.phone" maxlength="20" show-word-limit>
                  </el-input>
              </el-col>
              <el-col :span="8" style="margin-bottom: 8px;">
                  <span class="detail">用户密码:&ensp;</span>
              </el-col>
              <el-col :span="16" style="padding-left: 0px;">
                  <el-input :disabled="!isUser" class="vertical-interval" type="password" v-model="user.password" maxlength="20" show-word-limit>
                  </el-input>
              </el-col>
              <el-col :span="8" style="margin-bottom: 8px;">
                  <span class="detail">身份证号:&ensp;</span>
              </el-col>
              <el-col :span="16" style="padding-left: 0px;">
                  <el-input :disabled="!isUser" class="vertical-interval" type="text" v-model="user.idNumber" maxlength="20" show-word-limit>
                  </el-input>
              </el-col>
              <el-col :span="8" style="margin-bottom: 8px;">
                  <span class="detail">创建时间:&ensp;</span>
              </el-col>
              <el-col :span="16" style="margin-bottom: 0px;">
                  <div class="vertical-interval">{{ dateFormat(user.ctime) }}</div>
              </el-col>
              <el-col :span="8" style="margin-bottom: 8px;">
                  <span class="detail">用户性别:&ensp;</span>
              </el-col>
              <el-col :span="16" style="padding-left: 0px;">
                <el-radio-group :disabled="!isUser" class="vertical-interval" v-model="user.sex">
                    <el-radio label='0'>男</el-radio>
                    <el-radio label='1'>女</el-radio>
                </el-radio-group>
              </el-col>
              <el-col :span="24" style="margin-top: 20px;">
                <el-button type="primary" v-if="isUser" @click="update">更新用户信息</el-button>
              </el-col>
              
          </el-row>
        </div>
    </div>
    <!-- Container END -->
  </main>
</template>

<script>
  export default {
    data() {
      return {
        user: {
          id: null,
          username: '匿名',
          password: null,
          phone: null,
          sex: 0,
          idNumber: null,
          ctime: 1678180104000
        },
        isUser: false,
        oldPassword: null
      }
    },
    methods: {
      selectUser(userId) {
        this.$http.get(this.$host+'/user/select/'+userId).then(res => {
          if (res.data.code != 200) {
            this.$message.error('当前服务异常，请稍后再试');
          } else {
            this.user = res.data.data
            this.oldPassword = this.user.password
            console.log(this.user)
          }
        })
      },
      dateFormat(date) { 
        if (date == undefined) { 
            return "";
        } 
        return this.$moment(date).format("YYYY年MM月DD日 HH时mm分ss秒"); 
      },
      update () {
        if (this.oldPassword === this.user.password) {
          this.user.password = null
        }
        this.$http.post(this.$host+'/user/fill', this.user, {headers: {'username': localStorage.getItem("username")}}).then(res => {
          if (res.data.code != 200) {
            this.$message.error('当前服务异常，请稍后再试');
          } else {
            this.$message({
              message: '更新信息成功',
              type: 'success',
              showClose: true
            });
            this.$parent.handleClose();
          }
        })
      }
    },
    mounted () {
      let userId = localStorage.getItem('userId')
      console.log(userId)
      if(userId == null) {
        this.$message.error('请先登录');
      } else {
        this.isUser = true
        this.selectUser(userId)
      }
    },
    created () {
      
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
