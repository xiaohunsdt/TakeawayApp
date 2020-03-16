<template>
  <el-dialog :close-on-click-modal="false" :visible.sync="dialogFormVisible" size="mini" title="编辑地址" width="600px">
    <el-form :model="formData" v-if="formData">
      <el-form-item label="地址ID" label-width="120">
        <el-input v-model="formData.id"></el-input>
      </el-form-item>
      <el-form-item label="地址" label-width="120">
        <el-input v-model.number="formData.address"></el-input>
      </el-form-item>
      <el-form-item label="详细" label-width="120">
        <el-input v-model.number="formData.detail"></el-input>
      </el-form-item>
      <el-form-item label="手机" label-width="120">
        <el-input v-model.number="formData.phone"></el-input>
      </el-form-item>
    </el-form>
    <div class="dialog-footer" slot="footer">
      <el-button @click="dialogFormVisible = false">取 消</el-button>
      <el-button @click="onEdit" type="primary">修 改</el-button>
    </div>
  </el-dialog>
</template>

<script>
  import addressApi from '@/api/address'
  import {Message} from 'element-ui'

  export default {
    name: 'AddressEditDialog',
    watch: {
      dialogFormVisible() {
        if (this.dialogFormVisible) {
          this.init()
        } else {
          this.formData = null
        }
      }
    },
    data() {
      return {
        dialogFormVisible: false,
        addressId: null,
        formData: null
      }
    },
    methods: {
      init() {
        addressApi.getAddressById(this.addressId)
          .then(res => {
            const {id, address, detail, phone} = res
            this.formData = Object.assign({id, address, detail, phone})
          })
      },
      openDialog(addressId) {
        if (!addressId) {
          Message({
            message: '错误：没有输入地址ID',
            type: 'error',
            duration: 3 * 1000
          })
          return
        }
        this.addressId = addressId
        this.dialogFormVisible = true
      },
      onEdit() {
        addressApi.updateAddress(this.formData)
          .then(res => {
            this.$message({
              message: res.message,
              type: 'success'
            })
            this.$emit('updated-address', this.formData)
          })
      }
    }
  }
</script>

<style scoped>

</style>
