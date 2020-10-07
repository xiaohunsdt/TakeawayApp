<template>
  <el-dialog
      :close-on-click-modal="false"
      :show-close="false"
      :visible.sync="dialogVisible"
      style="text-align: left">
    <template v-slot:title>
      <h3 v-if="goods===null">添加商品</h3>
      <h3 v-else>编辑商品</h3>
    </template>
    <el-steps :active="active" align-center finish-status="success">
      <el-step title="基本信息"></el-step>
      <el-step title="规格设置"></el-step>
      <el-step title="SKU设置"></el-step>
      <el-step title="追加商品"></el-step>
    </el-steps>
    <div style="margin-top:50px">
      <el-form v-if="active===0" :model="produceData" :rules="rules" size="mini" status-icon>
        <base-card>
          <el-form-item label="商品名称" prop="name">
            <el-input v-model="produceData.name" autocomplete="off"/>
          </el-form-item>
          <el-form-item label="简介" prop="desc">
            <el-input v-model="produceData.desc" autocomplete="off"/>
          </el-form-item>
          <el-form-item label="所属分类" prop="categoryId">
            <el-select v-model="produceData.categoryId" placeholder="选择分类">
              <el-option
                  v-for="item in categoryList"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id"/>
            </el-select>
          </el-form-item>
          <el-form-item label="商品标记">
            <el-checkbox-group v-model="flagSelected">
              <el-checkbox label="新品" name="flag"></el-checkbox>
              <el-checkbox label="热门" name="flag"></el-checkbox>
              <el-checkbox label="今日推荐" name="flag"></el-checkbox>
            </el-checkbox-group>
          </el-form-item>
          <el-form-item label="优先级" prop="rate">
            <el-input v-model.number="produceData.index" autocomplete="off" type="number"></el-input>
          </el-form-item>
          <el-form-item label="状态" prop="state">
            <el-select v-model="produceData.state" placeholder="选择分类">
              <el-option label="下架" value="OFF"/>
              <el-option label="上架" value="ON"/>
              <el-option label="缺货" value="SHORTAGE"/>
            </el-select>
          </el-form-item>
        </base-card>
      </el-form>
      <el-form v-if="active===1" :model="specData" size="mini">
        <base-card>
          <h2>设置规格</h2>
          <el-form-item label="规格">
            <el-select v-model="specData.currentSpec" placeholder="规格" value-key="id">
              <el-option
                  v-for="item in specList"
                  :key="item.id"
                  :label="item.key"
                  :value="item">
              </el-option>
            </el-select>
            <el-button style="margin-left: 10px" type="primary" @click="addSpec()">
              添加
            </el-button>
          </el-form-item>
          <el-table
              :data="specData.selected"
              style="width: 100%">
            <el-table-column label="规格">
              <template v-slot="scope">
                {{ scope.row.key }}
              </template>
            </el-table-column>
            <el-table-column label="操作">
              <template v-slot="scope">
                <el-button size="mini" style="margin-left: 10px" type="primary" @click="deleteSpec(scope.row)">
                  删除
                </el-button>
              </template>
            </el-table-column>
          </el-table>
        </base-card>
        <base-card v-if="specData.selected.length > 0" style="margin-top: 50px">
          <h2>设置参数</h2>
          <el-row :gutter="20">
            <el-col
                v-for="(spec, index) in specData.selected"
                :key="spec.id"
                :span="8">
              <dynamic-input
                  :label="spec.key"
                  :model-array="spec.params"
                  :rule-model-name="`selected.${index}.params`"/>
            </el-col>
          </el-row>
        </base-card>
      </el-form>
      <div v-if="active===3">
        <base-card>
          <el-table style="width: 100%">
            <el-table-column label="规格">
              <template v-slot="scope">
                {{ scope.row.key }}
              </template>
            </el-table-column>
            <el-table-column label="可用">
              <template v-slot="scope">
                <el-button size="mini" style="margin-left: 10px" type="primary" @click="deleteSpec(scope.row)">
                  可用
                </el-button>
              </template>
            </el-table-column>
          </el-table>
        </base-card>
      </div>
    </div>
    <div slot="footer" class="dialog-footer">
      <el-button @click="closeWindow">取 消</el-button>
      <el-button v-if="active!==0" type="primary" @click="active--">
        上一步
      </el-button>
      <el-button v-if="active!==3" type="primary" @click="active++">
        下一步
      </el-button>
      <el-button v-if="active===3" v-loading.fullscreen.lock="sendLoading" type="primary" @click="handleCreateNewGoods">
        <div v-if="goods===null">创建</div>
        <div v-else>修改</div>
      </el-button>
    </div>
  </el-dialog>
