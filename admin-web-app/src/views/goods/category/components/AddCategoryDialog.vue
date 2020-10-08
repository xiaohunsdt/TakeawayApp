<template>
  <div>
    <el-button size="mini" type="success" @click="onCreateNewCategory">创建新分类</el-button>
    <el-dialog
        :destroy-on-close="true"
        :visible.sync="dialogVisible"
        style="text-align: left"
        title="添加分类">
      <el-form :model="formData">
        <el-form-item label="分类名称">
          <el-input v-model="formData.name" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button
            v-loading.fullscreen.lock="createLoading"
            type="primary"
            @click="handleCreateNewCategory">确 定
        </el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import categoryApi from '@a/category'

export default {
  name: 'AddCategoryDialog',
  data() {
    return {
      dialogVisible: false,
      createLoading: false,
      formData: {
        name: null
      }
    }
  },
  watch: {
    dialogVisible(newVal) {
      if (newVal) {
        this.formData = {}
      }
    }
  },
  methods: {
    onCreateNewCategory() {
      this.dialogVisible = true
    },
    handleCreateNewCategory() {
      if (this.formData.name === null || this.formData.name === '') {
        this.$message({
          showClose: true,
          message: '请输入分类名称',
          type: 'error'
        })
        return
      }
      this.createLoading = true
      categoryApi.createNewCategory(this.formData)
          .then(response => {
            this.dialogVisible = false
            this.$message({
              message: response.message,
              type: 'success'
            })
            this.$emit('createSuccess')
          })
          .finally(() => {
            this.createLoading = false
          })
    }
  }
}
</script>

<style scoped>

</style>
