<template>
  <div class="container">
    <base-card class="container-header">
      <el-form :inline="true" :model="formData" class="demo-form-inline" size="mini">
        <el-form-item label="用户名">
          <el-input placeholder="用户名" v-model="formData.name"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button @click="onSearch" type="primary">查询</el-button>
        </el-form-item>
      </el-form>
    </base-card>
    <base-card class="container-main">
      <el-table
        :data="tableData"
        class="tb-edit"
        element-loading-text="正在加载中..."
        highlight-current-row
        stripe
        style="width: 100%"
        v-loading="listLoading">
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
  import BaseCard from '@/components/BaseCard'
  import addressApi from '@/api/address'

  export default {
    name: 'AddressManagement',
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
          name: null,
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
            this.listLoading = false
          }).catch(() => {
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
