<template>
  <div class="container">
    <base-card class="container-header">
      <el-form :inline="true" :model="formData" class="demo-form-inline" size="mini" style="max-width: 1280px">
        <el-form-item label="昵称">
          <el-input v-model="formData.nickName" placeholder="请输入昵称"></el-input>
        </el-form-item>
        <el-form-item label="订单ID">
          <el-input v-model="formData.orderId" placeholder="请输入订单ID"></el-input>
        </el-form-item>
        <el-form-item label="支付方式">
          <el-select v-model="formData.paymentWay" placeholder="选择支付方式">
            <el-option :value="null" label="所有"/>
            <el-option label="账户余额" value="BALANCE"/>
            <el-option label="通帐转帐" value="TRANSFER"/>
            <el-option label="微信支付" value="WEIXIN_PAY"/>
            <el-option label="支付宝支付" value="ALI_PAY"/>
            <el-option label="刷卡支付" value="CREDIT_CARD"/>
            <el-option label="现金支付" value="CASH"/>
          </el-select>
        </el-form-item>
        <el-form-item label="退款状态">
          <el-select v-model="formData.state" placeholder="选择订单状态">
            <el-option :value="null" label="所有"/>
            <el-option label="处理中" value="PROCESSING"/>
            <el-option label="完成" value="DONE"/>
            <el-option label="失败" value="FAILED"/>
          </el-select>
        </el-form-item>
        <el-form-item label="日期">
          <el-date-picker
            v-model="formData.formDate"
            end-placeholder="end date"
            format="yyyy-MM-dd"
            start-placeholder="start date"
            type="daterange"
            value-format="yyyy-MM-dd"/>
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
        style="width: 100%"
        @expand-change="getOrderDetail">
        <el-table-column
          align="center"
          label="Id"
          prop="id">
        </el-table-column>
        <el-table-column
          align="center"
          label="订单Id"
          prop="orderId">
        </el-table-column>
        <el-table-column
          align="center"
          label="用户"
          prop="userName">
        </el-table-column>
        <el-table-column
          align="center"
          label="订单总金额">
          <template v-slot="scope">
            <div>
              ₩ {{ scope.row.allPrice.toLocaleString() }}
              <el-tag size="mini" type="success">
                ￥ {{ (scope.row.allPrice / 1000 * 6).toFixed(2) }}
              </el-tag>
            </div>
          </template>
        </el-table-column>
        <el-table-column
          align="center"
          label="退款金额">
          <template v-slot="scope">
            <div>
              ₩ {{ scope.row.refundMoney.toLocaleString() }}
              <el-tag size="mini" type="success">
                ￥ {{ (scope.row.refundMoney / 1000 * 6).toFixed(2) }}
              </el-tag>
            </div>
          </template>
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
          label="退款状态">
          <template v-slot="scope">
            <el-tag v-if="scope.row.state === 'PROCESSING'">
              {{ scope.row.state | refundLogStateFormat }}
            </el-tag>
            <el-tag v-if="scope.row.state === 'DONE'" type="success">
              {{ scope.row.state | refundLogStateFormat }}
            </el-tag>
            <el-tag v-if="scope.row.state === 'FAILED'" type="danger">
              {{ scope.row.state | refundLogStateFormat }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column
          align="center"
          label="创建时间"
          prop="createDate">
        </el-table-column>
        <el-table-column
          align="center"
          label="操作"
          width="170">
          <template v-slot="scope">
            <div class="action-btns">
              <el-button
                v-if="scope.row.state==='PROCESSING'"
                size="mini"
                type="success"
                @click="onFinish(scope.row)">
                完成
              </el-button>
              <el-button
                v-if="scope.row.state==='PROCESSING'"
                size="mini"
                type="danger"
                @click="onReject(scope.row)">
                拒绝
              </el-button>
            </div>
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
    <edit-order-dialog ref="edit-order-dialog"/>
  </div>
</template>

<script>
import BaseCard from '@/components/BaseCard'
import EditOrderDialog from './components/EditOrderDialog'
import { formatPaymentWay, formatRefundLogState, parseTime } from '@u/index'
import refundLogApi from '@a/refund-log'

export default {
  name: 'ExpressOrder',
  filters: {
    paymentWayFormat: function(value) {
      return formatPaymentWay(value)
    },
    refundLogStateFormat: function(value) {
      return formatRefundLogState(value)
    }
  },
  components: {
    BaseCard,
    EditOrderDialog
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
        orderId: null,
        paymentWay: null,
        state: null,
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
    getList() {
      this.listLoading = true

      const params = Object.assign({}, this.formData)
      params.startDate = parseTime(params.formDate[0], '{y}-{m}-{d}')
      params.endDate = parseTime(params.formDate[1], '{y}-{m}-{d}')

      refundLogApi.getLogListByPage(this.page, params)
        .then(response => {
          const datas = response.records
          datas.forEach(item => {
            item.detail = {}
          })
          this.tableData = datas
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
    handleSizeChange(val) {
      this.page.size = val
      this.getList()
    },
    handleCurrentChange(val) {
      this.page.current = val
      this.getList()
    },
    onFinish(refundLog) {
      refundLogApi.doneRefund(refundLog.id).then(res => {
        this.$message.success(res.message)
        refundLog.state = 'DONE'
      })
    },
    onReject(refundLog) {
      refundLogApi.rejectRefund(refundLog.id).then(res => {
        this.$message.success(res.message)
        refundLog.state = 'FAILED'
      })
    }
  }
}
</script>

<style lang="scss">
.el-table__expanded-cell {
  padding: 20px 25px !important;
}

.order-expand {
  font-size: 0;

  form {
    label {
      width: 90px;
      color: #99a9bf;
    }

    .el-form-item {
      margin-right: 0;
      margin-bottom: 0;
    }

    .el-form-item__label {
      line-height: 30px;
    }

    .el-form-item__content {
      margin-left: 90px;
      line-height: 30px;
    }
  }

  &-form {
    padding: 0px 10px;
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
}

.action-btns {
  button {
    margin-bottom: 5px;
  }
}
</style>
