<template>
  <el-dialog
      :close-on-click-modal="false"
      :title="storeId?'店铺详情':'生成店铺'"
      :visible.sync="dialogFormVisible"
      size="mini"
      width="400px">
    <el-form :model="formData">
      <el-form-item v-if="storeId" label="店铺ID" label-width="70px">
        <el-input v-model="formData.id" disabled></el-input>
      </el-form-item>
      <el-form-item label="店铺名" label-width="70px">
        <el-input v-model="formData.name" :disabled="storeId!==null && storeId!==undefined"></el-input>
      </el-form-item>
      <el-form-item label="支付方式" label-width="70px">
        <el-select v-model="formData.paymentWay" placeholder="请选择关键字">
          <el-option label="免费" value="FREE"/>
          <el-option label="按量计费" value="PAY_AS_YOU_GO"/>
          <el-option label="包月" value="MONTH"/>
        </el-select>
      </el-form-item>
      <el-form-item label="到期时间" label-width="70px">
        <el-date-picker type="date" placeholder="选择日期" v-model="formData.expireDate" :disabled="formData.paymentWay !== 'MONTH'" value-format="yyyy-MM-dd"/>
      </el-form-item>
      <el-form-item label="状态" label-width="70px">
        <el-select v-model="formData.state" placeholder="请输入关键词">
          <el-option label="停止" value="OFF"/>
          <el-option label="正常" value="ON"/>
        </el-select>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="dialogFormVisible = false">取 消</el-button>
      <el-button v-if="storeId" type="primary" @click="onEdit">修 改</el-button>
      <el-button v-else type="success" @click="onSave">生 成</el-button>
    </div>
  </el-dialog>
</template>

<script>
import storeApi from '@/api/store'
import permission from '@/directive/permission/index.js'

export default {
  name: 'StoreDialog',
  directives: { permission },
  data() {
    return {
      dialogFormVisible: false,
      storeId: null,
      formData: {
        id: null,
        name: null,
        paymentWay: null,
        expireDate: null,
        state: null
      },
      loading: false
    }
  },
  watch: {
    dialogFormVisible() {
      if (this.dialogFormVisible) {
        this.init()
      } else {
        this.formData = this.$options.data().formData
      }
    }
  },
  methods: {
    init() {
      if (!this.storeId) {
        return
      }
      this.loading = true
      storeApi.getStoreById(this.storeId)
          .then(res => {
            this.formData = res
            this.loading = false
          })
          .catch(res => {
            this.loading = false
          })
    },
    openDialog(storeId) {
      this.storeId = storeId
      this.dialogFormVisible = true
    },
    onEdit() {
      storeApi.update(this.formData)
          .then(res => {
            this.$message({
              message: res.message,
              type: 'success'
            })
            this.$emit('updated-store', this.formData)
          })
    },
    onSave() {
      storeApi.create(this.formData)
          .then(res => {
            this.$message({
              message: res.message,
              type: 'success'
            })
            this.$emit('updated-store', this.formData)
          })
    }
  }
}
</script>

<style scoped>

</style>
