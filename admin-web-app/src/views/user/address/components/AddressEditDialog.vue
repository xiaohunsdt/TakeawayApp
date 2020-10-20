<template>
  <el-dialog :close-on-click-modal="false" :visible.sync="dialogFormVisible" size="mini" title="编辑地址" width="600px">
    <el-form v-if="formData" :model="formData">
      <el-form-item label="地址ID" label-width="120">
        <el-input v-model="formData.id" disabled></el-input>
      </el-form-item>
      <el-form-item label="地址" label-width="120">
        <el-select
            v-model="formData.address"
            :loading="loading"
            :remote-method="onSearch"
            filterable
            placeholder="请输入关键词"
            remote
            reserve-keyword
            style="display: block;"
            @change="onSelect">
          <el-option
              v-for="item in addressList"
              :key="item.address"
              :label="item.address"
              :value="item.address">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="详细" label-width="120">
        <el-input v-model="formData.detail"></el-input>
      </el-form-item>
      <el-form-item label="手机" label-width="120">
        <el-input v-model="formData.phone"></el-input>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="dialogFormVisible = false">取 消</el-button>
      <el-button type="primary" @click="onEdit">修 改</el-button>
    </div>
  </el-dialog>
</template>

<script>
import addressApi from '@/api/address'
import { Message } from 'element-ui'

export default {
  name: 'AddressEditDialog',
  data() {
    return {
      dialogFormVisible: false,
      addressId: null,
      formData: null,
      addressList: [],
      loading: false
    }
  },
  watch: {
    dialogFormVisible() {
      if (this.dialogFormVisible) {
        this.init()
      } else {
        this.formData = null
      }
    }
  },
  methods: {
    init() {
      addressApi.getAddressById(this.addressId)
          .then(res => {
            const { id, address, detail, phone } = res
            this.formData = Object.assign({ id, address, detail, phone })
          })
      this.addressList = []
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
            this.$message.success(res.message)
            this.$emit('updated-address', this.formData)
          })
    },
    onSearch(query) {
      if (query !== '') {
        this.loading = true
        addressApi.searchAddress(query)
            .then(res => {
              this.addressList = res
            })
            .catch(() => {
              this.addressList = []
            })
            .finally(() => {
              this.loading = false
            })
      } else {
        this.addressList = []
      }
    },
    onSelect(address) {
      const temp = this.addressList.find(item => item.address === address)
      this.formData.address = temp.address
      this.formData.x = temp.x
      this.formData.y = temp.y
    }
  }
}
</script>

<style scoped>

</style>
