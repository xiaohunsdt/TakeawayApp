<template>
  <div class="container">
    <base-card class="container-header">
      <el-form :inline="true" :model="formData" class="demo-form-inline" size="mini">
        <el-form-item label="昵称">
          <el-input placeholder="昵称" v-model="formData.nickName"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button @click="onSearch" type="primary">查询</el-button>
        </el-form-item>
      </el-form>
      <div class="action-bar">
        <el-button @click="onSendCoupon" size="small" type="primary">发放优惠卷</el-button>
      </div>
    </base-card>
    <base-card class="container-main">
      <el-table
        :data="tableData"
        @selection-change="handleSelectionChange"
        class="tb-edit"
        element-loading-text="正在加载中..."
        highlight-current-row
        stripe
        style="width: 100%"
        v-loading="listLoading">
        <el-table-column
          type="selection"
          width="55">
        </el-table-column>
        <el-table-column
          align="center"
          label="ID"
          prop="id"
          width="160">
        </el-table-column>
        <el-table-column
          align="center"
          label="昵称">
          <template v-slot="scope">
            <!--            <div v-if="scope.row.name!==''">{{ scope.row.name }}</div>-->
            <div>{{ scope.row.nickName }}</div>
          </template>
        </el-table-column>
        <el-table-column
          align="center"
          label="等级"
          prop="level">
        </el-table-column>
        <el-table-column
          align="center"
          label="余额"
          prop="money">
        </el-table-column>
        <el-table-column
          align="center"
          label="性别"
          prop="gender">
        </el-table-column>
        <el-table-column
          align="center"
          label="最后登录时间"
          prop="lastLoginDate">
        </el-table-column>
        <el-table-column
          align="center"
          label="加入日期"
          prop="createDate">
        </el-table-column>
        <el-table-column
          label="操作">
          <template v-slot="scope">
            <el-button @click="onDelete(scope.row.id)" size="mini" type="danger">删除</el-button>
          </template>
        </el-table-column>
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
    <generate-coupon-dialog :user-ids="selectedUserIds" ref="generate-coupon-dialog"/>
  </div>
</template>

<script>
  import BaseCard from '@/components/BaseCard'
  import GenerateCouponDialog from '@/components/coupon/generate-dialog'
  import userApi from '@/api/user'

  export default {
    name: 'UserManagement',
    components: {
      BaseCard,
      GenerateCouponDialog
    },
    computed: {
      selectedUserIds() {
        return this.multipleSelection.map(user => user.id)
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
          nickName: null,
          categoryId: null
        },
        listLoading: false,
        tableData: [],
        multipleSelection: []
      }
    },
    created() {
      this.onSearch()
    },
    methods: {
      onSearch() {
        this.listLoading = true
        this.page.current = 1

        userApi.getUserListByPage(this.page, this.formData)
          .then(response => {
            this.tableData = response.records
            this.page.total = parseInt(response.total)
            this.listLoading = false
          }).catch(() => {
          this.listLoading = false
        })
      },
      onDelete(id) {
        this.$confirm('是否确定删除此用户?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          userApi.deleteUser(id)
            .then(() => {
              this.onSearch()
            })
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
      handleSelectionChange(val) {
        this.multipleSelection = val
      },
      onSendCoupon() {
        this.$refs['generate-coupon-dialog'].openDialog()
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

  .action-bar {
    display: flex;
    justify-content: flex-end;
  }
</style>
