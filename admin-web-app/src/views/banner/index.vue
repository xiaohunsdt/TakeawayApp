<template>
  <div class="container">
    <base-card class="container-header">
      <el-form :inline="true" :model="formData" class="demo-form-inline" size="mini">
        <el-form-item label="显示">
          <el-select v-model="formData.isShow" placeholder="选择显示模式">
            <el-option :value="null" label="所有"/>
            <el-option :value="1" label="可见"/>
            <el-option :value="0" label="不可见"/>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSearch">查询</el-button>
        </el-form-item>
      </el-form>
    </base-card>
    <base-card class="container-main">
      <el-table
          v-loading="listLoading"
          :data="tableData"
          class="tb-edit"
          element-loading-text="正在加载中..."
          highlight-current-row
          stripe
          style="width: 100%">
        <el-table-column
            align="center"
            label="ID"
            prop="id"
            width="200"/>
        <el-table-column
            align="center"
            label="标题"
            prop="title"
            width="200"/>
        <el-table-column
            align="center"
            label="主图">
          <template v-slot="props">
            <img
                v-if="props.row.img!==''"
                :src="$VUE_APP_BASE_API + props.row.img"
                style="height: 40px;width: auto;"/>
          </template>
        </el-table-column>
        <el-table-column
            align="center"
            label="显示">
          <template v-slot="props">
            <el-switch
                v-model="props.row.isShow"
                active-color="#13ce66"
                inactive-color="#ff4949"
                @change="onIsShowChange(props.row)"/>
          </template>
        </el-table-column>
        <el-table-column
            align="center"
            label="Index"
            prop="index">
        </el-table-column>
        <el-table-column
            align="center"
            label="操作">
          <template v-slot="props">
            <el-button size="mini" type="primary" @click="onEdit(props.row.id)">编辑</el-button>
            <el-button size="mini" type="danger" @click="onDelete(props.row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
          :current-page="page.current"
          :page-size="page.size"
          :page-sizes="[15, 50, 100]"
          :total="page.total"
          background
          layout="total, sizes, prev, pager, next, jumper"
          style="margin-top: 15px"
          @current-change="handleCurrentChange"
          @size-change="handleSizeChange">
      </el-pagination>
    </base-card>
  </div>
</template>

<script>
import BaseCard from '@/components/BaseCard'
import bannerApi from '@/api/banner'

export default {
  name: 'BannerManagement',
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
    getList() {
      this.listLoading = true

      bannerApi.getBannerListByPage(this.page, this.formData)
          .then(response => {
            this.tableData = response.records
            this.page.total = parseInt(response.total)
          })
          .finally(() => {
            this.listLoading = false
          })
    },
    handleSizeChange(val) {
      this.page.size = val
      this.getList()
    },
    handleCurrentChange(val) {
      this.page.current = val
      this.getList()
    },
    onSearch() {
      this.page.current = 1
      this.getList()
    },
    onIsShowChange(banner) {
      bannerApi.changeIsShow(banner.id, banner.isShow)
          .then(response => {
            this.$message({
              message: response.message,
              type: 'success'
            })
          })
    },
    onEdit(id) {
      this.$router.push({
        path: '/banner/edit', query: { bannerId: id }
      })
    },
    onDelete(id) {
      this.$confirm('是否确定删除此横幅?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        bannerApi.deleteBanner(id)
            .then(() => {
              this.getList()
            })
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

.el-form-item {
  margin-bottom: unset !important;
}
</style>
