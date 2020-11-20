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
        <el-form-item label="单号">
          <el-input v-model="formData.number" placeholder="请输入单号"></el-input>
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
        <el-form-item label="订单状态">
          <el-select v-model="formData.orderState" placeholder="选择订单状态">
            <el-option :value="null" label="所有"/>
            <el-option label="等待接单" value="WAITING_RECEIVE"/>
            <el-option label="生产中" value="PRODUCING"/>
            <el-option label="配送中" value="DELIVERING"/>
            <el-option label="已完成" value="FINISHED"/>
            <el-option label="退款" value="REFUND"/>
            <el-option label="过期" value="EXPIRED"/>
          </el-select>
        </el-form-item>
        <el-form-item label="显示删除">
          <el-select v-model="formData.showDelete" placeholder="是否显示删除的订单">
            <el-option :value="0" label="不显示"/>
            <el-option :value="1" label="显示"/>
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
        <el-table-column type="expand">
          <template v-slot="props">
            <div v-if="props.row.detail.hasOwnProperty('address')" class="order-expand">
              <base-card>
                <el-table :data="props.row.detail.orderItemList" :show-header="false" stripe style="width: 100%">
                  <el-table-column>
                    <template v-slot="scope">
                      {{scope.row.produceName}}
                      <el-tag v-if="scope.row.goodsTitle!==''" size="mini" effect="dark">{{scope.row.goodsTitle}}</el-tag>
                    </template>
                  </el-table-column>
                  <el-table-column>
                    <template v-slot="scope">
                      <img
                          v-if="scope.row.goodsThumb!==''"
                          :src="$VUE_APP_BASE_API + scope.row.goodsThumb"
                          style="height: 30px;width: auto;"/>
                    </template>
                  </el-table-column>
                  <el-table-column>
                    <template v-slot="scope">
                      ₩ {{ scope.row.goodsPrice.toLocaleString() }}
                    </template>
                  </el-table-column>
                  <el-table-column>
                    <template v-slot="scope">
                      x {{ scope.row.goodsCount }}
                    </template>
                  </el-table-column>
                </el-table>
              </base-card>
              <base-card>
                <el-form class="order-expand-form" label-position="left">
                  <el-row>
                    <el-col :span="12">
                      <el-form-item label="订单 ID">
                        <span>{{ props.row.id }}</span>
                      </el-form-item>
                      <el-form-item label="用户 ID">
                        <span>{{ props.row.userId }}</span>
                      </el-form-item>
                      <el-form-item label="总金额">
                        <span>₩ {{ props.row.allPrice.toLocaleString() }}</span>&nbsp;
                        <span v-if="props.row.deliveryPrice > 0">(配送费:₩ {{
                            props.row.deliveryPrice.toLocaleString()
                          }})</span>
                      </el-form-item>
                      <el-form-item label="优惠">
                        <span>₩ {{ props.row.discountedPrices.toLocaleString() }}</span>
                        <span v-if="props.row.discount !=''">({{ props.row.discount }}折)</span>
                      </el-form-item>
                      <el-form-item v-if="props.row.ps!==''" label="备注">
                        <span>{{ props.row.ps }}</span>
                      </el-form-item>
                    </el-col>
                    <el-col :span="12">
                      <el-form-item label="地址">
                        <div>
                          <div>{{ props.row.detail.address.address }}</div>
                          <div>{{ props.row.detail.address.detail }}</div>
                        </div>
                      </el-form-item>
                      <el-form-item label="联系方式">
                        <span>{{ props.row.detail.address.phone }}</span>
                      </el-form-item>
                    </el-col>
                  </el-row>
                </el-form>
              </base-card>
            </div>
          </template>
        </el-table-column>
        <el-table-column
            align="center"
            label="单号"
            prop="number"
            width="60">
        </el-table-column>
        <el-table-column
            align="center"
            label="用户">
          <template v-slot="props">
            <div>{{ props.row.userName }}</div>
          </template>
        </el-table-column>
        <el-table-column
            align="center"
            label="商品数量"
            prop="goodsCount"
            width="80">
        </el-table-column>
        <el-table-column
            align="center"
            label="总金额"
            prop="allPrice">
          <template v-slot="scope">
            <div>₩ {{ scope.row.allPrice.toLocaleString() }}</div>
          </template>
        </el-table-column>
        <el-table-column
            align="center"
            label="折扣/抵扣金额"
            width="160">
          <template v-slot="scope">
            <div v-if="scope.row.discount > 0">{{ scope.row.discount }}折</div>
            <div v-if="scope.row.discountedPrices > 0">₩ {{ scope.row.discountedPrices.toLocaleString() }}</div>
          </template>
        </el-table-column>
        <el-table-column
            align="center"
            label="实际金额">
          <template v-slot="scope">
            <div>₩ {{ scope.row.realPrice.toLocaleString() }}</div>
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
            label="支付状态">
          <template v-slot="scope">
            <el-tag v-if="scope.row.payState === 'PAID'" type="success">
              {{ scope.row.payState | payStateFormat }}
            </el-tag>
            <el-tag v-if="scope.row.payState === 'PAY_LATER'" type="warning">
              {{ scope.row.payState | payStateFormat }}
            </el-tag>
            <el-tag v-if="scope.row.payState === 'UN_PAY'" type="danger">
              {{ scope.row.payState | payStateFormat }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column
            align="center"
            label="订单状态">
          <template v-slot="scope">
            <el-tag v-if="scope.row.orderState === 'FINISHED'" type="success">
              {{ scope.row.orderState | orderStateFormat }}
            </el-tag>
            <el-tag v-else-if="scope.row.orderState === 'REFUND'" type="danger">
              {{ scope.row.orderState | orderStateFormat }}
            </el-tag>
            <el-tag v-else-if="scope.row.orderState === 'EXPIRED'" type="info">
              {{ scope.row.orderState | orderStateFormat }}
            </el-tag>
            <el-tag v-else>
              {{ scope.row.orderState | orderStateFormat }}
            </el-tag>
            <el-tag v-if="scope.row.deleted === 1" type="info">
              已删除
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
                  v-if="scope.row.orderState==='WAITING_RECEIVE' && scope.row.payState!=='PAID'"
                  size="mini"
                  type="primary"
                  @click="onEditOrder(scope.row.id)">编辑
              </el-button>
              <el-button
                  v-if="(scope.row.paymentWay==='WEIXIN_PAY'||scope.row.paymentWay==='TRANSFER'||scope.row.paymentWay==='ALI_PAY') && scope.row.payState==='UN_PAY' && scope.row.orderState==='WAITING_RECEIVE'"
                  size="mini"
                  type="success"
                  @click="onConfirmPay(scope.row)">
                确认收款
              </el-button>
              <el-button
                  v-if="scope.row.payState!=='UN_PAY' && (scope.row.orderState==='PRODUCING' || scope.row.orderState==='DELIVERING')"
                  size="mini"
                  type="success"
                  @click="onPrintOrder(scope.row)">
                打印
              </el-button>
              <el-button
                  v-if="scope.row.orderState==='WAITING_RECEIVE' && scope.row.payState!=='UN_PAY'"
                  size="mini"
                  type="success"
                  @click="onReceiveOrder(scope.row)">接单
              </el-button>
              <el-button
                  v-if="scope.row.orderState==='PRODUCING'"
                  size="mini"
                  type="success"
                  @click="onDeliveryOrder(scope.row)">配送
              </el-button>
              <el-button
                  v-if="scope.row.orderState==='DELIVERING'"
                  size="mini"
                  type="success"
                  @click="onFinishOrder(scope.row)">完成
              </el-button>
              <el-button
                  v-if="scope.row.orderState!=='EXPIRED' && scope.row.orderState!=='REFUND' && (scope.row.payState==='PAID' || (scope.row.payState==='PAY_LATER' && scope.row.orderState==='FINISHED'))"
                  size="mini"
                  type="danger"
                  @click="onRefundOrder(scope.row)">
                退款
              </el-button>
              <el-button
                  v-if="(scope.row.orderState==='EXPIRED' || scope.row.orderState==='REFUND') && scope.row.deleted === 0"
                  size="mini"
                  type="danger"
                  @click="onDeleteOrder(scope.row)">
                删除
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
import orderApi from '@/api/order'
import { formatOrderState, formatPaymentWay, formatPayState, parseTime } from '@/utils/index'

export default {
  name: 'ExpressOrder',
  filters: {
    orderStateFormat: function(value) {
      return formatOrderState(value)
    },
    payStateFormat: function(value) {
      return formatPayState(value)
    },
    paymentWayFormat: function(value) {
      return formatPaymentWay(value)
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
        number: null,
        orderId: null,
        paymentWay: null,
        orderState: null,
        showDelete: 0,
        orderType: 'EXPRESS',
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

      orderApi.getOrderListByPage(this.page, params)
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
    async getOrderDetail(row, expandedRows) {
      const currentRow = expandedRows.find(item => item.id === row.id)
      // if (currentRow !== undefined && !currentRow.hasOwnProperty('detail')) {
      if (currentRow !== undefined) {
        await orderApi.getOrderDetail(row.id)
            .then(response => {
              this.$set(currentRow, 'detail', response)
            })
      }
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
    onEditOrder(orderId) {
      this.$refs['edit-order-dialog'].openDialog(orderId)
    },
    onPrintOrder(order) {
      const loading = this.$loading({
        lock: true,
        text: '打印中...',
        spinner: 'el-icon-loading',
        background: 'rgba(0, 0, 0, 0.7)'
      })
      orderApi.printOrder(order)
          .then(res => {
            this.$message.success('打印成功')
            // order.payState = 'PAID'
          })
          .finally(() => {
            loading.close()
          })
    },
    onConfirmPay(order) {
      this.$confirm('确定此账户已付款?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        orderApi.confirmPay(order.id)
            .then(res => {
              this.$message.success(res.message)
              order.payState = 'PAID'
            })
      })
    },
    onReceiveOrder(order) {
      orderApi.receiveOrder(order.id)
          .then(res => {
            this.$message.success(res.message)
            order.orderState = 'PRODUCING'
          })
    },
    onDeliveryOrder(order) {
      orderApi.deliveryOrder(order.id)
          .then(res => {
            this.$message.success(res.message)
            order.orderState = 'DELIVERING'
          })
    },
    onFinishOrder(order) {
      this.$confirm('确定当前订单已完成?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        orderApi.finishOrder(order.id)
            .then(res => {
              this.$message.success(res.message)
              order.orderState = 'FINISHED'
            })
      })
    },
    onRefundOrder(order) {
      this.$confirm('确定要退款吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        orderApi.refundOrder(order.id)
            .then(res => {
              this.$message.success(res.message)
              order.orderState = 'REFUND'
            })
      })
    },
    onDeleteOrder(order) {
      this.$confirm('确定要删除这个订单吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        orderApi.deleteOrder(order.id)
            .then(res => {
              this.$message.success(res.message)
              this.getList()
            })
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
