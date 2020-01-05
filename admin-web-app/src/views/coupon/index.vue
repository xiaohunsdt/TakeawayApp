<template>
  <div class="container">
    <base-card class="container-header">
      <el-form :inline="true" :model="formData" class="demo-form-inline" size="mini">
        <el-form-item label="昵称">
          <el-input placeholder="请输入昵称" v-model="formData.nickName"></el-input>
        </el-form-item>
        <el-form-item label="类型">
          <el-select placeholder="请选择优惠卷类型" v-model="formData.couponType">
            <el-option label="所有" value=""/>
            <el-option label="现金卷" value="1"/>
            <el-option label="折扣卷" value="2"/>
          </el-select>
        </el-form-item>
        <el-form-item label="状态">
          <el-select placeholder="请选择优惠卷状态" v-model="formData.couponState">
            <el-option label="所有" value=""/>
            <el-option label="未使用" value="0"/>
            <el-option label="已使用" value="1"/>
            <el-option label="已过期" value="2"/>
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
  import couponApi from '@/api/coupon'
  import { formatCouponState, formatCouponType } from '@/utils/index'

  export default {
    name: 'CouponManagement',
    components: {
      BaseCard
    },
    filters: {
      couponTypeFormat: function(value) {
        return formatCouponType(value)
      },
      couponStateFormat: function(value) {
        return formatCouponState(value)
      }
    },
    data() {
      return {
        page: {
          current: 1,
          size: 15,
          total: 0
        },
        formData: {
          nickName: '',
          couponType: '',
          couponState: ''
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
        couponApi.getCouponListByPage(this.page, this.formData)
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
      },
      onDelete(id) {
        this.$confirm('是否确定删除此活动?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          getCouponListByPage.deleteActivity(id)
            .then(() => {
              this.onSearch()
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
