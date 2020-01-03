<template>
  <div class="container">
    <base-card class="container-main">
      <div style="display: flex;justify-content: center;">
        <el-form :model="formData" label-width="120px" ref="form"
                 style="display: flex;justify-content: center;width: 920px">
          <div style="width:460px">
            <el-form-item :rules=" { required: true, message: '请输入标题', trigger: 'blur' }" label="优惠卷名称"
                          prop="couponName">
              <el-input v-model="formData.couponName"/>
            </el-form-item>
            <el-form-item label="优惠卷类型">
              <el-select placeholder="请选择优惠卷类型" v-model="formData.couponType">
                <el-option label="现金卷" value="MONEY"/>
                <el-option label="折扣卷" value="DISCOUNT"/>
              </el-select>
            </el-form-item>
            <el-form-item label="优惠卷面值" v-if="formData.couponType==='MONEY'">
              <el-input v-model.number="formData.couponMoney"/>
            </el-form-item>
            <el-form-item label="优惠卷折扣" v-if="formData.couponType==='DISCOUNT'">
              <el-input v-model.number="formData.couponDiscount"/>
            </el-form-item>
            <el-form-item label="最低消费">
              <el-input v-model.number="formData.minimumMoney"/>
            </el-form-item>
            <el-form-item label="过期天数">
              <el-input v-model.number="formData.expireDays"/>
            </el-form-item>
          </div>
          <el-divider direction="vertical"/>
          <div style="width:460px">
            <dynamic-input :model-array.sync="formData.allowCategory" label="允许的分类" rule-model-name="allowCategory"/>
            <dynamic-input :model-array.sync="formData.limitCategory" label="禁止的分类" rule-model-name="limitCategory"/>
            <dynamic-input :model-array.sync="formData.allowGoods" label="禁止的商品" rule-model-name="allowGoods"/>
            <dynamic-input :model-array.sync="formData.limitGoods" label="允许的商品" rule-model-name="limitGoods"/>
          </div>
        </el-form>
      </div>
      <div style="text-align: center;margin-top: 15px">
        <el-button @click="saveTemplate" round size="medium" type="success">保存</el-button>
      </div>
    </base-card>
  </div>
</template>

<script>
  import couponTemplateApi from '@/api/coupon-template'
  import DynamicInput from './components/DynamicInput'

  import BaseCard from '@/components/BaseCard'

  export default {
    name: 'CouponTemplateEditManagement',
    components: {
      BaseCard,
      DynamicInput
    },
    activated() {
      // const query = getQueryObject()
    },
    data() {
      return {
        formData: {
          couponName: '',
          couponType: 'MONEY',
          couponMoney: 0,
          couponDiscount: 0,
          minimumMoney: 0,
          expireDays: 0,
          allowCategory: [],
          limitCategory: [],
          allowGoods: [],
          limitGoods: []
        }
      }
    },
    methods: {
      init() {
        this.formData.couponName = ''
        this.formData.couponType = 'MONEY'
        this.formData.couponMoney = 0
        this.formData.couponDiscount = 0
        this.formData.minimumMoney = 0
        this.formData.expireDays = 0
        this.formData.allowCategory = []
        this.formData.limitCategory = []
        this.formData.allowGoods = []
        this.formData.limitGoods = []
      },
      saveTemplate() {
        this.$refs.form.validate((valid) => {
          if (valid) {
            couponTemplateApi.createNewTemplate(this.formData)
              .then(res => {
                this.init()
                this.$message({
                  message: res.message,
                  type: 'success'
                })
              })
          }
        })
      }
    }
  }
</script>

<style lang="scss" scoped>
  $bg: #F3F3F9;

  .container {
    padding: 1rem;
    min-height: inherit;
    width: 100%;
    background-color: $bg;
    overflow: hidden;
  }

  .el-divider--vertical {
    height: inherit;
    margin: 0 14px;
  }
</style>
