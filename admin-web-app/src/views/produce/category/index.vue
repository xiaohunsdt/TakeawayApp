<template>
  <div class="container">
    <base-card class="container-header">
      <el-row>
        <el-col :span="18">
          <el-form :inline="true" :model="formData" class="demo-form-inline" size="mini">
            <el-form-item label="名称">
              <el-input v-model="formData.name" placeholder="分类名称"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="onSearch">查询</el-button>
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
          v-loading="listLoading"
          :data="tableData"
          class="tb-edit"
          element-loading-text="正在加载中..."
          highlight-current-row
          stripe
          style="width: 100%">
        <el-table-column
            label="ID"
            prop="id"
            width="350">
        </el-table-column>
        <el-table-column label="名称">
          <template v-slot="scope">
            <el-input
                v-model="scope.row.name"
                placeholder="请输入内容"
                size="small"
                @change="onEdit(scope.$index, scope.row)"></el-input>
            <span>{{ scope.row.name }}</span>
          </template>
        </el-table-column>
        <el-table-column label="优先级">
          <template v-slot="scope">
            <el-input
                v-model="scope.row.index"
                placeholder="请输入优先级"
                size="small"
                @change="onEdit(scope.$index, scope.row)"></el-input>
            <span>{{ scope.row.index }}</span>
          </template>
        </el-table-column>
        <el-table-column
            label="操作"
            width="150">
          <template v-slot="scope">
            <el-button size="mini" type="danger" @click="onDelete(scope.row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
          :current-page="page.current"
          :page-size="page.size"
          :page-sizes="[15, 50, 100]"
          :total="page.total"
          background
          layout="total, sizes, prev, pager, next, jumper"
          style="margin-top: 15px"
          @current-change="handleCurrentChange"
          @size-change="handleSizeChange">
      </el-pagination>
    </base-card>
  </div>
</template>

<script>
import BaseCard from '@c/BaseCard/index'
import categoryApi from '@a/category'
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
  created() {
    this.onSearch()
  },
  methods: {
    getList() {
      this.listLoading = true

      categoryApi.getCategoryListByPage(this.page, this.formData)
          .then(response => {
            this.tableData = response.records
            this.page.total = parseInt(response.total)
          })
          .finally(() => {
            this.listLoading = false
          })
    },
    onSearch() {
      this.page.current = 1
      this.getList()
    },
    onEdit(index, row) {
      categoryApi.updateCategory(row)
          .then((res) => {
            this.$message.success(res.message)
          })
    },
    onDelete(id) {
      this.$confirm('删除此分类会导致此分类下的所有产品都将被删除!!是否确定删除此分类?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        categoryApi.deleteCategory(id)
            .then(() => {
              this.getList()
            })
      })
    },
    handleSizeChange(val) {
      this.page.size = val
      this.getList()
    },
    handleCurrentChange(val) {
      this.page.current = val
      this.getList()
    }
  }
}
</script>

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
