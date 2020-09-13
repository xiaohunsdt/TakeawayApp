<template>
  <div class="container">
    <base-card class="container-header">
      <el-row>
        <el-col :span="18">
          <el-form :inline="true" :model="formData" class="demo-form-inline" size="mini">
            <el-form-item label="名称">
              <el-input v-model="formData.name" placeholder="商品名称"></el-input>
            </el-form-item>
            <el-form-item label="分类">
              <el-select v-model="formData.categoryId" placeholder="选择分类">
                <el-option label=" " value=""></el-option>
                <el-option
                    v-for="item in categoryList"
                    :key="item.id"
                    :label="item.name"
                    :value="item.id">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="状态">
              <el-select v-model="formData.state" placeholder="选择状态">
                <el-option label=" " value=""></el-option>
                <el-option label="上架" value="1"></el-option>
                <el-option label="下架" value="0"></el-option>
                <el-option label="缺货" value="-1"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="onSearch">查询</el-button>
            </el-form-item>
          </el-form>
        </el-col>
        <el-col :span="6" style="text-align: right">
          <el-button size="mini" type="success" @click="onCreateNewGoods">添加新商品</el-button>
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
            align="center"
            label="名称"
            prop="name">
        </el-table-column>
        <el-table-column
            align="center"
            label="缩略图"
            width="100">
          <template v-slot="scope">
            <el-button
                v-if="scope.row.thumb===''"
                size="mini"
                type="primary"
                @click="onUploadImg(scope.row)">
              上传
            </el-button>
            <img
                v-else
                :src="$VUE_APP_BASE_API + scope.row.thumb"
                style="height: 30px;width: auto;"
                @click="onUploadImg(scope.row)"/>
          </template>
        </el-table-column>
        <el-table-column
            align="center"
            label="简介"
            prop="desc"
            width="250">
        </el-table-column>
        <el-table-column
            align="center"
            label="分类"
            prop="category">
        </el-table-column>
        <el-table-column
            align="center"
            label="价格"
            prop="price">
        </el-table-column>
        <el-table-column
            align="center"
            label="月销"
            prop="monthSale">
        </el-table-column>
        <el-table-column
            align="center"
            label="优先级"
            prop="index">
        </el-table-column>
        <!--        <el-table-column-->
        <!--          label="评分"-->
        <!--          prop="rate"-->
        <!--          align="center">-->
        <!--        </el-table-column>-->
        <el-table-column
            align="center"
            label="状态"
            prop="state">
        </el-table-column>
        <el-table-column
            align="center"
            label="操作"
            width="170">
          <template v-slot="scope">
            <el-button size="mini" type="primary" @click="onEdit(scope.row)">编辑</el-button>
            <el-button size="mini" type="danger" @click="onDelete(scope.row.id)">删除</el-button>
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
    <goods-dialog
        :category-list="categoryList"
        :dialog-visible.sync="dialogVisible"
        :goods-data.sync="currentGoods"
        @event-success="onSearch"
    />
    <goods-image-dialog
        :dialogVisible.sync="imageUploaderVisible"
        :goodsData.sync="currentGoods"/>
  </div>
</template>

<script>
import BaseCard from '@/components/BaseCard'
import GoodsImageDialog from './components/GoodsImageDialog'
import goodsApi from '@/api/goods'
import categoryApi from '@/api/category'
import GoodsDialog from './components/GoodsDialog'

export default {
  name: 'GoodsManagement',
  components: {
    BaseCard,
    GoodsImageDialog,
    GoodsDialog
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
        categoryId: null,
        state: null
      },
      imageUploaderVisible: false,
      dialogVisible: false,
      listLoading: false,
      tableData: [],
      categoryList: [],
      currentGoods: null
    }
  },
  created() {
    this.onSearch()
    categoryApi.getAllCategory()
        .then(response => {
          this.categoryList = response
        })
  },
  methods: {
    getList() {
      this.listLoading = true

      goodsApi.getGoodsListByPage(this.page, this.formData)
          .then(response => {
            this.tableData = response.records
            this.page.total = parseInt(response.total)
          })
          .finally(() => {
            this.listLoading = false
          })
    },
    onSearch() {
      this.page.current = 1
      this.getList()
    },
    onEdit(row) {
      this.currentGoods = row
      this.dialogVisible = true
    },
    onDelete(id) {
      this.$confirm('是否确定删除此商品?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        goodsApi.deleteGoods(id)
            .then(() => {
              this.getList()
            })
      })
    },
    onCreateNewGoods() {
      this.dialogVisible = true
    },
    onUploadImg(currentGoods) {
      this.currentGoods = currentGoods
      this.imageUploaderVisible = true
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

<style lang="scss">
.el-table th, .el-table td {
  padding: 8px 0px;
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

.el-form-item {
  margin-bottom: unset !important;
}
</style>
