<template>
  <el-dialog
      :close-on-click-modal="false"
      :show-close="false"
      :visible.sync="dialogVisible"
      style="text-align: left">
    <template v-slot:title>
      <h3 v-if="produce===null">添加商品</h3>
      <h3 v-else>编辑商品</h3>
    </template>
    <el-steps :active="active" align-center finish-status="success">
      <el-step title="基本信息"></el-step>
      <el-step title="规格设置"></el-step>
      <el-step title="SKU设置"></el-step>
    </el-steps>
    <div style="margin-top:50px">
      <el-form v-show="active===0" ref="produce-form" :model="produceData" :rules="rules" size="mini" status-icon>
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
            </el-select>
          </el-form-item>
        </base-card>
      </el-form>
      <el-form v-show="active===1" :model="specData" size="mini">
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
      <div v-if="active===2">
        <base-card>
          <el-table :data="goodsList" max-height="600px" style="width: 100%">
            <el-table-column v-for="spec in specData.selected" :key="spec.key" :label="spec.key">
              <template v-slot="scope">
                {{ scope.row.ownSpecs[spec.id] }}
              </template>
            </el-table-column>
            <el-table-column label="价格">
              <template v-slot="scope">
                <el-input v-model.number="scope.row.price" placeholder="请输入价格" size="small"/>
              </template>
            </el-table-column>
            <el-table-column label="库存">
              <template v-slot="scope">
                <el-tooltip content="-1表示无限库存" placement="right">
                  <el-input v-model.number="scope.row.stock" placeholder="请输入库存" size="small"/>
                </el-tooltip>
              </template>
            </el-table-column>
            <el-table-column label="可用">
              <template v-slot="scope">
                <el-select v-model="scope.row.state" placeholder="选择状态">
                  <el-option label="可用" value="ON"></el-option>
                  <el-option label="不可用" value="OFF"></el-option>
                  <el-option label="缺货" value="SHORTAGE"></el-option>
                </el-select>
              </template>
            </el-table-column>
          </el-table>
        </base-card>
      </div>
    </div>
    <div slot="footer" class="dialog-footer">
      <el-button @click="closeWindow">取 消</el-button>
      <el-button v-if="active!==0" type="primary" @click="previous()">
        上一步
      </el-button>
      <el-button v-if="active!==2" type="primary" @click="next()">
        下一步
      </el-button>
      <el-button v-if="active===2" v-loading.fullscreen.lock="sendLoading" type="primary" @click="handleCreateNewGoods">
        <div v-if="produce===null">创建</div>
        <div v-else>修改</div>
      </el-button>
    </div>
  </el-dialog>
</template>

<script>
import produceApi from '@/api/produce'
import specApi from '@/api/spec'
import DynamicInput from './DynamicInput'
import BaseCard from '@c/BaseCard'

