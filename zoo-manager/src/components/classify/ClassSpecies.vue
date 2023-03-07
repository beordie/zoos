<template>
    <div>
        <el-breadcrumb separator="/">
            <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item><a href="/">物种管理</a></el-breadcrumb-item>
            <el-breadcrumb-item>纲种</el-breadcrumb-item>
        </el-breadcrumb>
        <el-divider></el-divider>
        <el-card class="box-card">
            <el-form :model="queryParams" ref="queryParams" :inline="true">
                <el-form-item prop="classSpecies">
                    <el-select v-model="queryParams.classSpecies" filterable remote clearable reserve-keyword placeholder="搜索纲种名">
                        
                    </el-select>
                </el-form-item>

                <el-form-item>
                    <el-button type="primary" icon="el-icon-search">查询</el-button>
                    <el-button type="primary" icon="el-icon-refresh">清空</el-button>
                    <el-button type="primary" icon="el-icon-plus" @click="editClick()">新增</el-button>
                </el-form-item>
            </el-form>
            <el-table :data="data" border style="width: 100%">
                <el-table-column prop="id" v-if="false"></el-table-column>
                <el-table-column prop="classSpecies" label="纲种名" width="150"></el-table-column>
                <el-table-column label="操作">
                    <template slot-scope="scope">
                        <el-button size="small" type="primary" @click="editClick(scope.row)">编辑</el-button>
                        <el-button size="small" type="danger" @click="deleteClick(scope.row.id)">删除</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <div class="pagination" style="margin-top:20px;text-align:center;">
              <el-pagination background @size-change="handleSizeChange" @current-change="handleCurrentChange" layout="total,prev, sizes, pager, next,jumper"
                  :current-page.sync="pageParams.offset" :page-sizes="[5, 10]" :page-size="5" :total="total">
              </el-pagination>
            </div>
        </el-card>
        <el-dialog class="classify" title="修改增加" :visible.sync="editVisible" :width="width" :before-close="handleClose">
            <el-card style="text-align: center;">
                <el-input class="vertical-interval" type="text" placeholder="请输入纲种名" v-model="editParam.classSpecies" maxlength="10" show-word-limit></el-input>
                <el-button type="primary" v-if="editParam.id == null" @click="add">增加</el-button>
                <el-button type="primary" v-if="editParam.id != null">更新</el-button>
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
          data: [
            {
                id: 1,
                classSpecies: '哺乳纲',
            }
          ],
          queryParams: {
            classSpecies: null
          },
          pageParams: {
            offset: 1,
            limit: 5
          },
          total: 20,
          editVisible: false,
          editParam: {
            id: null,
            classSpecies: null
          },
          width: '30%'
      }
  },
  methods: {
    handleClose (done) {
      this.editParam = {
        id: null,
        classSpecies: null
      }
      done()
    },
    goBack() {

    },
    editClick(row) {
      if (row !== undefined) {
        this.editParam = row
      }
      this.editVisible = true
    },
    handleCurrentChange(val) {
        this.pageParams.offset = val;
        this.selectList()
    },
    handleSizeChange(val) {
        this.pageParams.limit = val
        this.selectList()
    },
    selectList () {
      this.getTotal()
      this.$http.get(this.$host + 'animal/select/kind/classSpecies?offset=' + this.pageParams.offset
          + "&limit=" + this.pageParams.limit
      ).then(res => {
        if (res.data.code == 200) {
            this.$notify({
                title: '成功',
                message: '查询成功',
                type: 'success'
            });
            this.data = res.data.data
        } else {
            console.log(res)
        }
      }).catch(({err}) => {
          window.alert(err)
      })
    },
    getTotal () {
      this.$http.get(this.$host + '/animal/select/count/classSpecies').then(res => {
          if (res.data.code == 200) {
              this.total = res.data.data
          } else {
              console.log(res)
          }
      }).catch(({err}) => {
          window.alert(err)
      })
    },
    add () {
        this.$http.post(this.$host + '/animal/create', this.editParam).then(res => {
            if (res.data.code == 200) {
                this.$notify({
                    title: '成功',
                    message: '新增成功',
                    type: 'success'
                });
                this.selectList()
                this.editVisible = false;
                this.editParam = {
                    id: null,
                    classSpecies: null
                }
            } else {
                console.log(res)
            }
        }).catch(({err}) => {
            window.alert(err)
        })
    },
    deleteClick (id) {
      this.$http.post(this.$host + '/animal/delete?animalId=' + id).then(res => {
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
    }
  },
  mounted () {
    this.selectList()
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style>
.classify>.el-dialog>.el-dialog__body {
  padding: 0px;
}
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
