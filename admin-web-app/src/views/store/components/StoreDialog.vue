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
      <el-form-item label="店铺地址">
        <el-select
            v-model="formData.address"
            :loading="searchLoading"
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
      <el-form-item label="地址经度">
        <el-input v-model="formData.x" disabled></el-input>
      </el-form-item>
      <el-form-item label="地址纬度">
        <el-input v-model="formData.y" disabled></el-input>
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
import storeApi from '@a/store'
import permission from '@/directive/permission/index.js'
import addressApi from '@a/address'

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
        address: '',
        x: null,
        y: null,
        paymentWay: null,
        expireDate: null,
        state: null
      },
      loading: false,
      searchLoading: false,
      addressList: []
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
          })
          .finally(() => {
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
    },
    onSearch(query) {
      if (query !== '') {
        this.searchLoading = true
        addressApi.searchAddress(query)
            .then(res => {
              this.addressList = res
            })
            .catch(() => {
              this.addressList = []
            })
            .finally(() => {
              this.searchLoading = false
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
    },
  }
}
</script>

<style scoped>

</style>