export default {
  name: 'GoodsDialog',
  components: {
    BaseCard,
    DynamicInput
  },
  watch: {
    active(newVal) {
      if (newVal === 2) {
        this.formatGoodsList()
      }
    }
  },
  data() {
    return {
      dialogVisible: false,
      sendLoading: false,
      active: 0,
      produce: null,
      categoryList: [],
      specList: [],
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
      if (this.produce === null) {
        this.createGoods()
      } else {
        this.updateGoods()
      }
    },
    createGoods() {
      this.produceData.flags = this.flagSelected.join()
      // this.goodsList.forEach(item => {
      //   if (item.state === 'ON' && item.stock === 0) {
      //     this.$message.warning('商品可用状态下必须设置库存')
      //   }
      // })
      this.sendLoading = true
      produceApi.create(this.produceData, this.specData.selected, this.goodsList)
          .then(res => {
            this.$message.success(res.message)
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
      produceApi.update(this.produceData, this.specData.selected, this.goodsList)
          .then(res => {
            this.$message.success(res.message)
            this.$emit('event-success')
            this.closeWindow()
          })
          .finally(() => {
            this.sendLoading = false
          })
    },
    async openWindow(produce, categoryList) {
      this.categoryList = categoryList
      this.dialogVisible = true
      await specApi.getAll().then(res => {
        res.forEach(item => {
          item.params = []
        })
        this.specList = res
      })
      if (produce) {
        this.sendLoading = true
        produceApi.getDetailById(produce.id)
            .then(res => {
              this.produce = res
              this.produceData = res.produce
              const specs = JSON.parse(res.specs.options)
              for (const key in specs) {
                const index = this.specList.findIndex(item => item.id === key)
                if (index >= 0) {
                  const paramsArr = specs[key].map(item => {
                    return {
                      key: new Date().getTime() + Math.floor(Math.random() * 100),
                      value: item
                    }
                  })
                  this.specList[index].params.push(...paramsArr)
                  this.specData.selected.push(this.specList[index])
                }
              }
            })
            .finally(() => {
              this.sendLoading = false
            })
      }
    },
    closeWindow() {
      this.dialogVisible = false

      this.active = 0
      this.produce = null
      this.produceData = {}
      this.specData = {
        currentSpec: null,
        selected: []
      }
      this.flagSelected = []
      this.$emit('update:dialogVisible', false)
      this.$emit('update:produce', null)
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
    },
    previous() {
      this.active--
    },
    next() {
      if (this.active === 0) {
        this.$refs['produce-form'].validate((valid) => {
          if (valid) {
            this.active++
          }
        })
      } else if (this.active === 1) {
        if (this.specData.selected.length > 0) {
          for (const item of this.specData.selected) {
            if (item.params.length === 0) {
              this.$message.warning('请将规格参数填写完整!')
              return
            }
          }
        }
        if (this.produce && produceApi.formatSpecs(this.specData.selected).options !== this.produce.specs.options) {
          this.$confirm('此产品的规格参数发生改变,之前设置的sku将全部作废,是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            this.active++
          })
        } else {
          this.active++
        }
      } else {
        this.active++
      }
    },
    formatGoodsList() {
      this.goodsList = []
      let sku = []
      const array = this.specData.selected.map(spec => spec.params.map(item => {
        const obj = {}
        obj[spec.id] = item.value
        return obj
      }))
      if (array.length < 2) {
        sku = array[0] || []
      } else {
        sku = array.reduce((total, currentValue) => {
          const res = []
          total.forEach(t => {
            currentValue.forEach(cv => {
              if (t instanceof Array) {
                res.push([...t, cv])
              } else {
                res.push([t, cv])
              }
            })
          })
          return res
        })
      }

      if (sku.length > 0) {
        for (let i = 0; i < sku.length; i++) {
          const indexes = []
          if (sku[i] instanceof Array) {
            for (let j = 0; j < sku[i].length; j++) {
              const key = this.specData.selected[j]['id']
              const index = this.specData.selected[j].params.findIndex(item => item.value === sku[i][j][key])
              indexes[j] = index
            }
          } else {
            const key = this.specData.selected[0]['id']
            const index = this.specData.selected[0].params.findIndex(item => item.value === sku[i][key])
            indexes[0] = index
          }

          let targetSku = sku[i]
          if (targetSku instanceof Array) {
            targetSku = targetSku.reduce((previous, next) => {
              return Object.assign(previous, next)
            }, {})
          }

          let goodsData
          if (this.produce) {
            const goodsIndex = this.produce.goodsList.findIndex(item => {
                  return item.indexes === indexes.join('_') && item.ownSpecs === JSON.stringify(targetSku)
                }
            )
            if (goodsIndex >= 0) {
              goodsData = Object.assign({}, this.produce.goodsList[goodsIndex])
              goodsData.ownSpecs = JSON.parse(goodsData.ownSpecs)
            }
          }

          if (!goodsData) {
            goodsData = {}
            goodsData.ownSpecs = targetSku
            goodsData.indexes = indexes.join('_')
            goodsData.price = 0
            goodsData.stock = -1
            goodsData.state = 'OFF'
          }
          goodsData.title = Object.values(targetSku).join(' ')
          this.goodsList.push(goodsData)
        }
      } else {
        let goodsData
        if (this.produce && this.produce.goodsList.length === 1) {
          goodsData = Object.assign({}, this.produce.goodsList[0])
        } else {
          goodsData = {}
          goodsData.price = 0
          goodsData.stock = -1
          goodsData.state = 'OFF'
        }
        goodsData.title = ''
        goodsData.ownSpecs = null
        goodsData.indexes = null
        this.goodsList.push(goodsData)
      }
    }
  }
}
</script>

<style scoped>

</style>
