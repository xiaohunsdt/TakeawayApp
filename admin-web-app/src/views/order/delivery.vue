<template>
  <div class="container">
    <base-card class="container-header">
      <el-form :inline="true" :model="formData" class="demo-form-inline" size="mini">
        <el-form-item label="配送员">
          <el-input v-model="formData.adminName" placeholder="配送员的账号"></el-input>
        </el-form-item>
        <el-form-item label="支付方式">
          <el-select v-model="formData.paymentWay" placeholder="选择订单支付方式">
            <el-option :value="null" label="所有"/>
            <el-option label="账户余额" value="BALANCE"/>
            <el-option label="通帐转帐" value="TRANSFER"/>
            <el-option label="微信支付" value="WEIXIN_PAY"/>
            <el-option label="支付宝支付" value="ALI_PAY"/>
            <el-option label="刷卡支付" value="CREDIT_CARD"/>
            <el-option label="现金支付" value="CASH"/>
          </el-select>
        </el-form-item>
        <el-form-item label="日期">
          <el-date-picker
            v-model="formData.formDate"
            end-placeholder="end date"
            format="yyyy-MM-dd"
            start-placeholder="start date"
            type="daterange"
            value-format="yyyy-MM-dd">
          </el-date-picker>
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
          label="管理员"
          prop="adminName">
        </el-table-column>
        <el-table-column
          align="center"
          label="订单编号"
          prop="number">
        </el-table-column>
        <el-table-column
          align="center"
          label="支付方式">
          <template v-slot="scope">
            <el-tag>{{ scope.row.paymentWay | paymentWayFormat }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column
          align="center"
          label="金额">
          <template v-slot="scope">
            {{ scope.row.money && scope.row.money.toLocaleString() }}
          </template>
        </el-table-column>
        <el-table-column
          align="center"
          label="订单日期"
          prop="orderCreateDate"
          width="100">
        </el-table-column>
        <el-table-column
          align="center"
          label="配送日期"
          prop="createDate"
          width="100">
        </el-table-column>
        <el-table-column
          align="center"
          label="订单完成时间">
          <template v-slot="props">
            <el-tag :type="props.row.orderFinishMinute!=='未完成'?'success':'warning'">{{ props.row.orderFinishMinute }}
              {{ props.row.orderFinishMinute !== '未完成' ? '分钟' : '' }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column
          align="center"
          label="配送完成时间">
          <template v-slot="props">
            <el-tag :type="props.row.deliveryFinishMinute!=='未完成'?'success':'warning'">
              {{ props.row.deliveryFinishMinute }} {{ props.row.deliveryFinishMinute !== '未完成' ? ' 分钟' : '' }}
            </el-tag>
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
import BaseCard from '@/components/BaseCard'
import deliveryApi from '@/api/delivery'

import { formatPaymentWay, parseTime } from '@/utils/index'

export default {
  name: 'CommentManagement',
  components: {
    BaseCard
  },
  filters: {
    paymentWayFormat: function(value) {
      return formatPaymentWay(value)
    }
  },
  data() {
    return {
      page: {
        current: 1,
        size: 15,
        total: 0
      },
      formData: {
        adminName: null,
        paymentWay: null,
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
      this.page.current = 1
      this.getList()
    },
    getList() {
      this.listLoading = true

      const params = Object.assign({}, this.formData)
      params.startDate = parseTime(params.formDate[0], '{y}-{m}-{d}')
      params.endDate = parseTime(params.formDate[1], '{y}-{m}-{d}')

      deliveryApi.getDeliveryListByPage(this.page, params)
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
