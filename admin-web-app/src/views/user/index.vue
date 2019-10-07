<template>
  <div class="container">
    <base-card class="container-header">
      <el-form :inline="true" :model="formData" class="demo-form-inline" size="mini">
        <el-form-item label="用户名">
          <el-input placeholder="用户名" v-model="formData.name"></el-input>
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
        <el-table-column
          label="用户名/昵称"
          align="center">
          <template v-slot="scope">
            <div v-if="scope.row.name !==''">{{ scope.row.name }}</div>
            <div>{{ scope.row.nickName }}</div>
          </template>
        </el-table-column>
        <el-table-column
          label="头像"
          align="center">
          <template v-slot="scope">
            <img
              :src="scope.row.avatar"
              style="height: 30px;width: auto;"
              v-if="scope.row.avatar !==''"/>
          </template>
        </el-table-column>
        <el-table-column
          label="等级"
          prop="level"
          align="center">
        </el-table-column>
        <el-table-column
          label="余额"
          prop="money"
          align="center">
        </el-table-column>
        <el-table-column
          label="性别"
          prop="gender"
          align="center">
        </el-table-column>
        <el-table-column
          label="最后登录时间"
          prop="lastLoginDate"
          align="center">
        </el-table-column>
        <el-table-column
          label="加入日期"
          prop="createDate"
          align="center">
        </el-table-column>
        <el-table-column
          label="操作"
          width="150">
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
  </div>
</template>

<script>
  import BaseCard from '@/components/BaseCard/BaseCard'
  import userApi from '@/api/user'

  export default {
    name: 'UserManagement',
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
          name: null,
          categoryId: null
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
