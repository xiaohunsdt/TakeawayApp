<template>
  <el-dialog
    :close-on-click-modal="false"
    :show-close="false"
    :visible.sync="dialogVisible"
    style="text-align: left">
    <template v-slot:title>
      <h3 v-if="goodsData===null">添加商品</h3>
      <h3 v-else>编辑商品</h3>
    </template>
    <el-form :model="formData" :rules="rules" ref="formRef" size="mini">
      <el-form-item label="商品名称" prop="name">
        <el-input autocomplete="off" v-model="formData.name"></el-input>
      </el-form-item>
      <el-form-item label="简介" prop="desc">
        <el-input autocomplete="off" v-model="formData.desc"></el-input>
      </el-form-item>
      <el-form-item label="所属分类" prop="categoryId">
        <el-select placeholder="选择分类" v-model="formData.categoryId">
          <el-option
            :key="item.id"
            :label="item.name"
            :value="item.id"
            v-for="item in categoryList">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="商品标记">
        <el-checkbox-group v-model="flagSelected">
          <el-checkbox label="新品" name="flag"></el-checkbox>
          <el-checkbox label="热门" name="flag"></el-checkbox>
          <el-checkbox label="今日推荐" name="flag"></el-checkbox>
        </el-checkbox-group>
      </el-form-item>
      <el-form-item label="价格" prop="price">
        <el-input autocomplete="off" type="number" v-model.number="formData.price"></el-input>
      </el-form-item>
      <el-form-item label="库存">
        <el-input autocomplete="off" type="stock" v-model.number="formData.stock"></el-input>
      </el-form-item>
      <el-form-item label="月销">
        <el-input autocomplete="off" type="number" v-model.number="formData.monthSale"></el-input>
      </el-form-item>
      <el-form-item label="评分" prop="rate">
        <el-input autocomplete="off" type="number" v-model.number="formData.rate"></el-input>
      </el-form-item>
      <el-form-item label="优先级" prop="rate">
        <el-input autocomplete="off" type="number" v-model.number="formData.index"></el-input>
      </el-form-item>
      <el-form-item label="状态" prop="state">
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
      <el-button @click="closeWindow">取 消</el-button>
      <el-button
        @click="handleCreateNewGoods"
        type="primary"
        v-loading.fullscreen.lock="sendLoading">确 定
      </el-button>
    </div>
  </el-dialog>
</template>

<script>
  import goodsApi from '@/api/goods'

  export default {
    name: 'GoodsDialog',
    props: {
      dialogVisible: {
        type: Boolean,
        required: true
      },
      categoryList: {
        type: Array,
        required: true
      },
      goodsData: {
        type: Object,
        default: null,
        required: false
      }
    },
    data() {
      return {
        sendLoading: false,
        formData: {},
        flagSelected: [],
        rules: {
          name: [
            { required: true, message: '请输入商品名称', trigger: 'blur' },
            { max: 16, message: '最多16个字符', trigger: 'blur' }
          ],
          desc: [
            { max: 50, message: '最多50个字符', trigger: 'blur' }
          ],
          categoryId: [
            { required: true, message: '请选择分类', trigger: 'blur' }
          ],
          price: [
            { type: 'number', required: true, message: '请输入价格', trigger: 'blur' }
          ],
          rate: [
            { type: 'number', min: 0, max: 10, message: '请输入0-10的数字', trigger: 'blur' }
          ],
          state: [
            { required: true, message: '请选择状态', trigger: 'blur' }
          ]
        }
      }
    },
    watch: {
      dialogVisible(newVal, oldVal) {
        if (newVal) {
          if (this.goodsData != null) {
            this.sendLoading = true
            goodsApi.getByGoodsId(this.goodsData.id)
                .then(response => {
                  this.sendLoading = false
                  this.formData = response
                  this.flagSelected = this.formData.flags.split(',')
                })
                .catch(() => {
                  this.sendLoading = false
                })
          }
        }
      }
    },
    methods: {
      handleCreateNewGoods() {
        this.$refs.formRef.validate((valid) => {
          if (valid) {
            if (this.goodsData === null) {
              this.createGoods()
            } else {
              this.updateGoods()
            }
          } else {
            console.log('error submit!!')
            return false
          }
        })
      },
      createGoods() {
        this.formData.flags = this.flagSelected.join()

        this.sendLoading = true
        goodsApi.createNewGoods(this.formData)
          .then(response => {
            this.sendLoading = false
            this.$message({
              message: response.message,
              type: 'success'
            })
            this.$emit('event-success')
            this.closeWindow()
          })
          .catch(() => {
            this.sendLoading = false
          })
      },
      updateGoods() {
        this.formData.flags = this.flagSelected.join()

        this.sendLoading = true
        goodsApi.updateGoods(this.formData)
          .then(response => {
            this.sendLoading = false
            this.$message({
              message: response.message,
              type: 'success'
            })
            this.$emit('event-success')
            this.closeWindow()
          })
          .catch(() => {
            this.sendLoading = false
          })
      },
      closeWindow() {
        this.formData = {}
        this.flagSelected = []
        this.$emit('update:dialogVisible', false)
        this.$emit('update:goodsData', null)
      }
    }
  }
</script>

<style scoped>

</style>
