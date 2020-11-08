<template>
  <el-dialog :close-on-click-modal="false" :show-close="false" :visible.sync="dialogVisible" label-width="100px"
             style="text-align: left;" width="500px">
    <template v-slot:title>
      <h3>提现申请</h3>
    </template>
    <div style="font-size: 18px;font-weight: bolder;margin-bottom: 12px">可提现余额: {{ balance.toLocaleString() }}</div>
    <el-form :model="formData" label-position="left" label-width="80px" size="small" style="width: 100%">
      <el-form-item label="提现金额">
        <el-input v-model.number="formData.money" placeholder="请输入提现金额" type="number"></el-input>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="closeWindow">取 消</el-button>
      <el-button v-loading.fullscreen.lock="sendLoading" type="primary" @click="handleApply">
        申请
      </el-button>
    </div>
  </el-dialog>
</template>

<script>
import balanceApi from '@a/balance'
import { mapGetters } from 'vuex'

export default {
  name: 'ApplyWithdrawDialog',
  data() {
    return {
      dialogVisible: false,
      sendLoading: false,
      balance: 0,
      formData: {
        money: 0
      }
    }
  },
  computed: {
    ...mapGetters([
      'userData'
    ])
  },
  methods: {
    async openWindow() {
      this.dialogVisible = true
      this.sendLoading = true
      balanceApi.getMyBalance()
        .then(res => {
          this.balance = parseInt(res.money)
        })
        .finally(() => {
          this.sendLoading = false
        })
    },
    closeWindow() {
      this.dialogVisible = false
      this.$nextTick(() => {
        this.formData.money = 0
      })
    },
    handleApply() {
      balanceApi.applyWithdraw(this.formData.money)
        .then(res => {
          this.$message.success(res.message)
          this.closeWindow()
          this.$emit('apply-success')
        })
        .finally(() => {
          this.sendLoading = false
        })
    }
  }
}
</script>

<style scoped>

</style>
