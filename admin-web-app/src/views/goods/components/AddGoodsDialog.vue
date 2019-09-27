<template>
  <div style="text-align: left">
    <el-button @click="onCreateNewGoods" size="mini" type="success">创建新分类</el-button>
    <el-dialog
      :destroy-on-close="true"
      :visible.sync="dialogVisible"
      title="添加商品">
      <el-form :model="formData">
        <el-form-item label="商品名称">
          <el-input autocomplete="off" v-model="formData.name"></el-input>
        </el-form-item>
      </el-form>
      <div class="dialog-footer" slot="footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button
          @click="handleCreateNewGoods"
          type="primary"
          v-loading.fullscreen.lock="createLoading">确 定
        </el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
    import goodsApi from '@/api/goods'

    export default {
        name: 'AddGoodsDialog',
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
                    this.formData.name = null
                }
            }
        },
        methods: {
            onCreateNewGoods() {
                this.dialogVisible = true
            },
            handleCreateNewGoods() {
                if (this.formData.name === null || this.formData.name === '') {
                    this.$message({
                        showClose: true,
                        message: '请输入商品名称',
                        type: 'error'
                    })
                    return
                }
                this.createLoading = true
                goodsApi.createNewGoods(this.formData)
                    .then(response => {
                        this.createLoading = false
                        this.dialogVisible = false
                        this.$message({
                            message: response.message,
                            type: 'success'
                        })
                        this.$emit('createSuccess')
                    })
                    .catch(() => {
                        this.createLoading = false
                    })
            }
        }
    }
</script>

<style scoped>

</style>
