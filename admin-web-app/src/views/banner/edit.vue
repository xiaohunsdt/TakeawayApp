<template>
  <div class="container">
    <base-card class="container-main">
      <div>
        <el-form :model="formData" :rules="rules" label-width="80px" ref="form" style="width: 460px">
          <el-form-item label="横幅名称" prop="title">
            <el-input v-model="formData.title"></el-input>
          </el-form-item>
          <el-form-item label="跳转地址">
            <el-input v-model="formData.pagePath"></el-input>
          </el-form-item>
          <el-form-item label="Index">
            <el-input v-model.number="formData.index"></el-input>
          </el-form-item>
          <el-form-item label="横幅主图">
            <el-upload
                :action="$VUE_APP_BASE_API + '/api/admin/banner/uploadImg'"
                :headers="authHeader"
                :on-success="onUploadImgSuccess">
              <el-button size="small" type="primary">点击上传</el-button>
            </el-upload>
            <img :src="$VUE_APP_BASE_API + formData.img" style="height: 120px" v-if="formData.img!==''"/>
          </el-form-item>
        </el-form>
      </div>
      <div style="text-align: center;margin-top: 15px">
        <el-button @click="saveBanner" round size="medium" type="success">保存</el-button>
      </div>
    </base-card>
  </div>
</template>

<script>
  import { getToken } from '@/utils/auth'
  import { getQueryObject } from '@/utils/index'
  import bannerApi from '@/api/banner'
  import BaseCard from '@/components/BaseCard'

  export default {
    name: 'EditBanner',
    components: {
      BaseCard
    },
    computed: {
      authHeader() {
        return {
          Authorization: `Bearer ${getToken()}`
        }
      }
    },
    data() {
      return {
        formData: {
          title: '',
          pagePath: '',
          img: '',
          index: 0
        },
        rules: {
          title: [
            { required: true, message: '请输入标题', trigger: 'blur' }
          ]
        }
      }
    },
    created() {
      this.init()
    },
    activated() {
      this.init()
    },
    methods: {
      init() {
        const query = getQueryObject()
        if (query.bannerId) {
          bannerApi.getBannerById(query.bannerId)
              .then(res => {
                this.formData = res
              })
        }
      },
      onUploadImgSuccess(response, file, fileList) {
        this.formData.img = `/upload/images/banner/${response.message}`
      },
      saveBanner() {
        this.$refs.form.validate((valid) => {
          if (valid) {
            const params = Object.assign({}, this.formData)
            bannerApi.createNewBanner(params)
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
