<template>
  <div class="container">
    <base-card class="container-header">
      <el-row>
        <el-col :span="18">
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
        </el-col>
        <el-col :span="6" style="text-align: right">
          <el-button v-permission="['SHOP_MANAGER']" size="mini" type="success" @click="onApplyWithdraw">申请提现</el-button>
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
          prop="id">
        </el-table-column>
        <el-table-column
          v-if="userData.level === 'SUPER_MANAGER'"
          align="center"
          label="店铺名"
          prop="storeName">
        </el-table-column>
        <el-table-column
          align="center"
          label="提现金额">
          <template v-slot="props">
            {{ (props.row.money - props.row.fee).toLocaleString() }}
            ({{ props.row.money.toLocaleString() }})
          </template>
        </el-table-column>
        <el-table-column
          align="center"
          label="手续费">
          <template v-slot="props">
            {{ props.row.fee.toLocaleString() }}
          </template>
        </el-table-column>
        <el-table-column
          align="center"
          label="备注"
          prop="ps">
        </el-table-column>
        <el-table-column
          align="center"
          label="状态">
          <template v-slot="props">
            {{ props.row.state | formatState }}
          </template>
        </el-table-column>
        <el-table-column
          align="center"
          label="操作">
          <template v-slot="scope">
            <el-button v-if="scope.row.state === 'WAITING_PROCESS'" v-permission="['SHOP_MANAGER']" size="mini" type="primary" @click="onCancel(scope.row.id)">
              取消
            </el-button>
<!--            <el-button v-if="scope.row.state === 'REJECT' || scope.row.state === 'CANCEL'" size="mini" v-permission="['SHOP_MANAGER']" type="danger" @click="onDelete(scope.row.id)">-->
<!--              删除-->
<!--            </el-button>-->
            <el-button v-if="scope.row.state === 'WAITING_PROCESS'" v-permission="['SUPER_MANAGER']" size="mini" type="danger" @click="onReject(scope.row.id)">
              拒绝
            </el-button>
            <el-button v-if="scope.row.state === 'WAITING_PROCESS'" v-permission="['SUPER_MANAGER']" size="mini" type="success" @click="onHandle(scope.row.id)">
              完成
            </el-button>
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
    <apply-withdraw-dialog ref="apply-withdraw-dialog" @apply-success="onSearch()"/>
  </div>
</template>

<script>
import BaseCard from '@c/BaseCard/index'
import ApplyWithdrawDialog from './components/ApplyWithdrawDialog'
import { parseTime } from '@u/index'
import balanceApi from '@a/balance'
import permission from '@/directive/permission/index.js'
import { mapGetters } from 'vuex'

export default {
  name: 'WithdrawLog',
  components: {
    BaseCard,
    ApplyWithdrawDialog
  },
  directives: { permission },
  filters: {
    formatState(state) {
      let str = ''
      switch (state) {
        case 'WAITING_PROCESS':
          str = '等待处理'
          break
        case 'FINISH':
          str = '已完成'
          break
        case 'REJECT':
          str = '拒绝'
          break
        case 'CANCEL':
          str = '取消'
          break
      }
      return str
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
        formDate: [
          new Date(),
          new Date()
        ]
      },
      listLoading: false,
      tableData: []
    }
  },
  computed: {
    ...mapGetters([
      'userData'
    ])
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
      this.tableData = []
      const params = Object.assign({}, this.formData)
      params.startDate = parseTime(params.formDate[0], '{y}-{m}-{d}')
      params.endDate = parseTime(params.formDate[1], '{y}-{m}-{d}')

      balanceApi.getWithdrawLogListByPage(this.page, params)
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
    onApplyWithdraw() {
      this.$refs['apply-withdraw-dialog'].openWindow()
    },
    onCancel(id) {
      balanceApi.cancelWithdraw(id)
        .then(response => {
          this.$message.success(response.message)
          this.getList()
        })
    },
    onDelete(id) {
      balanceApi.deleteWithdraw(id)
        .then(response => {
          this.$message.success(response.message)
          this.getList()
        })
    },
    onHandle(id) {
      balanceApi.handleWithdraw(id)
        .then(response => {
          this.$message.success(response.message)
          this.getList()
        })
    },
    onReject(id) {
      this.$prompt('请输入备注信息', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消'
      }).then(({ value }) => {
        balanceApi.rejectWithdraw(id, value)
          .then(response => {
            this.$message.success(response.message)
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
</style>
