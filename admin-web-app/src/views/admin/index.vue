<template>
  <div class="container">
    <base-card class="container-header">
      <el-row>
        <el-col :span="18">
          <el-form :inline="true" :model="formData" class="demo-form-inline" size="mini">
            <el-form-item label="级别">
              <el-select v-model="formData.type" placeholder="选择级别">
                <el-option :value="null" label="所有"/>
                <el-option :value="2" label="店长"/>
                <el-option :value="3" label="接单员"/>
                <el-option :value="4" label="外卖员"/>
              </el-select>
            </el-form-item>
            <el-form-item label="状态">
              <el-select v-model="formData.state" placeholder="选择状态">
                <el-option :value="null" label="所有"/>
                <el-option :value="1" label="正常"/>
                <el-option :value="0" label="冻结"/>
              </el-select>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="onSearch">查询</el-button>
            </el-form-item>
          </el-form>
        </el-col>
        <el-col :span="6" style="text-align: right">
          <el-button size="mini" type="success" @click="onCreateNewAdmin">添加新管理员</el-button>
        </el-col>
      </el-row>
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
            label="账号"
            prop="userName"
            width="200"/>
        <el-table-column
            align="center"
            label="级别"
            prop="level"
            width="200"/>
        <el-table-column
            align="center"
            label="状态"
            prop="state"
            width="200"/>
        <el-table-column
            align="center"
            label="最后登陆时间"
            prop="loginDate"/>
        <el-table-column
            align="center"
            label="操作">
          <template v-slot="props">
            <el-button size="mini" type="primary" @click="onEdit(props.row.id)">编辑</el-button>
            <!--            <el-button @click="onDelete(props.row.id)" size="mini" type="danger">删除</el-button>-->
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
    <admin-dialog ref="admin-dialog" @updated-admin="onUpdatedAdmin"/>
  </div>
</template>

<script>
import BaseCard from '@/components/BaseCard'
import AdminDialog from './components/AdminDialog'
import adminApi from '@/api/admin'

export default {
  name: 'AdminManagement',
  components: {
    BaseCard,
    AdminDialog
  },
  data() {
    return {
      page: {
        current: 1,
        size: 15,
        total: 0
      },
      formData: {
        type: null,
        state: null
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
      adminApi.getSubAdminByPage(this.page, this.formData)
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
    onCreateNewAdmin() {
      this.$refs['admin-dialog'].openDialog()
    },
    onEdit(id) {
      this.$refs['admin-dialog'].openDialog(id)
    },
    onUpdatedAdmin() {
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
