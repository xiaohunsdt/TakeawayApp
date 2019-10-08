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
          prop="number"
          label="编号">
        </el-table-column>
        <el-table-column
          align="center"
          prop="user.name"
          label="用户">
        </el-table-column>
        <el-table-column
          align="center"
          prop="goodsCount"
          label="商品数量">
        </el-table-column>
        <el-table-column
          align="center"
          prop="allPrice"
          label="总金额">
        </el-table-column>
        <el-table-column
          align="center"
          label="折扣/抵扣金额">
          <template v-slot="scope">
            <div v-if="scope.row.discount !==''">{{ scope.row.discount }}</div>
            <div>₩ {{ scope.row.discountedPrices }}</div>
          </template>
        </el-table-column>
        <el-table-column
          align="center"
          prop="realPrice"
          label="实际金额">
        </el-table-column>
        <el-table-column
          align="center"
          prop="paymentWay"
          label="支付方式">
        </el-table-column>
        <el-table-column
          align="center"
          prop="payState"
          label="支付状态">
        </el-table-column>
        <el-table-column
          align="center"
          prop="orderState"
          label="订单状态">
        </el-table-column>
        <el-table-column
          align="center"
          prop="createDate"
          label="创建时间">
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
  import orderApi from '@/api/order'

  export default {
    name: 'OrderManagement',
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
      onSearch() {
        this.listLoading = true
        orderApi.getOrderListByPage(this.page, this.formData)
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
        this.onSearch()
      },
      handleCurrentChange(val) {
        this.page.current = val
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
</style>
