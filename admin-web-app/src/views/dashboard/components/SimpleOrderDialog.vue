<template>
  <el-dialog
      :close-on-click-modal="false"
      :lock-scroll="false"
      :modal-append-to-body="false"
      :title="title"
      :visible.sync="dialogFormVisible"
      class="simple-order-dialog"
      size="mini"
      width="800px">
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
            <el-table
                :data="props.row.detail.orderItemList"
                :show-header="false"
                stripe
                style="width: 100%">
              <el-table-column
                  prop="goodsName">
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
                    <span>₩ {{ props.row.allPrice.toLocaleString() }}</span>
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
          label="实际金额">
        <template v-slot="scope">
          <div>₩ {{ scope.row.realPrice.toLocaleString() }}</div>
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
    </el-table>
    <el-pagination
        :current-page="page.current"
        :page-size="page.size"
        :page-sizes="[10, 50, 100]"
        :total="page.total"
        background
        layout="total, sizes, prev, pager, next, jumper"
        style="margin-top: 15px"
        @current-change="handleCurrentChange"
        @size-change="handleSizeChange">
    </el-pagination>
  </el-dialog>
</template>

<script>
import orderApi from '@/api/order'
import { formatOrderState, formatPaymentWay, formatPayState, parseTime } from '@/utils/index'

export default {
  name: 'SimpleOrderDialog',
  filters: {
    orderStateFormat: function(value) {
      return formatOrderState(value)
    },
    payStateFormat: function(value) {
      return formatPayState(value)
    }
  },
  watch: {
    dialogFormVisible() {
      if (this.dialogFormVisible) {
        this.page.current = 1
        this.getList()
      }
    }
  },
  computed: {
    title() {
      let temp = ''
      if (this.formData.orderState) {
        temp = formatOrderState(this.formData.orderState)
      } else if (this.formData.paymentWay) {
        temp = formatPaymentWay(this.formData.paymentWay)
      } else {
        temp = '全部'
      }
      return temp + '的订单'
    }
  },
  data() {
    return {
      dialogFormVisible: false,
      page: {
        current: 1,
        size: 10,
        total: 0
      },
      formData: {
        orderState: null,
        paymentWay: null,
        showDelete: 0
      },
      listLoading: false,
      tableData: []
    }
  },
  methods: {
    getList() {
      const nowStr = parseTime(new Date(), '{y}-{m}-{d}')
      const params = Object.assign({}, this.formData)
      params.startDate = nowStr
      params.endDate = nowStr

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
    openDialog(orderState, paymentWay) {
      this.formData.orderState = orderState
      this.formData.paymentWay = paymentWay
      this.dialogFormVisible = true
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

<style>
.simple-order-dialog .el-table__expanded-cell {
  padding: 10px 20px !important;
}

.simple-order-dialog .el-form-item {
  margin: unset;
}
</style>

<style scoped>
.simple-order-dialog .el-dialog {
  transition: .3s;
}
</style>
