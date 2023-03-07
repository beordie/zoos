<template>
    <div>
        <el-breadcrumb separator="/">
            <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item><a href="/">用户管理</a></el-breadcrumb-item>
        </el-breadcrumb>
        <el-divider></el-divider>
        <el-card class="box-card">
          <el-form :model="queryParams" ref="queryParams" :inline="true">
              <el-form-item prop="username">
                  <el-select :loading="loading" :remote-method="selectUsernames" v-model="queryParams.username" filterable remote clearable reserve-keyword placeholder="搜索用户名">
                      <el-option v-for="(username, index) in usernames" :key="index" :label="username" :value="username"></el-option>
                  </el-select>
              </el-form-item>

              <el-form-item>
                <el-button type="primary" icon="el-icon-search" @click="query">查询</el-button>
                <el-button type="primary" icon="el-icon-refresh">清空</el-button>
              </el-form-item>
            </el-form>
            <el-table :data="users" border style="width: 100%">
                <el-table-column prop="id" v-if="false"></el-table-column>
                <el-table-column prop="username" label="用户名" width="150"></el-table-column>
                <el-table-column prop="password" label="密码" width="300"></el-table-column>
                <el-table-column prop="phone" label="联系方式" width="150"></el-table-column>
                <el-table-column label="性别" width="150">
                  <template slot-scope="scope">
                    <el-tag :type="scope.row.sex === '0' ? 'primary' : 'success'" disable-transitions>{{scope.row.sex == 0 ? '男' : '女'}}</el-tag>
                  </template>
                </el-table-column>
                <el-table-column prop="idNumber" label="身份证" width="300"></el-table-column>
                <el-table-column prop="ctime" :formatter="dateFormat" label="时间" width="200"></el-table-column>
                <el-table-column label="操作">
                    <template slot-scope="scope">
                        <el-button size="small" type="primary" @click="editClick(scope.row)">编辑</el-button>
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

        <el-dialog class="classify" title="修改" :visible.sync="editVisible" :width="width" :before-close="handleClose">
            <el-card style="text-align: left;">
                <el-input class="vertical-interval" type="text" placeholder="请输入用户名" v-model="editParam.username" maxlength="10" show-word-limit>
                    <template slot="append">用户姓名</template>
                </el-input>
                <el-input :disabled="true" class="vertical-interval" type="text" placeholder="请输入密码" v-model="editParam.password" maxlength="20" show-word-limit>
                    <template slot="append">用户密码</template>
                </el-input>
                <el-input class="vertical-interval" type="text" placeholder="请输入电话" v-model="editParam.phone" maxlength="20" show-word-limit>
                    <template slot="append">联系方式</template>
                </el-input>
                <el-radio-group class="vertical-interval" v-model="editParam.sex">
                    <el-radio label='0'>男</el-radio>
                    <el-radio label='1'>女</el-radio>
                </el-radio-group>
                <el-input class="vertical-interval" type="text" placeholder="请输入身份证号" v-model="editParam.idNumber" maxlength="30" show-word-limit>
                    <template slot="append">身份证号</template>
                </el-input>
                
                <el-button type="primary" v-if="editParam.id == null">增加</el-button>
                <el-button type="primary" v-if="editParam.id != null" @click="update">更新</el-button>
            </el-card>
        </el-dialog>
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
          users: [
              {
                  username: '张三',
                  password: '11111',
                  phone: '110',
                  sex: 0,
                  idNumber: '52242',
                  ctime: '111111'
              }
          ],
          usernames: [
            '张三'
          ],
          queryParams: {
              username: ''
          },
          pageParams: {
              offset: 1,
              limit: 5,
              total: 10
          },
          loading: false,
          editParam: {
              username: null,
              password: null,
              phone: null,
              sex: 0,
              idNumber: null
          },
          editVisible: false,
          width: '30%'
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
    selectUsernames(name) {
      if (name !== '') {
        this.loading = true;

        this.loading = false;
      } else {
        this.usernames = [];
      }
    },
    editClick(row) {
      if (row !== undefined) {
          this.editParam = row
      }
      this.editVisible = true
    },
    handleClose (done) {
      this.editParam = {
        username: null,
        password: null,
        phone: null,
        sex: 0,
        idNumber: null
      }
      done()
    },
    query() {
        this.selectList()
    },
    selectList () {
      this.getTotal()
      this.$http.get(this.$host + 'user/list?offset=' + this.pageParams.offset
          + "&limit=" + this.pageParams.limit
      ).then(res => {
        if (res.data.code == 200) {
            this.$notify({
                title: '成功',
                message: '查询成功',
                type: 'success'
            });
            this.users = res.data.data
        } else {
            console.log(res)
        }
      }).catch(({err}) => {
          window.alert(err)
      })
    },
    getTotal () {
      this.$http.get(this.$host + '/user/count').then(res => {
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
      this.$http.post(this.$host + '/user/delete?userId=' + id).then(res => {
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
    update () {
      this.$http.post(this.$host + '/user/update', this.editParam).then(res => {
          if (res.data.code == 200) {
              this.$notify({
                  title: '成功',
                  message: '更新',
                  type: 'success'
              });
              this.selectList()
              this.editVisible = false
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
            return this.$moment(date).format("YYYY年MM月DD日 HH:mm:ss"); 
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
