<template>
  <el-dialog
      :close-on-click-modal="false"
      :show-close="false"
      :visible.sync="dialogVisible">
    <el-upload
        ref="uploader"
        :action="$VUE_APP_BASE_API + '/api/admin/uploadImg'"
        :headers="authHeader"
        :on-preview="handlePictureCardPreview"
        :on-remove="handleRemove"
        :on-success="onSuccess"
        list-type="picture-card">
      <i class="el-icon-plus"></i>
    </el-upload>
    <el-dialog :visible.sync="imgDetailDialogVisible">
      <img :src="imgDetailUrl" alt="" width="100%">
    </el-dialog>
    <div slot="footer" class="dialog-footer">
      <el-button @click="closeWindow">关闭</el-button>
    </div>
  </el-dialog>
</template>

<script>
import { getToken } from '@/utils/auth'
import goodsApi from '@/api/goods'

export default {
  name: 'GoodsImageDialog',
  props: {
    dialogVisible: {
      type: Boolean,
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
      imgDetailUrl: '',
      imgDetailDialogVisible: false
    }
  },
  computed: {
    authHeader() {
      return {
        Authorization: `Bearer ${getToken()}`
      }
    }
  },
  watch: {
    dialogVisible(newVal, oldVal) {
      if (!newVal) {
        this.$refs.uploader.clearFiles()
      }
    }
  },
  methods: {
    onSuccess(response, file, fileList) {
      const thumb = `/upload/images/${response.message}`
      goodsApi.updateGoodsThumb(this.goodsData.id, thumb).then(res => {
        this.$message.success('上传成功')
      })
    },
    handleRemove(file, fileList) {
      console.log(file, fileList)
    },
    handlePictureCardPreview(file) {
      this.imgDetailUrl = file.url
      this.imgDetailDialogVisible = true
    },
    closeWindow() {
      this.$emit('update:dialogVisible', false)
      this.$emit('update:goodsData', null)
    }
  }
}
</script>
<style>
.el-dialog {
  max-width: 800px;
}

.el-upload, .el-upload-dragger {
  width: 100%;
}
</style>
<style scoped>

</style>
