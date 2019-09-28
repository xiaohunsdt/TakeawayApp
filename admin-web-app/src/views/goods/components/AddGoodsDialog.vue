<template>
  <div>
    <el-button @click="onCreateNewGoods" size="mini" type="success">创建新分类</el-button>
    <el-dialog
      :destroy-on-close="true"
      :visible.sync="dialogVisible"
      style="text-align: left"
      title="添加商品">
      <el-form :model="formData" :rules="rules" size="mini" ref="formRef">
        <el-form-item label="商品名称" prop="name">
          <el-input autocomplete="off" v-model="formData.name"></el-input>
        </el-form-item>
        <el-form-item label="简介" prop="desc">
          <el-input autocomplete="off" v-model="formData.desc"></el-input>
        </el-form-item>
        <el-form-item label="所属分类" prop="category">
          <el-select placeholder="选择分类" v-model="formData.categoryId">
            <el-option
              :key="item.id"
              :label="item.name"
              :value="item.id"
              v-for="item in categoryList">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="价格">
          <el-input autocomplete="off" v-model="formData.price"></el-input>
        </el-form-item>
        <el-form-item label="月销">
          <el-input autocomplete="off" v-model="formData.monthSale"></el-input>
        </el-form-item>
        <el-form-item label="评分">
          <el-input autocomplete="off" v-model="formData.rate"></el-input>
        </el-form-item>
        <el-form-item label="状态">
          <el-select placeholder="选择分类" v-model="formData.state">
            <el-option
              label="下架"
              value="OFF">
            </el-option>
            <el-option
              label="上架"
              value="ON">
            </el-option>
            <el-option
              label="缺货"
              value="SHORTAGE">
            </el-option>
          </el-select>
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
        props: {
            categoryList: {
                type: Array,
                required: true
            }
        },
        data() {
            return {
                dialogVisible: false,
                createLoading: false,
                formData: {},
                rules: {
                    name: [
                        { required: true, message: '请输入商品名称', trigger: 'blur' },
                        { max: 8, message: '最多8个字符', trigger: 'blur' }
                    ],
                    desc: [
                        { max: 50, message: '最多50个字符', trigger: 'blur' }
                    ],
                    category: [
                        { required: true, message: '请选择分类', trigger: 'blur' }
                    ]
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
            onCreateNewGoods() {
                this.dialogVisible = true
            },
            handleCreateNewGoods() {
                this.$refs.formRef.validate((valid) => {
                    if (valid) {
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
                    } else {
                        console.log('error submit!!')
                        return false
                    }
                })
            }
        }
    }
</script>

<style lang="scss">
  .el-dialog {
    width: 500px;
  }
</style>

<style scoped>

</style>