</template>

<script>
import goodsApi from '@/api/goods'
import DynamicInput from './DynamicInput'
import BaseCard from '@c/BaseCard'

export default {
  name: 'GoodsDialog',
  components: {
    BaseCard,
    DynamicInput
  },
  watch: {
    dialogVisible(newVal) {
      if (newVal) {
        if (this.goods != null) {
          this.sendLoading = true
          goodsApi.getByGoodsId(this.goods.id)
              .then(response => {
                this.produceData = response
                this.flagSelected = this.produceData.flags.split(',')
              })
              .finally(() => {
                this.sendLoading = false
              })
        }
      }
    },
    active(newVal) {
      if (newVal === 3) {
        const allCount = this.specData.selected.map(item => item.params.length).reduce((prev, curr) => {
          return prev * curr
        })

        for (let i = 0; i < this.specData.selected.length; i++) {
          const indexes = []
          for (let j = 0; j < this.specData.selected.length; j++) {
            indexes.push(0)
          }

          for (let j = 0; j < this.specData.selected.length; j++) {
            if (i === j) {
              continue
            }
            if(++indexes[j] < this.specData.selected[j].params.length){
              for (let k = 0; k < ; k++) {

              }
            }
          }
        }
      }
    }
  },
  data() {
    return {
      dialogVisible: false,
      sendLoading: false,
      active: 0,
      goods: null,
      categoryList: [],
      specList: [{
        id: 1,
        key: '颜色',
        params: []
      },
        {
          id: 2,
          key: '尺寸',
          params: []
        },
        {
          id: 3,
          key: '口味',
          params: []
        },
        {
          id: 4,
          key: '測試',
          params: []
        }
      ],
      produceData: {},
      specData: {
        currentSpec: null,
        selected: []
      },
      goodsList: [],
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
          { required: true, message: '请选择分类', trigger: 'change' }
        ],
        price: [
          { type: 'number', required: true, message: '请输入价格', trigger: 'blur' }
        ],
        rate: [
          { type: 'number', min: 0, max: 10, message: '请输入0-10的数字', trigger: 'blur' }
        ],
        state: [
          { required: true, message: '请选择状态', trigger: 'change' }
        ]
      }
    }
  },
  methods: {
    handleCreateNewGoods() {
      this.$refs.formRef.validate((valid) => {
        if (valid) {
          if (this.goods === null) {
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
      this.produceData.flags = this.flagSelected.join()

      this.sendLoading = true
      goodsApi.createNewGoods(this.produceData)
          .then(response => {
            this.$message({
              message: response.message,
              type: 'success'
            })
            this.$emit('event-success')
            this.closeWindow()
          })
          .finally(() => {
            this.sendLoading = false
          })
    },
    updateGoods() {
      this.produceData.flags = this.flagSelected.join()

      this.sendLoading = true
      goodsApi.updateGoods(this.produceData)
          .then(response => {
            this.$message({
              message: response.message,
              type: 'success'
            })
            this.$emit('event-success')
            this.closeWindow()
          })
          .finally(() => {
            this.sendLoading = false
          })
    },
    openWindow(goods, categoryList) {
      this.goods = goods
      this.categoryList = categoryList
      this.dialogVisible = true
    },
    closeWindow() {
      this.dialogVisible = false
      this.active = 0
      this.produceData = {}
      this.specData = {
        currentSpec: null,
        selected: []
      }
      this.goodsData = {}
      this.flagSelected = []
      this.$emit('update:dialogVisible', false)
      this.$emit('update:goods', null)
    },
    addSpec() {
      if (!this.specData.currentSpec) {
        this.$message.error('请先选择规格')
        return
      }
      const spec = this.specData.currentSpec
      const index = this.specData.selected.findIndex(item => item.id === spec.id)
      if (index >= 0) {
        this.$message.error('已经添加此规格')
        return
      }
      this.specData.selected.push(spec)
    },
    deleteSpec(spec) {
      const index = this.specData.selected.findIndex(item => item.id === spec.id)
      console.log(index)
      if (index >= 0) {
        this.specData.selected.splice(index, 1)
      }
    }
  }
}
</script>

<style scoped>

</style>
