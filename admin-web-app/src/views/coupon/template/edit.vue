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
          <el-form-item label="允许的分类" v-if="formData.allowCategory.length===0">
            <el-button @click.prevent="addAllowCategory" circle icon="el-icon-plus" size="mini" type="success"/>
          </el-form-item>
          <el-form-item
            :key="index"
            :label="`允许的分类(${index})`"
            :prop="`category.${index}`.value"
            :rules="{required: true, message: '分类名称不能为空', trigger: 'blur'}"
            v-for="(category, index) in formData.allowCategory">
            <el-input v-model="category.value">
              <template slot="append">
                <i @click.prevent="removeAllowCategory(category)" class="el-icon-delete"></i>
              </template>
            </el-input>
          </el-form-item>
          <el-button @click.prevent="addAllowCategory" circle icon="el-icon-plus" size="mini" type="success" v-if="formData.allowCategory.length>0"/>

          <el-form-item label="禁止的分类" v-if="formData.limitCategory.length===0">
            <el-button @click.prevent="addLimitCategory" circle icon="el-icon-plus" size="mini" type="success"/>
          </el-form-item>
          <el-form-item
            :key="index"
            :label="`禁止的分类(${index})`"
            :prop="`category.${index}`.value"
            :rules="{required: true, message: '分类名称不能为空', trigger: 'blur'}"
            v-for="(category, index) in formData.limitCategory">
            <el-input v-model="category.value">
              <template slot="append">
                <i @click.prevent="removeLimitCategory(category)" class="el-icon-delete"></i>
              </template>
            </el-input>
          </el-form-item>
          <el-button @click.prevent="addLimitCategory" circle icon="el-icon-plus" size="mini" type="success" v-if="formData.limitCategory.length>0"/>
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

  import BaseCard from '@/components/BaseCard'

  export default {
    name: 'CouponTemplateEditManagement',
    components: {
      BaseCard
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
      },
      addAllowCategory() {
        this.formData.allowCategory.push({
          value: ''
        })
      },
      removeAllowCategory(category) {
        var index = this.formData.allowCategory.indexOf(category)
        if (index !== -1) {
          this.formData.allowCategory.splice(index, 1)
        }
      },
      addLimitCategory() {
        this.formData.limitCategory.push({
          value: ''
        })
      },
      removeLimitCategory(category) {
        var index = this.formData.limitCategory.indexOf(category)
        if (index !== -1) {
          this.formData.limitCategory.splice(index, 1)
        }
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
