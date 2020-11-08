<template>
  <div class="container">
    <base-card class="container-header">
      <el-form :inline="true" :model="formData" class="demo-form-inline" size="mini">
        <el-form-item label="日期">
          <el-date-picker
            v-model="formData.formDate"
            end-placeholder="end date"
            format="yyyy-MM-dd"
            start-placeholder="start date"
            type="daterange"
            value-format="yyyy-MM-dd">
          </el-date-picker>
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
          label="时间"
          prop="createDate">
        </el-table-column>
        <el-table-column
          align="center"
          label="内容"
          prop="ps">
        </el-table-column>
        <el-table-column
          align="center"
          label="金额">
          <template v-slot="props">
            <el-tag :type="props.row.money > 0 ? 'success':'danger'">
              {{ parseInt(props.row.money).toLocaleString() }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column
          align="center"
          label="变更后资金">
          <template v-slot="props">
            {{ parseInt(props.row.afterMoney).toLocaleString() }}
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
import { parseTime } from '@u/index'
import balanceApi from '@a/balance'

export default {
  name: 'BalanceLog',
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
        formDate: [
          new Date(),
          new Date()
        ]
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
      this.page.current = 1
      this.getList()
    },
    getList() {
      this.listLoading = true

      const params = Object.assign({}, this.formData)
      params.startDate = parseTime(params.formDate[0], '{y}-{m}-{d}')
      params.endDate = parseTime(params.formDate[1], '{y}-{m}-{d}')

      balanceApi.getBalanceLogListByPage(this.page, params)
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
