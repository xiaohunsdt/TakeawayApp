<template>
  <div class="container">
    <base-card class="container-header">
      <el-row>
        <el-col :span="18">
          <el-form :inline="true" :model="formData" class="demo-form-inline" size="mini">
            <el-form-item label="名称">
              <el-input placeholder="商品名称" v-model="formData.name"></el-input>
            </el-form-item>
            <el-form-item label="分类">
              <el-select placeholder="选择分类" v-model="formData.categoryId">
                <el-option label=" " value=""></el-option>
                <el-option
                  :key="item.id"
                  :label="item.name"
                  :value="item.id"
                  v-for="item in categoryList">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item>
              <el-button @click="onSearch" type="primary">查询</el-button>
            </el-form-item>
          </el-form>
        </el-col>
        <el-col :span="6" style="text-align: right">
          <add-goods-dialog
            :category-list="categoryList"
            @createSuccess="onSearch"/>
        </el-col>
      </el-row>
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
          label="名称"
          prop="name">
        </el-table-column>
        <el-table-column
          label="缩略图"
          width="100">
          <template scope="scope">
            <img :src="scope.row.thumb" alt="" style="height: 30px;width: auto;">
          </template>
        </el-table-column>
        <el-table-column
          label="简介"
          prop="desc"
          width="250">
        </el-table-column>
        <el-table-column
          label="分类"
          prop="category">
        </el-table-column>
        <el-table-column
          label="价格"
          prop="price">
        </el-table-column>
        <el-table-column
          label="月销"
          prop="monthSale">
        </el-table-column>
        <el-table-column
          label="评分"
          prop="rate">
        </el-table-column>
        <el-table-column
          label="状态"
          prop="state">
        </el-table-column>
        <el-table-column
          label="操作"
          width="150">
          <template scope="scope">
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
    import goodsApi from '@/api/goods'
    import categoryApi from '@/api/category'
    import AddGoodsDialog from './components/AddGoodsDialog'

    export default {
        name: 'GoodsManagement',
        components: {
            BaseCard,
            AddGoodsDialog
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
                tableData: [],
                categoryList: []
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
            onSearch() {
                this.listLoading = true
                goodsApi.getGoodsListByPage(this.page, this.formData)
                    .then(response => {
                        this.tableData = response.records
                        this.page.total = parseInt(response.total)
                        this.listLoading = false
                    }).catch(() => {
                    this.listLoading = false
                })
            },
            onEdit(index, row) {
                goodsApi.updateGoods(row)
                    .then((response) => {
                        this.$message({
                            message: response.message,
                            type: 'success'
                        })
                    })
            },
            onDelete(id) {
                this.$confirm('是否确定删除此商品?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    goodsApi.deleteGoods(id)
                        .then(() => {
                            this.onSearch()
                        })
                })
            },
            handleSizeChange(val) {
                this.onSearch()
            },
            handleCurrentChange(val) {
                this.onSearch()
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
</style>
