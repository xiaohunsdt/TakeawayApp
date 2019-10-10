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
        @expand-change="getOrderDetail"
        class="tb-edit"
        element-loading-text="正在加载中..."
        highlight-current-row
        stripe
        style="width: 100%"
        v-loading="listLoading">
        <el-table-column type="expand">
          <template v-slot="props">
            <div class="order-expand" v-if="props.row.detail.hasOwnProperty('address')">
              <base-card>
                <el-table
                  :data="props.row.detail.orderItemList"
                  :show-header="false"
                  stripe
                  style="width: 100%">
                  <el-table-column
                    prop="goodsName">
                  </el-table-column>
                  <el-table-column>
                    <template v-slot="scope">
                      <img
                        :src="uploadUrl + scope.row.goodsThumb"
                        style="height: 30px;width: auto;"/>
                    </template>
                  </el-table-column>
                  <el-table-column>
                    <template v-slot="scope">
                      ₩ {{ scope.row.goodsPrice.toLocaleString() }}
                    </template>
                  </el-table-column>
                  <el-table-column>
                    <template v-slot="scope">
                      x {{ scope.row.goodsCount }}
                    </template>
                  </el-table-column>
                </el-table>
              </base-card>
              <el-form class="order-expand-form" label-position="left">
                <el-row>
                  <el-col :span="12">
                    <el-form-item label="订单 ID">
                      <span>{{ props.row.id }}</span>
                    </el-form-item>
                    <el-form-item label="总金额">
                      <span>{{ props.row.allPrice }}</span>
                    </el-form-item>
                    <el-form-item label="优惠">
                      <span>{{ props.row.discountedPrices }}</span>
                      <span v-if="props.row.discount !=''">({{ props.row.discount }}折)</span>
                    </el-form-item>
                    <el-form-item label="实际金额">
                      <span>{{ props.row.realPrice }}</span>
                    </el-form-item>
                  </el-col>
                  <el-col :span="12">
                    <el-form-item label="地址">
                      <div>{{ props.row.detail.address.address }} ({{ props.row.detail.address.detail }})</div>
                    </el-form-item>
                    <el-form-item label="联系方式">
                      <span>{{ props.row.detail.address.phone }}</span>
                    </el-form-item>
                  </el-col>
                </el-row>
              </el-form>
            </div>
          </template>
        </el-table-column>
        <el-table-column
          align="center"
          label="编号"
          prop="number">
        </el-table-column>
        <el-table-column
          align="center"
          label="用户"
          prop="user.name">
        </el-table-column>
        <el-table-column
          align="center"
          label="商品数量"
          prop="goodsCount">
        </el-table-column>
        <el-table-column
          align="center"
          label="总金额"
          prop="allPrice">
        </el-table-column>
        <el-table-column
          align="center"
          label="折扣/抵扣金额">
          <template v-slot="scope">
            <div v-if="scope.row.discount !==''">{{ scope.row.discount }}</div>
            <div>₩ {{ scope.row.discountedPrices }}</div>
          </template>
        </el-table-column>
        <el-table-column
          align="center"
          label="实际金额"
          prop="realPrice">
        </el-table-column>
        <el-table-column
          align="center"
          label="支付方式"
          prop="paymentWay">
        </el-table-column>
        <el-table-column
          align="center"
          label="支付状态"
          prop="payState">
        </el-table-column>
        <el-table-column
          align="center"
          label="订单状态"
          prop="orderState">
        </el-table-column>
        <el-table-column
          align="center"
          label="创建时间"
          prop="createDate">
        </el-table-column>
        <el-table-column
          align="center"
          label="操作">
          <template v-slot="scope">
            <el-button @click="onEdit(scope.row.id)" size="mini" type="primary">编辑</el-button>
            <!--            <el-button size="mini" type="primary">接单</el-button>-->
            <!--            <el-button size="mini" type="primary">配送</el-button>-->
            <!--            <el-button size="mini" type="primary">完成</el-button>-->
            <!--            <el-button size="mini" type="primary">取消</el-button>-->
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
  import orderApi from '@/api/order'
  import { getServerUrl } from '@/utils/sys'

  export default {
    name: 'OrderManagement',
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
    computed: {
      uploadUrl() {
        return getServerUrl()
      }
    },
    created() {
      this.onSearch()
    },
    methods: {
      onSearch() {
        this.listLoading = true
        orderApi.getOrderListByPage(this.page, this.formData)
          .then(response => {
            const datas = response.records
            datas.forEach(item => {
              item.detail = {}
            })
            this.tableData = datas
            this.page.total = parseInt(response.total)
            this.listLoading = false
          }).catch(() => {
          this.listLoading = false
        })
      },
      async getOrderDetail(row, expandedRows) {
        const currentRow = expandedRows.find(item => item.id === row.id)
        // if (currentRow !== undefined && !currentRow.hasOwnProperty('detail')) {
        if (currentRow !== undefined) {
          await orderApi.getOrderDetail(row.id)
            .then(response => {
              this.$set(currentRow, 'detail', response)
            })
        }
      },
      onEdit(id) {
        console.log(id)
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

<style lang="scss">
  .el-table__expanded-cell {
    padding: 20px 25px !important;
  }

  .order-expand {
    font-size: 0;

    form {
      label {
        width: 90px;
        color: #99a9bf;
      }

      .el-form-item {
        margin-right: 0;
        margin-bottom: 0;
      }
    }

    &-form {
      padding: 0px 10px;
    }
  }
</style>

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
