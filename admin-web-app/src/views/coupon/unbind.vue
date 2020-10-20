<template>
  <div class="container">
    <base-card class="container-header">
      <el-form :inline="true" :model="formData" class="demo-form-inline" size="mini">
        <el-form-item label="类型">
          <el-select v-model="formData.couponType" placeholder="请选择优惠卷类型">
            <el-option label="所有" value=""/>
            <el-option label="现金卷" value="1"/>
            <el-option label="折扣卷" value="2"/>
          </el-select>
        </el-form-item>
        <el-form-item label="状态">
          <el-select v-model="formData.couponState" placeholder="请选择优惠卷状态">
            <el-option label="所有" value=""/>
            <el-option label="未使用" value="0"/>
            <el-option label="已使用" value="1"/>
            <el-option label="已过期" value="2"/>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSearch">查询</el-button>
        </el-form-item>
      </el-form>
      <div class="action-bar">
        <el-button size="small" type="primary" @click="exportToExecl">导出数据</el-button>
        <el-button size="small" type="primary" @click="onSendCoupon">发放优惠卷</el-button>
      </div>
    </base-card>
    <base-card class="container-main">
      <el-table
          v-loading="listLoading"
          :data="tableData"
          class="tb-edit"
          element-loading-text="正在加载中..."
          highlight-current-row
          stripe
          style="width: 100%"
          @selection-change="handleSelectionChange">
        <el-table-column
            type="selection"
            width="55">
        </el-table-column>
        <el-table-column type="expand">
          <template v-slot="props">
            <div class="template-expand">
              <el-form class="template-expand-form" label-position="left">
                <el-row>
                  <el-col :span="12">
                    <el-form-item label="允许的分类:">
                      <el-tag
                          v-for="item in props.row.allowCategory"
                          :key="item"
                          class="rule-tag"
                          effect="dark"
                          type="success">
                        {{ item }}
                      </el-tag>
                    </el-form-item>
                    <el-form-item label="限制的分类:">
                      <el-tag
                          v-for="item in props.row.limitCategory"
                          :key="item"
                          class="rule-tag"
                          effect="dark"
                          type="danger">
                        {{ item }}
                      </el-tag>
                    </el-form-item>
                  </el-col>
                  <el-col :span="12">
                    <el-form-item label="允许的商品:">
                      <el-tag
                          v-for="item in props.row.allowGoods"
                          :key="item"
                          class="rule-tag"
                          effect="dark"
                          type="success">
                        {{ item }}
                      </el-tag>
                    </el-form-item>
                    <el-form-item label="限制的商品:">
                      <el-tag
                          v-for="item in props.row.limitGoods"
                          :key="item"
                          class="rule-tag"
                          effect="dark"
                          type="danger">
                        {{ item }}
                      </el-tag>
                    </el-form-item>
                  </el-col>
                </el-row>
              </el-form>
            </div>
          </template>
        </el-table-column>
        <el-table-column
            align="center"
            label="Id"
            prop="id"
            width="160"/>
        <el-table-column
            align="center"
            label="优惠卷名称"
            prop="couponName"/>
        <el-table-column
            align="center"
            label="优惠卷类型">
          <template v-slot="scope">
            {{ scope.row.couponType | couponTypeFormat }}
          </template>
        </el-table-column>
        <el-table-column
            align="center"
            label="优惠卷面值">
          <template v-slot="scope">
            {{ scope.row.couponMoney.toLocaleString() }}
          </template>
        </el-table-column>
        <el-table-column
            align="center"
            label="优惠卷折扣"
            prop="couponDiscount">
          <template v-slot="scope">
            {{ scope.row.couponDiscount }}折
          </template>
        </el-table-column>
        <el-table-column
            align="center"
            label="最低消费">
          <template v-slot="scope">
            {{ scope.row.minimumMoney.toLocaleString() }}
          </template>
        </el-table-column>
        <el-table-column
            align="center"
            label="过期日期"
            prop="expireDate"
            width="100"/>
        <el-table-column
            align="center"
            label="状态">
          <template v-slot="scope">
            <el-tag v-if="scope.row.state === 'UN_USE'" type="success">
              {{ scope.row.state | couponStateFormat }}
            </el-tag>
            <el-tag v-else-if="scope.row.state === 'USED'" type="danger">
              {{ scope.row.state | couponStateFormat }}
            </el-tag>
            <el-tag v-else-if="scope.row.state === 'EXPIRED'" type="info">
              {{ scope.row.state | couponStateFormat }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column
            align="center"
            label="创建时间"
            prop="createDate"
            width="100"/>
        <el-table-column
            align="center"
            label="操作">
          <template v-slot="scope">
            <el-button
                size="mini"
                type="danger"
                @click="onDelete(scope.row.id)">
              删除
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
    <generate-coupon-dialog ref="generate-coupon-dialog"/>
  </div>
</template>

<script>
import BaseCard from '@/components/BaseCard'
import GenerateCouponDialog from '@/components/coupon/GenerateDialog'

import couponApi from '@/api/coupon'
import { formatCouponState, formatCouponType, parseTime } from '@/utils/index'

export default {
  name: 'UnBindCouponManagement',
  components: {
    BaseCard,
    GenerateCouponDialog
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
        couponState: '',
        bindState: 0
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
    getList() {
      this.listLoading = true

      couponApi.getCouponListByPage(this.page, this.formData)
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
    handleSelectionChange(val) {
      this.multipleSelection = val
    },
    onSearch() {
      this.page.current = 1
      this.getList()
    },
    onDelete(id) {
      this.$confirm('是否确定删除此优惠卷?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        couponApi.deleteCoupon(id)
            .then(() => {
              this.getList()
            })
      })
    },
    onSendCoupon() {
      this.$refs['generate-coupon-dialog'].openDialog()
    },
    exportToExecl() {
      if (this.multipleSelection.length > 0) {
        import('@/vendor/Export2Excel').then(excel => {
          // 导出的表头
          const tHeader = ['ID', '优惠卷名称', '优惠卷类型', '优惠卷面值', '优惠卷折扣', '最低消费', '过期日期']
          const filterVal = ['id', 'couponName', 'couponType', 'couponMoney', 'couponDiscount', 'minimumMoney', 'expireDate']
          const data = this.formatJson(filterVal, this.multipleSelection)
          // 导出表头要对应的数据
          excel.export_json_to_excel({
            header: tHeader,
            data,
            filename: '订单查询列表-' + parseTime(new Date(), '{y}-{m}-{d}_{h}:{i}:{s}'),
            autoWidth: true,
            bookType: 'xlsx'
          })
        })
      }
    },
    formatJson(filterVal, jsonData) {
      return jsonData.map(v => filterVal.map(j => {
        return v[j]
      }))
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

.action-bar {
  display: flex;
  justify-content: flex-end;
}

.rule-tag {
  margin-right: 10px;
}
</style>
