<template>
  <div class="container">
    <base-card class="container-main">
      <div>
        <el-form :model="formData" :rules="rules" label-width="80px" ref="form" style="width: 460px">
          <el-form-item label="活动名称" prop="title">
            <el-input v-model="formData.title"></el-input>
          </el-form-item>
          <el-form-item label="活动主图">
            <el-upload
              :action="$VUE_APP_BASE_API + '/api/admin/activity/uploadImg'"
              :headers="authHeader"
              :on-success="onUploadImgSuccess">
              <el-button size="small" type="primary">点击上传</el-button>
            </el-upload>
            <img :src="$VUE_APP_BASE_API + formData.mainImg" style="height: 120px" v-if="formData.mainImg!==''"/>
          </el-form-item>
          <el-form-item label="活动时间">
            <el-date-picker
              end-placeholder="结束日期"
              format="yyyy-MM-dd"
              start-placeholder="开始日期"
              type="daterange"
              v-model="formData.formDate"
              value-format="yyyy-MM-dd"/>
          </el-form-item>
        </el-form>
      </div>
      <div>
        <tinymce :height="300" v-model="formData.content"/>
      </div>
      <div style="text-align: center;margin-top: 15px">
        <el-button @click="saveActivity" round size="medium" type="success">保存</el-button>
      </div>
    </base-card>
  </div>
</template>

<script>
  import { getToken } from '@/utils/auth'
  import { getQueryObject } from '@/utils/index'
  import activityApi from '@/api/activity'

  import BaseCard from '@/components/BaseCard'
  import Tinymce from '@/components/Tinymce'

  export default {
    name: 'EditActivity',
    components: {
      BaseCard,
      Tinymce
    },
    computed: {
      authHeader() {
        return {
          Authorization: `Bearer ${getToken()}`
        }
      }
    },
    activated() {
      const query = getQueryObject()
      if (query.activityId) {
        activityApi.getActivityById(query.activityId)
          .then(res => {
            this.formData = res
            this.formData.formDate = [
              new Date(this.formData.startDate),
              new Date(this.formData.endDate)
            ]
          })
      }
    },
    data() {
      return {
        formData: {
          title: '',
          content: '',
          mainImg: '',
          formDate: [
            new Date(),
            new Date(new Date().getTime() + 24 * 60 * 60 * 1000)
          ]
        },
        rules: {
          title: [
            { required: true, message: '请输入标题', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      onUploadImgSuccess(response, file, fileList) {
        this.formData.mainImg = `/upload/images/activity/${response.message}`
      },
      saveActivity() {
        this.$refs.form.validate((valid) => {
          if (valid) {
            const params = Object.assign({}, this.formData)
            params.startDate = params.formDate[0]
            params.endDate = params.formDate[1]
            activityApi.createNewActivity(params)
              .then(response => {
                this.$message({
                  message: response.message,
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
</style>
