<template>
  <div class="container">
    <base-card class="container-header">
      <el-row>
        <el-col :span="18">
          <el-form :inline="true" :model="formData" class="demo-form-inline" size="mini">
            <el-form-item label="名称">
              <el-input placeholder="分类名称" v-model="formData.name"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button @click="onSearch" type="primary">查询</el-button>
            </el-form-item>
          </el-form>
        </el-col>
        <el-col :span="6" style="text-align: right">
          <add-category-dialog
            @createSuccess="onSearch"/>
        </el-col>
      </el-row>
    </base-card>
    <base-card class="container-main">
      <el-table
        class="tb-edit"
        :data="tableData"
        element-loading-text="正在加载中..."
        highlight-current-row
        stripe
        style="width: 100%"
        v-loading="listLoading"
      >
        <el-table-column
          label="ID"
          prop="id"
          width="350"
        >
        </el-table-column>
        <el-table-column
          label="名称"
        >
          <template scope="scope">
            <el-input
              @change="onEdit(scope.$index, scope.row)"
              placeholder="请输入内容"
              size="small"
              v-model="scope.row.name"></el-input>
            <span>{{scope.row.name}}</span>
          </template>
        </el-table-column>
        <el-table-column
          label="操作"
          width="150">
          <template scope="scope">
            <el-button @click="onDelete(scope.row.id)" size="mini" type="danger">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
        :current-page="page.current"
        :page-size="page.size"
        :page-sizes="[15, 50, 100]"
        :total="page.total"
        @current-change="handleCurrentChange"
        @size-change="handleSizeChange"
        background
        layout="total, sizes, prev, pager, next, jumper"
        style="margin-top: 15px">
      </el-pagination>
    </base-card>
  </div>
</template>

<script>
    import BaseCard from '@/components/BaseCard/BaseCard'
    import categoryApi from '@/api/category'
    import AddCategoryDialog from './components/AddCategoryDialog'

    export default {
        name: 'CategoryManagement',
        components: {
            BaseCard,
            AddCategoryDialog
        },
        data() {
            return {
                page: {
                    current: 1,
                    size: 15,
                    total: 0
                },
                formData: {
                    name: null
                },
                listLoading: false,
                tableData: []
            }
        },
        methods: {
            onSearch() {
                this.listLoading = true
                categoryApi.getCategoryListByPage(this.page, this.formData)
                    .then(response => {
                        this.tableData = response.records
                        this.page.total = parseInt(response.total)
                        this.listLoading = false
                    }).catch(() => {
                    this.listLoading = false
                })
            },
            onEdit(index, row) {
                categoryApi.updateCategory(row)
                    .then((response) => {
                        this.$message({
                            message: response.message,
                            type: 'success'
                        })
                    })
            },
            onDelete(id) {
                this.$confirm('是否确定删除此分类?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    categoryApi.delteCategory(id)
                        .then(() => {
                            this.onSearch()
                        })
                })
            },
            handleSizeChange(val) {
                this.onSearch()
            },
            handleCurrentChange(val) {
                this.onSearch()
            }
        },
        created() {
            this.onSearch()
        }
    }
</script>

<style lang="scss">
  .el-form-item {
    margin-bottom: unset !important;
  }

  .tb-edit {
    .el-input {
      display: none
    }

    .current-row .el-input {
      display: block
    }

    .current-row .el-input + span {
      display: none
    }
  }
</style>
<style lang="scss" scoped>
  $bg: #F3F3F9;

  .container {
    padding: 1rem;
    min-height: inherit;
    width: 100%;
    background-color: $bg;
    overflow: hidden;

    &-header {
      margin-bottom: 30px;
    }
  }
</style>
