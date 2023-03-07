<template>
  <main id='sign'>
    <!-- Container START -->
    <div class="container">
      <div id="main-body" class="card card-body text-center">
        <form>
          <!-- New password -->
          <div class="mb-3 input-group-lg">
            <input v-model="event.name" class="form-control" type="text" placeholder="输入举报人姓名">
          </div>
          <div class="mb-3 input-group-lg">
            <input v-model="event.phone" class="form-control" type="text" placeholder="输入举报人联系方式">
          </div>
          <!-- Confirm password -->
          <div class="mb-3 input-group-lg">
            <input v-model="event.reportedEvent" class="form-control" type="text" placeholder="输入举报事件">
          </div>
          <!-- Button -->
          <div class="d-grid"><button @click="report()" type="button" class="btn btn-lg btn-primary">提交举报</button>
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
        event: {
          name: '',
          phone: '',
          reportedEvent: ''
        }
      };
    },
    methods: {
      report() {
        if(this.event.name == '' || this.event.phone == '' || this.event.reportedEvent == '') {
          this.$message.warning('信息不能为空')
          return;
        }
        this.$http.post(this.$host + '/report/commit', this.event).then(res => {
          if (res.data.code != 200) {
            this.$message.error(res.data.message);
          } else {
            this.$message({
              message: res.data.message,
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
