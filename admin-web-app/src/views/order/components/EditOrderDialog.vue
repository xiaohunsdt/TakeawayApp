<template>
  <el-dialog
    :close-on-click-modal="false"
    :visible.sync="dialogFormVisible"
    size="mini"
    title="编辑订单"
    v-loading="formData===null"
    width="400px">
    <el-form :model="formData" v-if="formData!==null">
      <el-form-item label="订单ID" label-width="80">
        <el-input :disabled="true" v-model="formData.id"></el-input>
      </el-form-item>
      <el-form-item label="折扣" label-width="80">
        <el-input type="number" v-model.number="formData.discount"></el-input>
      </el-form-item>
      <el-form-item label="备注" label-width="80">
        <el-input :rows="3" type="textarea" v-model="formData.ps"></el-input>
      </el-form-item>
    </el-form>
    <div class="dialog-footer" slot="footer">
      <el-button @click="dialogFormVisible = false">取 消</el-button>
      <el-button @click="editOrder" type="primary">编 辑</el-button>
    </div>
  </el-dialog>
</template>

<script>
  import orderApi from '@/api/order'

  export default {
    name: 'EditOrderDialog',
    data() {
      return {
        dialogFormVisible: false,
        formData: null
      }
    },
    methods: {
      init(orderId) {
        this.formData = null
        orderApi.getOrderDetail(orderId)
          .then(res => {
            this.formData = {
              id: res.id,
              discount: res.discount,
              ps: res.ps
            }
          })
      },
      openDialog(orderId) {
        this.init(orderId)
        this.dialogFormVisible = true
      },
      editOrder() {
        orderApi.editOrder(this.formData)
          .then(res => {
            this.$message({
              message: res.message,
              type: 'success'
            })
          })
      }
    }
  }
</script>

<style scoped>

</style>
