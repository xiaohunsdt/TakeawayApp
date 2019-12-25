<template>
  <div class="container">
    <base-card class="container-main">
      <div>
        <el-form :model="formData" label-width="80px" ref="form" style="width: 460px">
          <el-form-item label="活动名称">
            <el-input v-model="formData.name"></el-input>
          </el-form-item>

          <el-form-item label="活动时间">
            <el-col :span="11">
              <el-date-picker placeholder="选择日期" style="width: 100%;" type="date" v-model="formData.startDate" />
            </el-col>
            <el-col :span="2" class="line">-</el-col>
            <el-col :span="11">
              <el-time-picker placeholder="选择时间" style="width: 100%;" v-model="formData.endDate" />
            </el-col>
          </el-form-item>
        </el-form>
      </div>
      <div>
        <tinymce :height="300" v-model="formData.content"/>
      </div>
    </base-card>
  </div>
</template>

<script>
  import BaseCard from '@/components/BaseCard'
  import Tinymce from '@/components/Tinymce'

  export default {
    name: 'EditActivity',
    components: {
      BaseCard,
      Tinymce
    },
    data() {
      return {
        formData: {
          title: '',
          content: '',
          mainImg: '',
          startDate: new Date(),
          endDate: new Date()
        }
      }
    },
    methods: {}
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
