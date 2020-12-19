<template>
  <el-dialog
    v-loading="formData===null"
    :close-on-click-modal="false"
    :visible.sync="dialogFormVisible"
    title="退款申请"
    width="400px">
    <el-form ref="refund-submit-form" :model="formData" :rules="rules" size="mini" status-icon>
      <el-form-item label-width="80" prop="money">
        <span slot="label">
          退款金額 <el-tag size="mini" type="warning">最多只能退 <span style="color: blue">{{ canRefundMoney }}</span>韩币</el-tag>
        </span>
        <el-input v-model.number="formData.money" type="number"></el-input>
      </el-form-item>
      <el-form-item label="退款备注" label-width="80">
        <el-input v-model="formData.refundRes"></el-input>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button size="mini" @click="dialogFormVisible = false">取消</el-button>
      <el-button :loading="loading" size="mini" type="primary" @click="onSubmit">提交</el-button>
    </div>
  </el-dialog>
</template>

<script>
import refundLogApi from '@a/refund-log'
import orderApi from '@a/order'

let g_resolve = null
let g_reject = null

export default {
  name: 'RefundSubmitDialog',
  data() {
    return {
      loading: false,
      dialogFormVisible: false,
      formData: {
        money: 0,
        refundRes: ''
      },
      rules: {
        money: [
          { type: 'number', max: this.canRefundMoney, required: true, message: '超出最大退款金额限制', trigger: 'change' },
          { type: 'number', min: 1, message: '退款金额必须大于0', trigger: 'change' }
        ]
      },
      order: null,
      canRefundMoney: 0
    }
  },
  watch: {
    canRefundMoney: function(newVal) {
      this.rules.money[0].max = newVal
    }
  },
  methods: {
    init(order) {
      this.canRefundMoney = 0
      this.order = order
      this.formData = {
        money: 0,
        refundRes: ''
      }

      refundLogApi.getAllRefundMoneyByOrderId(this.order.id).then(res => {
        this.canRefundMoney = this.order.realPrice - parseInt(res.message)
      })
    },
    show(orderId, resolve, reject) {
      g_resolve = resolve
      g_reject = reject
      this.init(orderId)
      this.dialogFormVisible = true
    },
    onSubmit() {
      this.$refs['refund-submit-form'].validate((valid) => {
        if (valid) {
          this.loading = true
          orderApi.refundOrder(this.order.id, this.formData.money, this.formData.refundRes)
            .then(res => {
              let orderState
              if (this.canRefundMoney === this.formData.money) {
                orderState = 'REFUND'
              } else {
                orderState = 'PART_REFUND'
              }
              this.dialogFormVisible = false
              g_resolve({ res, orderState })
            })
            .catch(res => {
              g_reject(res)
            })
            .finally(() => {
              this.loading = false
            })
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
