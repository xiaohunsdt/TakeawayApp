<template>
  <div class="container">
    <base-card class="container-header">
      <el-form :inline="true" :model="formData" class="demo-form-inline" size="mini">
        <el-form-item label="类型">
          <el-select placeholder="请选择优惠卷类型" v-model="formData.couponType">
            <el-option label="所有" value=""/>
            <el-option label="现金卷" value="1"/>
            <el-option label="折扣卷" value="2"/>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button @click="onSearch" type="primary">查询</el-button>
        </el-form-item>
      </el-form>
      <div class="action-bar">
        <el-button @click="onCreateNewTemplate" size="small" type="primary">添加新模板</el-button>
      </div>
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
        <el-table-column type="expand">
          <template v-slot="props">
            <div class="template-expand">
              <el-form class="template-expand-form" label-position="left">
                <el-row>
                  <el-col :span="12">
                    <el-form-item label="允许的分类:">
                      <el-tag
                        :key="item"
                        class="rule-tag"
                        effect="dark"
                        type="success"
                        v-for="item in props.row.allowCategory">
                        {{ item }}
                      </el-tag>
                    </el-form-item>
                    <el-form-item label="限制的分类:">
                      <el-tag
                        :key="item"
                        class="rule-tag"
                        effect="dark"
                        type="danger"
                        v-for="item in props.row.limitCategory">
                        {{ item }}
                      </el-tag>
                    </el-form-item>
                  </el-col>
                  <el-col :span="12">
                    <el-form-item label="允许的商品:">
                      <el-tag
                        :key="item"
                        class="rule-tag"
                        effect="dark"
                        type="success"
                        v-for="item in props.row.allowGoods">
                        {{ item }}
                      </el-tag>
                    </el-form-item>
                    <el-form-item label="限制的商品:">
                      <el-tag
                        :key="item"
                        class="rule-tag"
                        effect="dark"
                        type="danger"
                        v-for="item in props.row.limitGoods">
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
          label="优惠卷面值"
          prop="couponMoney"/>
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
          label="最低消费"
          prop="minimumMoney"/>
        <el-table-column
          align="center"
          label="过期天数"
          prop="expireDays"/>
        <el-table-column
          align="center"
          label="创建时间"
          prop="createDate"/>
        <el-table-column
          align="center"
          label="操作"
          width="170">
          <template v-slot="scope">
            <el-button @click="onEdit(scope.row.id)" size="mini" type="primary">编辑</el-button>
            <el-button
              @click="onDelete(scope.row.id)"
              size="mini"
              type="danger">
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
  import couponTemplateApi from '@/api/coupon-template'
  import { formatCouponType } from '@/utils/index'

  export default {
    name: 'CouponTemplateManagement',
    components: {
      BaseCard
    },
    filters: {
      couponTypeFormat: function(value) {
        return formatCouponType(value)
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
          couponType: ''
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
        this.page.current = 1

        couponTemplateApi.getTemplateListByPage(this.page, this.formData)
          .then(response => {
            this.tableData = response.records
            this.page.total = parseInt(response.total)
            this.listLoading = false
          }).catch(() => {
          this.listLoading = false
        })
      },
      onCreateNewTemplate() {
        this.$router.push({
          path: './template/edit'
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
      onEdit(id) {
        this.$router.push({
          path: './template/edit', query: { templateId: id }
        })
      },
      onDelete(id) {
        this.$confirm('是否确定删除此活动?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          couponTemplateApi.deleteTemplate(id)
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

  .action-bar {
    display: flex;
    justify-content: flex-end;
  }

  .rule-tag {
    margin-right: 10px;
  }
</style>
