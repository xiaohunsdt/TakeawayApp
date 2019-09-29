<template>
  <el-dialog
    :close-on-click-modal="false"
    :show-close="false"
    :visible.sync="dialogVisible">
    <el-upload
      :action="uploadUrl"
      :headers="authHeader"
      :on-preview="handlePictureCardPreview"
      :on-remove="handleRemove"
      :on-success="onSuccess"
      list-type="picture-card"
      ref="uploader">
      <i class="el-icon-plus"></i>
    </el-upload>
    <el-dialog :visible.sync="imgDetaildialogVisible">
      <img :src="imgDetailUrl" alt="" width="100%">
    </el-dialog>
    <div class="dialog-footer" slot="footer">
      <el-button @click="$emit('update:dialogVisible', false)">关闭</el-button>
    </div>
  </el-dialog>
</template>

<script>
    import { getToken } from '@/utils/auth'
    import { getServerUrl } from '@/utils/sys'
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
                required: false
            }
        },
        computed: {
            uploadUrl() {
                return getServerUrl() + '/api/admin/uploadImg'
            },
            authHeader() {
                return {
                    Authorization: `Bearer ${getToken()}`
                }
            }
        },
        data() {
            return {
                imgDetailUrl: '',
                imgDetaildialogVisible: false
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
                goodsApi.updateGoodsThumb(this.goodsData.goodsId, thumb).then(response => {
                    this.$message({
                        message: '上传成功',
                        type: 'success'
                    })
                })
            },
            handleRemove(file, fileList) {
                console.log(file, fileList)
            },
            handlePictureCardPreview(file) {
                this.imgDetailUrl = file.url
                this.imgDetaildialogVisible = true
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
