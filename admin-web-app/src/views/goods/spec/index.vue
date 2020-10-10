<template>
  <div class="container">
    <base-card class="container-header">
      <el-row>
        <el-col :span="18">
          <el-form :inline="true" :model="formData" class="demo-form-inline" size="mini">
            <el-form-item label="规格">
              <el-input v-model="formData.key" placeholder="规格名称"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="onSearch">查询</el-button>
            </el-form-item>
          </el-form>
        </el-col>
        <el-col :span="6" style="text-align: right">
          <el-button size="mini" type="success" @click="onCreate">添加新规格</el-button>
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
                v-model="scope.row.key"
                placeholder="请输入内容"
                size="small"
                @change="onEdit(scope.$index, scope.row)"></el-input>
            <span>{{ scope.row.key }}</span>
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
import specApi from '@a/spec'

export default {
  name: 'CategoryManagement',
  components: {
    BaseCard
  },
  data() {
    return {
      page: {
        current: 1,
        size: 15,
        total: 0
      },
      formData: {
        key: null
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

      specApi.getListByPage(this.page, this.formData)
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
    onCreate() {
      this.$prompt('请输入规格', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消'
      }).then(({ value }) => {
        specApi.create(value).then(res => {
          this.$message.success(res.message)
          this.onSearch()
        })
      })
    },
    onEdit(index, row) {
      specApi.update(row)
          .then((res) => {
            this.$message.success(res.message)
          })
    },
    onDelete(id) {
      this.$confirm('是否确定删除?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        specApi.del(id)
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

.el-form-item {
  margin-bottom: unset !important;
}
</style>
