<template>
  <div class="container">
    <base-card class="container-header">
      <el-form :inline="true" :model="formData" class="demo-form-inline" size="mini">
        <el-form-item label="ID">
          <el-input v-model="formData.userId" placeholder="用户ID"></el-input>
        </el-form-item>
        <el-form-item label="昵称">
          <el-input v-model="formData.nickName" placeholder="昵称"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSearch">查询</el-button>
        </el-form-item>
      </el-form>
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
            label="用户名">
          <template v-slot="scope">
            <div v-if="scope.row.user.name !==''">{{ scope.row.user.name }}</div>
            <div>{{ scope.row.user.nickName }}</div>
          </template>
        </el-table-column>
        <el-table-column
            align="center"
            label="地址">
          <template v-slot="scope">
            <div>{{ scope.row.address }}</div>
          </template>
        </el-table-column>
        <el-table-column
            align="center"
            label="详细">
          <template v-slot="scope">
            <div>{{ scope.row.detail }}</div>
          </template>
        </el-table-column>
        <el-table-column
            align="center"
            label="手机">
          <template v-slot="scope">
            <div>{{ scope.row.phone }}</div>
          </template>
        </el-table-column>
        <el-table-column
            align="center"
            label="是否默认">
          <template v-slot="scope">
            <div>{{ scope.row.isDefault }}</div>
          </template>
        </el-table-column>
        <el-table-column
            label="操作">
          <template v-slot="scope">
            <el-button size="mini" type="primary" @click="onEdit(scope.row.id)">编辑</el-button>
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
    <address-edit-dialog ref="address-edit-dialog" @updated-address="onUpdatedAddress"/>
  </div>
</template>

<script>
import BaseCard from '@/components/BaseCard'
import AddressEditDialog from './components/AddressEditDialog'
import addressApi from '@/api/address'

export default {
  name: 'AddressManagement',
  components: {
    BaseCard,
    AddressEditDialog
  },
  data() {
    return {
      page: {
        current: 1,
        size: 15,
        total: 0
      },
      formData: {
        nickName: null,
        categoryId: null
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

      addressApi.getAddressListByPage(this.page, this.formData)
          .then(response => {
            this.tableData = response.records
            this.page.total = parseInt(response.total)
          })
          .finally(() => {
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
    onEdit(addressId) {
      this.$refs['address-edit-dialog'].openDialog(addressId)
    },
    onUpdatedAddress(address) {
      const index = this.tableData.findIndex(item => item.id === address.id)
      if (index > -1) {
        this.tableData[index] = Object.assign(this.tableData[index], address)
      }
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
