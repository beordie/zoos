<template>
  <el-container>
      <router-view name="Main"/>
  </el-container>
</template>

<script>
export default {
name: 'App',
components: {
  
},
data() {
  return {
  }
},
mounted () {
  let role = localStorage.getItem("role")
  if(role === null) {
      this.$router.push('/login');
  }
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
