<template>
  <el-dialog
      :close-on-click-modal="false"
      :title="adminId?'管理员详情':'生成新管理员'"
      :visible.sync="dialogFormVisible"
      size="mini"
      width="400px">
    <el-form :model="formData">
      <el-form-item v-if="adminId" label="管理员ID" label-width="120">
        <el-input v-model="formData.id" disabled></el-input>
      </el-form-item>
      <el-form-item label="用户名" label-width="120">
        <el-input v-model="formData.userName" :disabled="adminId!==null && adminId!==undefined"></el-input>
      </el-form-item>
      <el-form-item label="密码" label-width="120">
        <el-input v-model="formData.password"></el-input>
      </el-form-item>
      <el-form-item label="级别" label-width="120">
        <el-select v-model="formData.level" placeholder="请输入关键词">
          <el-option v-permission="['SUPER_MANAGER']" label="店管理员" value="SHOP_MANAGER"/>
          <el-option v-permission="['SUPER_MANAGER','SHOP_MANAGER']" label="接单员" value="RECEIVER"/>
          <el-option v-permission="['SUPER_MANAGER','SHOP_MANAGER']" label="外卖员" value="DELIVERER"/>
        </el-select>
      </el-form-item>
      <el-form-item label="店铺Id" label-width="120" v-permission="['SUPER_MANAGER']">
        <el-input v-model="formData.storeId"></el-input>
      </el-form-item>
      <el-form-item label="状态" label-width="120">
        <el-select v-model="formData.state" placeholder="请输入关键词">
          <el-option label="冻结" value="STOP"/>
          <el-option label="正常" value="NORMAL"/>
        </el-select>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="dialogFormVisible = false">取 消</el-button>
      <el-button v-if="adminId" type="primary" @click="onEdit">修 改</el-button>
      <el-button v-else type="success" @click="onSave">生 成</el-button>
    </div>
  </el-dialog>
</template>

<script>
import adminApi from '@/api/admin'
import permission from '@/directive/permission/index.js'

export default {
  name: 'AdminDialog',
  directives: { permission },
  data() {
    return {
      dialogFormVisible: false,
      adminId: null,
      formData: {
        id: null,
        storeId: null,
        userName: null,
        password: null,
        level: null,
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
      if (!this.adminId) {
        return
      }
      this.loading = true
      adminApi.getAdminInfoById(this.adminId)
          .then(res => {
            this.formData = res
          })
          .finally(res => {
            this.loading = false
          })
    },
    openDialog(adminId, storeId) {
      this.adminId = adminId
      this.formData.storeId = storeId
      this.dialogFormVisible = true
    },
    onEdit() {
      adminApi.updateAdmin(this.formData)
          .then(res => {
            this.$message({
              message: res.message,
              type: 'success'
            })
            this.$emit('updated-admin', this.formData)
          })
    },
    onSave() {
      adminApi.createNewSubAdmin(this.formData)
          .then(res => {
            this.$message({
              message: res.message,
              type: 'success'
            })
            this.$emit('updated-admin', this.formData)
          })
    }
  }
}
</script>

<style scoped>

</style>
