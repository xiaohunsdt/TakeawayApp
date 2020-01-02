<template>
  <div class="container">
    <base-card class="container-main">
      <div style="display: flex;justify-content: center;">
        <el-form :model="formData" :rules="rules" label-width="120px" ref="form" style="width: 460px">
          <el-form-item label="优惠卷名称" prop="couponName">
            <el-input v-model="formData.couponName"></el-input>
          </el-form-item>
          <el-form-item label="优惠卷类型">
            <el-select placeholder="请选择优惠卷类型" v-model="formData.couponType">
              <el-option :value="1" label="现金卷"></el-option>
              <el-option :value="2" label="折扣卷"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="优惠卷面值">
            <el-input v-model="formData.couponMoney"></el-input>
          </el-form-item>
          <el-form-item label="优惠卷折扣">
            <el-input v-model="formData.couponDiscount"></el-input>
          </el-form-item>
          <el-form-item label="最低消费">
            <el-input v-model="formData.minimumMoney"></el-input>
          </el-form-item>
          <el-form-item label="到期时间">
            <el-input v-model="formData.expireDays"></el-input>
          </el-form-item>
        </el-form>
        <el-divider direction="vertical"></el-divider>
        <el-form :model="formData" label-width="120px" ref="form-rule" style="width: 460px">
          <dynamic-input label="允许的分类" :model-array.sync="formData.allowCategory" />
          <dynamic-input label="禁止的分类" :model-array.sync="formData.limitCategory" />
          <dynamic-input label="禁止的商品" :model-array.sync="formData.allowGoods" />
          <dynamic-input label="允许的商品" :model-array.sync="formData.limitGoods" />
        </el-form>
      </div>
      <div style="text-align: center;margin-top: 15px">
        <el-button @click="saveTemplate" round size="medium" type="success">保存</el-button>
      </div>
    </base-card>
  </div>
</template>

<script>
  // import { getQueryObject } from '@/utils/index'
  // import activityApi from '@/api/activity'
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
          couponType: 1,
          couponMoney: 0,
          couponDiscount: 0,
          minimumMoney: 0,
          expireDays: 0,
          allowCategory: [],
          limitCategory: [],
          allowGoods: [],
          limitGoods: []
        },
        rules: {
          couponName: [
            { required: true, message: '请输入标题', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      saveTemplate() {
        this.$refs.form.validate((valid) => {
          if (valid) {
            this.$message({
              // message: response.message,
              type: 'success'
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
