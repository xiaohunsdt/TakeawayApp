<template>
  <div class="container">
    <base-card class="container-header">
      <el-form :inline="true" :model="formData" class="demo-form-inline" size="mini">
        <el-form-item label="优惠卷Id">
          <el-input placeholder="请输入优惠卷Id" v-model="formData.couponId"></el-input>
        </el-form-item>
        <el-form-item label="昵称">
          <el-input placeholder="请输入昵称" v-model="formData.nickName"></el-input>
        </el-form-item>
        <el-form-item label="订单Id">
          <el-input placeholder="请输入订单Id" v-model="formData.orderId"></el-input>
        </el-form-item>
        <el-form-item label="日期">
          <el-date-picker
            end-placeholder="end date"
            format="yyyy-MM-dd"
            start-placeholder="start date"
            type="daterange"
            v-model="formData.formDate"
            value-format="yyyy-MM-dd"/>
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
          label="优惠卷Id"
          prop="couponId">
        </el-table-column>
        <el-table-column
          align="center"
          label="用户昵称"
          prop="nickName">
        </el-table-column>
        <el-table-column
          align="center"
          label="订单Id"
          prop="orderId">
        </el-table-column>
        <el-table-column
          align="center"
          label="订单原价格">
          <template v-slot="scope">
            <div>₩ {{ scope.row.orderOriginalAmount.toLocaleString() }}</div>
          </template>
        </el-table-column>
        <el-table-column
          align="center"
          label="优惠金额">
          <template v-slot="scope">
            <div>₩ {{ scope.row.couponAmount.toLocaleString() }}</div>
          </template>
        </el-table-column>
        <el-table-column
          align="center"
          label="优惠后价格">
          <template v-slot="scope">
            <div>₩ {{ scope.row.orderFinalAmount.toLocaleString() }}</div>
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
  import couponApi from '@/api/coupon'
  import { parseTime } from '@/utils/index'

  export default {
    name: 'CouponLogManagement',
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
          nickName: '',
          formDate: [
            new Date(),
            new Date()
          ]
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

        const params = Object.assign({}, this.formData)
        params.startDate = parseTime(params.formDate[0], '{y}-{m}-{d}')
        params.endDate = parseTime(params.formDate[1], '{y}-{m}-{d}')

        couponApi.getCouponLogListByPage(this.page, params)
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

  .el-form-item {
    margin-bottom: unset !important;
  }
</style>
