<template>
  <div class="container">
    <base-card class="container-header">
      <el-form :inline="true" :model="formData" class="demo-form-inline" size="mini">
        <el-form-item label="显示">
          <el-select placeholder="选择显示模式" v-model="formData.isShow">
            <el-option :value="null" label="所有"/>
            <el-option :value="1" label="可见"/>
            <el-option :value="0" label="不可见"/>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button @click="onSearch" type="primary">查询</el-button>
        </el-form-item>
      </el-form>
    </base-card>
    <base-card class="container-main">
      <el-table
        :data="tableData"
        class="tb-edit"
        element-loading-text="正在加载中..."
        highlight-current-row
        stripe
        style="width: 100%"
        v-loading="listLoading">

      </el-table>
      <el-pagination
        :current-page="page.current"
        :page-size="page.size"
        :page-sizes="[15, 50, 100]"
        :total="page.total"
        @current-change="handleCurrentChange"
        @size-change="handleSizeChange"
        background
        layout="total, sizes, prev, pager, next, jumper"
        style="margin-top: 15px">
      </el-pagination>
    </base-card>
  </div>
</template>

<script>
  import BaseCard from '@/components/BaseCard'
  import activityApi from '@/api/activity'

  export default {
    name: 'ActivityManagement',
    components: {
      BaseCard
    },
    data() {
      return {
        page: {
          current: 1,
          size: 15,
          total: 0
        },
        formData: {
          isShow: null
        },
        listLoading: false,
        tableData: []
      }
    },
    created() {
      this.onSearch()
    },
    methods: {
      onSearch() {
        this.listLoading = true
        activityApi.getActivityListByPage(this.page, this.formData)
          .then(response => {
            this.tableData = response.records
            this.page.total = parseInt(response.total)
            this.listLoading = false
          }).catch(() => {
          this.listLoading = false
        })
      },
      handleSizeChange(val) {
        this.page.size = val
        this.onSearch()
      },
      handleCurrentChange(val) {
        this.page.current = val
        this.onSearch()
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

  .el-form-item {
    margin-bottom: unset !important;
  }
</style>
