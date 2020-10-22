<template>
  <div class="container">
    <base-card class="container-header">
      <el-row>
        <el-col :span="18">
          <el-form :inline="true" :model="formData" class="demo-form-inline" size="mini">
            <el-form-item label="级别">
              <el-input v-model="formData.name"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="onSearch">查询</el-button>
            </el-form-item>
          </el-form>
        </el-col>
        <el-col :span="6" style="text-align: right">
          <el-button size="mini" type="success" @click="onCreate">添加新店铺</el-button>
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
            align="center"
            label="ID"
            prop="id"
            width="200"/>
        <el-table-column
            align="center"
            label="名称"
            prop="name"
            width="200"/>
        <el-table-column
            align="center"
            label="收费方式"
            prop="paymentWay"
            width="200"/>
        <el-table-column
          align="center"
          label="余额"
          prop="balance"
          width="200"/>
        <el-table-column
            align="center"
            label="到期时间"
            prop="expireDate"
            width="200"/>
        <el-table-column
            align="center"
            label="状态"
            prop="state"
            width="200"/>
        <el-table-column
            align="center"
            label="创建时间"
            prop="createDate"/>
        <el-table-column
            align="center"
            label="操作">
          <template v-slot="props">
            <el-button size="mini" type="primary" @click="onEdit(props.row.id)">编辑</el-button>
            <el-button size="mini" type="primary" @click="onCreateAdmin(props.row.id)">添加管理员</el-button>
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
    <store-dialog ref="store-dialog" @updated-store="onUpdatedStore"/>
    <admin-dialog ref="admin-dialog"/>
  </div>
</template>

<script>
import BaseCard from '@/components/BaseCard'
import AdminDialog from '../admin/components/AdminDialog'
import StoreDialog from './components/StoreDialog'
import storeApi from '@/api/store'

export default {
  name: 'StoreManagement',
  components: {
    BaseCard,
    AdminDialog,
    StoreDialog
  },
  data() {
    return {
      page: {
        current: 1,
        size: 15,
        total: 0
      },
      formData: {
        type: null,
        state: null
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
      storeApi.getListByPage(this.page, this.formData)
          .then(response => {
            this.tableData = response.records
            this.page.total = parseInt(response.total)
            this.listLoading = false
          })
          .catch(() => {
            this.listLoading = false
          })
    },
    handleSizeChange(val) {
      this.page.size = val
      this.getList()
    },
    handleCurrentChange(val) {
      this.page.current = val
      this.getList()
    },
    onSearch() {
      this.page.current = 1
      this.getList()
    },
    onCreate() {
      this.$refs['store-dialog'].openDialog()
    },
    onEdit(id) {
      this.$refs['store-dialog'].openDialog(id)
    },
    onCreateAdmin(id) {
      this.$refs['admin-dialog'].openDialog(null, id)
    },
    onUpdatedStore() {
      this.onSearch()
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
}

.el-form-item {
  margin-bottom: unset !important;
}
</style>
