<template>
  <div class="container">
    <base-card class="container-header">
      <el-form :inline="true" :model="formData" class="demo-form-inline" size="mini">
        <el-form-item label="用户名">
          <el-input placeholder="昵称" v-model="formData.nickName"></el-input>
        </el-form-item>
        <el-form-item label="日期">
          <el-date-picker
            end-placeholder="end date"
            format="yyyy-MM-dd"
            start-placeholder="start date"
            type="daterange"
            v-model="formData.formDate"
            value-format="yyyy-MM-dd">
          </el-date-picker>
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
        <el-table-column type="expand">
          <template v-slot="props">
            <base-card v-if="props.row.comment">
              {{ props.row.comment }}
            </base-card>
          </template>
        </el-table-column>
        <el-table-column
          align="center"
          label="用户名"
          prop="userName">
        </el-table-column>
        <el-table-column
          align="center"
          label="订单编号"
          prop="orderId">
        </el-table-column>
        <el-table-column
          align="center"
          label="口味评分">
          <template v-slot="scope">
            <el-rate
              v-model="scope.row.delicious"
              :score-template="scope.row.delicious.toString()"
              disabled
              show-score
              text-color="#ff9900">
            </el-rate>
          </template>
        </el-table-column>
        <el-table-column
          align="center"
          label="配送评分">
          <template v-slot="scope">
            <el-rate
              v-model="scope.row.express"
              :score-template="scope.row.express.toString()"
              disabled
              show-score
              text-color="#ff9900">
            </el-rate>
          </template>
        </el-table-column>
        <el-table-column
          align="center"
          label="服务评分">
          <template v-slot="scope">
            <el-rate
              v-model="scope.row.service"
              :score-template="scope.row.service.toString()"
              disabled
              show-score
              text-color="#ff9900">
            </el-rate>
          </template>
        </el-table-column>
        <el-table-column
          align="center"
          label="评论时间"
          prop="createDate">
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
  import commentApi from '@/api/comment'

  export default {
    name: 'CommentManagement',
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
          nickName: null,
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
        this.listLoading = true
        this.page.current = 1

        const params = Object.assign({}, this.formData)
        params.startDate = params.formDate[0]
        params.endDate = params.formDate[1]

        commentApi.getCommentListByPage(this.page, params)
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
