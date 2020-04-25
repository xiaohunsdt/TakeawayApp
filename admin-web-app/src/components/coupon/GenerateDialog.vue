<template>
  <el-dialog :close-on-click-modal="false" :visible.sync="dialogFormVisible" size="mini" title="生成优惠卷" width="600px">
    <el-form :model="formData">
      <el-form-item label="优惠卷模板" label-width="120">
        <el-select placeholder="请选择优惠卷模板" v-model="formData.templateId" @change="onTemplateChange">
          <el-option
            :key="template.id"
            :label="template.couponName"
            :value="template.id"
            v-for="template in templateArr"/>
        </el-select>
      </el-form-item>
      <el-form-item label="用户ID" label-width="120">
        <el-input :autosize="{ minRows: 5, maxRows: 15}" type="textarea" v-model="formData.userIds"></el-input>
      </el-form-item>
      <el-form-item label="到期天数" label-width="120">
        <el-input v-model.number="formData.expireDays"></el-input>
      </el-form-item>
      <el-form-item label="优惠卷数量" label-width="120">
        <el-input v-model.number="formData.count"></el-input>
      </el-form-item>
    </el-form>
    <div class="dialog-footer" slot="footer">
      <el-button @click="dialogFormVisible = false">取 消</el-button>
      <el-button @click="generateCoupon" type="primary">生 成</el-button>
    </div>
  </el-dialog>
</template>

<script>
  import couponApi from '@/api/coupon'
  import couponTemplateApi from '@/api/coupon-template'

  export default {
    name: 'GenerateCoupon',
    props: {
      userIds: Array
    },
    watch: {
      dialogFormVisible() {
        if (this.dialogFormVisible) {
          this.init()
        }
      }
    },
    data() {
      return {
        dialogFormVisible: false,
        templateArr: [],
        formData: {
          templateId: '',
          userIds: '',
          expireDays: 0,
          count: 1
        }
      }
    },
    methods: {
      init() {
        if (this.userIds) {
          this.formData.userIds = this.userIds.join('\r\n')
        } else {
          this.formData.userIds = ''
        }

        couponTemplateApi.getAllTemplateList()
          .then(res => {
            this.templateArr = res
          })
      },
      openDialog() {
        this.dialogFormVisible = true
      },
      generateCoupon() {
        couponApi.generateCoupon(this.formData)
          .then(res => {
            this.$message({
              message: res.message,
              type: 'success'
            })
          })
      },
      onTemplateChange(event) {
        this.formData.expireDays = this.templateArr.find(item => item.id === event).expireDays
      }
    }
  }
</script>

<style scoped>

</style>
