<template>
  <el-dialog
      :close-on-click-modal="false"
      :title="adminId?'管理员详情':'生成新管理员'"
      :visible.sync="dialogFormVisible"
      size="mini"
      width="400px">
    <el-form :model="formData">
      <el-form-item label="管理员ID" label-width="120" v-if="adminId">
        <el-input disabled v-model="formData.id"></el-input>
      </el-form-item>
      <el-form-item label="用户名" label-width="120">
        <el-input :disabled="adminId!==null && adminId!==undefined" v-model="formData.userName"></el-input>
      </el-form-item>
      <el-form-item label="密码" label-width="120">
        <el-input v-model="formData.password"></el-input>
      </el-form-item>
      <el-form-item label="级别" label-width="120">
        <el-select placeholder="请输入关键词" v-model="formData.level">
          <el-option label="店管理员" v-permission="['SUPER_MANAGER']" value="SHOP_MANAGER"/>
          <el-option label="接单员" v-permission="['SUPER_MANAGER','SHOP_MANAGER']" value="RECEIVER"/>
          <el-option label="外卖员" v-permission="['SUPER_MANAGER','SHOP_MANAGER']" value="DELIVERER"/>
        </el-select>
      </el-form-item>
      <el-form-item label="状态" label-width="120">
        <el-select placeholder="请输入关键词" v-model="formData.state">
          <el-option label="冻结" value="STOP"/>
          <el-option label="正常" value="NORMAL"/>
        </el-select>
      </el-form-item>
    </el-form>
    <div class="dialog-footer" slot="footer">
      <el-button @click="dialogFormVisible = false">取 消</el-button>
      <el-button @click="onEdit" type="primary" v-if="adminId">修 改</el-button>
      <el-button @click="onSave" type="success" v-else>生 成</el-button>
    </div>
  </el-dialog>
</template>

<script>
import adminApi from '@/api/admin'
import permission from '@/directive/permission/index.js'

export default {
  name: 'StoreDialog',
  directives: { permission },
  data() {
    return {
      dialogFormVisible: false,
      adminId: null,
      formData: {
        id: null,
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
            this.loading = false
          })
          .catch(res => {
            this.loading = false
          })
    },
    openDialog(adminId) {
      this.adminId = adminId
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
