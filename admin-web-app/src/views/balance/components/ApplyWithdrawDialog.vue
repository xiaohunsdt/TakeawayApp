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
      <el-form-item label="通帐银行">
        <el-select v-model="formData.bankName" placeholder="请选择银行">
          <el-option value="농협">농협</el-option>
          <el-option value="SC제일은행">SC제일은행</el-option>
          <el-option value="국민은행">국민은행</el-option>
          <el-option value="신한은행">신한은행</el-option>
          <el-option value="우리은행">우리은행</el-option>
          <el-option value="기업은행">기업은행</el-option>
          <el-option value="하나은행">하나은행</el-option>
          <el-option value="하나외환은행">하나외환은행</el-option>
          <el-option value="우체국">우체국</el-option>
          <el-option value="대구은행">대구은행</el-option>
          <el-option value="부산은행">부산은행</el-option>
          <el-option value="광주은행">광주은행</el-option>
          <el-option value="전북은행">전북은행</el-option>
          <el-option value="제주은행">제주은행</el-option>
          <el-option value="경남은행">경남은행</el-option>
          <el-option value="새마을금고">새마을금고</el-option>
          <el-option value="한국시티은행">한국시티은행</el-option>
          <el-option value="수협">수협</el-option>
          <el-option value="신협">신협</el-option>
          <el-option value="NH농협증권">NH농협증권</el-option>
          <el-option value="대우증권">대우증권</el-option>
          <el-option value="상호저축은행">상호저축은행</el-option>
          <el-option value="유안타증권(구 동양)">유안타증권(구 동양)</el-option>
          <el-option value="케이뱅크">케이뱅크</el-option>
          <el-option value="카카오뱅크">카카오뱅크</el-option>
          <el-option value="삼성증권">삼성증권</el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="通帐账号">
        <el-input v-model="formData.account"></el-input>
      </el-form-item>
      <el-form-item label="账号户名">
        <el-input v-model="formData.accountName"></el-input>
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
        money: 0,
        bankName: '',
        accountName: '',
        account: ''
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
        this.formData = {
          money: 0,
          bankName: '',
          accountName: '',
          account: ''
        }
      })
    },
    handleApply() {
      balanceApi.applyWithdraw(this.formData)
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
