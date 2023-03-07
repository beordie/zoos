<template>
    <div>
        <el-breadcrumb separator="/">
            <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item><a href="/">事件管理</a></el-breadcrumb-item>
        </el-breadcrumb>
        <el-divider></el-divider>
        <el-card class="box-card">
          <el-form :model="queryParams" ref="queryParams" :inline="true">
              <el-form-item prop="name">
                  <el-select v-model="queryParams.name" filterable remote clearable reserve-keyword placeholder="搜索举报用户名">
                  </el-select>
              </el-form-item>

              <el-form-item prop="reportingTime">
                <el-date-picker v-model="queryParams.reportingTime" type="datetimerange" range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期"></el-date-picker>
              </el-form-item>

              <el-form-item>
                <el-button type="primary" icon="el-icon-search" @click="query">查询</el-button>
                <el-button type="primary" icon="el-icon-refresh">清空</el-button>
              </el-form-item>
            </el-form>
            <el-table :data="events" border style="width: 100%">
                <el-table-column prop="id" v-if="false"></el-table-column>
                <el-table-column prop="name" label="举报人姓名" width="150"></el-table-column>
                <el-table-column prop="phone" label="举报人联系方式" width="300"></el-table-column>
                <el-table-column prop="reportedEvent" label="举报事件" width="150"></el-table-column>
                <el-table-column prop="reportingTime" :formatter="dateFormat" label="举报时间" width="300"></el-table-column>
                <el-table-column label="操作">
                    <template slot-scope="scope">
                        <el-button size="small" type="danger" @click="deleteClick(scope.row.id)">删除</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <div class="pagination" style="margin-top:20px;text-align:center;">
              <el-pagination background @size-change="handleSizeChange" @current-change="handleCurrentChange" layout="total,prev, sizes, pager, next,jumper"
                :current-page.sync="pageParams.offset" :page-sizes="[5, 10]" :page-size="5" :total="pageParams.total">
            </el-pagination>
        </div>
        </el-card>
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
          content: '详情页面',
          events: [
              {
                  name: '张三',
                  phone: '11111',
                  reportedEvent: '110',
                  reportingTime: 0
              }
          ],
          queryParams: {
              name: '',
              reportingTime: 0
          },
          pageParams: {
              offset: 1,
              limit: 5,
              total: 10
          },
      }
  },
  methods: {
    goBack() {

    },
    handleClick(row) {
        console.log(row);
    },
    handleCurrentChange(val) {
        this.pageParams.offset = val;
        this.selectList()
    },
    handleSizeChange(val) {
        this.pageParams.limit = val
        this.selectList()
    },
    query() {
        this.selectList()
    },
    selectList () {
      this.getTotal()
      this.$http.get(this.$host + 'report/list?offset=' + this.pageParams.offset
          + "&limit=" + this.pageParams.limit
      ).then(res => {
        if (res.data.code == 200) {
            this.$notify({
                title: '成功',
                message: '查询成功',
                type: 'success'
            });
            this.events = res.data.data
        } else {
            console.log(res)
        }
      }).catch(({err}) => {
          window.alert(err)
      })
    },
    getTotal () {
      this.$http.get(this.$host + '/report/count').then(res => {
          if (res.data.code == 200) {
              this.pageParams.total = res.data.data
          } else {
              console.log(res)
          }
      }).catch(({err}) => {
          window.alert(err)
      })
    },
    deleteClick (id) {
      this.$http.post(this.$host + '/report/delete?reportId=' + id).then(res => {
          if (res.data.code == 200) {
              this.$notify({
                  title: '成功',
                  message: '删除',
                  type: 'success'
              });
              this.selectList()
          } else {
              console.log(res)
          }
      }).catch(({err}) => {
          window.alert(err)
      })
    },
    dateFormat(row, column) { 
      var date = row[column.property]; 
      if (date == undefined) { 
          return ""; 
      } 
      return this.$moment(date).format("YYYY 年 MM 月 DD 日 HH 时 mm 分 ss 秒"); 
    },
  },
  mounted () {
    this.selectList()
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
