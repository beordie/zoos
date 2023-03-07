<template>
    <div>
        <el-breadcrumb separator="/">
            <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item><a href="/">物种管理</a></el-breadcrumb-item>
            <el-breadcrumb-item>亚纲</el-breadcrumb-item>
        </el-breadcrumb>
        <el-divider></el-divider>
        <el-card class="box-card">
            <el-form :model="queryParams" ref="queryParams" :inline="true">
                <el-form-item prop="classSpecies">
                    <el-select v-model="queryParams.classSpecies" filterable remote clearable reserve-keyword placeholder="选择纲种名">
                        <el-option v-for="(name, index) in classSpecies" :key="index" :label="name" :value="name"></el-option>
                    </el-select>
                </el-form-item>

                <el-form-item prop="subclass">
                    <el-select v-model="queryParams.subclass" filterable remote clearable reserve-keyword placeholder="输入亚纲名">
                    </el-select>
                </el-form-item>

                <el-form-item>
                    <el-button type="primary" icon="el-icon-search" @click="query">查询</el-button>
                    <el-button type="primary" icon="el-icon-refresh">清空</el-button>
                    <el-button type="primary" icon="el-icon-plus" @click="editClick()">新增</el-button>
                </el-form-item>
            </el-form>
            <el-table :data="data" border style="width: 100%">
                <el-table-column prop="id" v-if="false"></el-table-column>
                <el-table-column prop="classSpecies" label="纲种名" width="150"></el-table-column>
                <el-table-column prop="subclass" label="亚纲名" width="150"></el-table-column>
                <el-table-column label="操作">
                    <template slot-scope="scope">
                        <el-button size="small" type="primary" @click="editClick(scope.row)">编辑</el-button>
                        <el-button size="small" type="danger" @click="deleteClick(scope.row.id)">删除</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <div class="pagination" style="margin-top:20px;text-align:center;">
            <el-pagination background @size-change="handleSizeChange" @current-change="handleCurrentChange" layout="total,prev, sizes, pager, next,jumper"
                :current-page.sync="queryParams.offset" :page-sizes="[5, 10]" :page-size="5" :total="total">
            </el-pagination>
        </div>
        </el-card>

        <el-dialog class="classify" title="修改增加" :visible.sync="editVisible" :width="width" :before-close="handleClose">
            <el-card style="text-align: left;">
                <div class="el-input el-input--medium el-input-group el-input-group--prepend vertical-interval">
                    <div class="el-input-group__prepend">订单状态</div>
                    <el-select class="" v-model="editParam.classSpecies" filterable remote clearable reserve-keyword placeholder="选择纲种名">
                        <el-option v-for="(name, index) in classSpecies" :key="index" :label="name" :value="name"></el-option>
                    </el-select>
                </div>
                
                <el-input class="vertical-interval" type="text" placeholder="请输入亚纲名" v-model="editParam.subclass" maxlength="10" show-word-limit>
                </el-input>
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
                  subclass: '动物纲'
              }
          ],
          queryParams: {
              classSpecies: null,
              subclass: null,
              orders: null,
              family: null,
              genus: null,
              offset: 1,
              limit: 5
          },
          classSpecies: [
              '哺乳纲'
          ],
          total: 20,
          editParam: {
            id: null,
            classSpecies: null,
            subclass: null
          },
          editVisible: false,
          width: '30%'
      }
  },
  methods: {
    handleClose (done) {
      this.editParam = {
        id: null,
        classSpecies: null,
        subclass: null
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
        this.queryParams.offset = val;
        this.selectList()
    },
    handleSizeChange(val) {
        this.queryParams.limit = val
        this.selectList()
    },
    query() {
        this.selectList()
    },
    selectList () {
      this.getTotal()
      this.$http.post(this.$host + 'animal/select/kind/subclass', this.queryParams
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
      this.$http.post(this.$host + '/animal/select/count/subclass', this.queryParams).then(res => {
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
                    classSpecies: null,
                    subclass: null
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
    },
    getClassSpecies() {
        this.$http.get(this.$host + '/animal/select/classSpecies').then(res => {
          if (res.data.code == 200) {
              this.classSpecies = res.data.data
          } else {
              console.log(res)
          }
      }).catch(({err}) => {
          window.alert(err)
      })
    }
  },
  mounted () {
    this.getClassSpecies()
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
