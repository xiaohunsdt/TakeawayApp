<template>
  <div class="container">
    <base-card class="header">
      <el-row>
        <el-col :span="18">
          <el-form :inline="true" :model="formData" class="demo-form-inline" size="mini">
            <el-form-item label="名称">
              <el-input placeholder="分类名称" v-model="formData.name"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button @click="onSearch" type="primary">查询</el-button>
            </el-form-item>
          </el-form>
        </el-col>
        <el-col :span="6" style="text-align: right">
          <el-button @click="onCreate" size="mini" type="success">创建新分类</el-button>
        </el-col>
      </el-row>
    </base-card>
    <base-card>
      <el-table
        :data="tableData"
        border
        element-loading-text="正在加载中..."
        highlight-current-row
        stripe
        style="width: 100%"
        v-loading="listLoading"
      >
        <el-table-column
          label="ID"
          prop="id"
          width="250"
        >
        </el-table-column>
        <el-table-column
          label="名称"
          prop="name"
        >
        </el-table-column>
      </el-table>
    </base-card>
  </div>
</template>

<script>
    import BaseCard from '@/components/BaseCard/BaseCard'
    import categoryApi from '@/api/category'

    export default {
        name: 'CategoryManagement',
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
                    name: null
                },
                listLoading: false,
                tableData: []
            }
        },
        methods: {
            onSearch() {
                this.listLoading = true
                categoryApi.getCategoryListByPage(this.page, this.formData)
                    .then(response => {
                        console.log(response)
                        this.tableData = response.records
                        this.page.total = parseInt(response.total)
                        this.listLoading = false
                    }).catch(() => {
                    this.listLoading = false
                })
            },
            onCreate() {
                console.log('asd')
            }
        },
        created() {
            this.onSearch()
        }
    }
</script>

<style lang="scss">
  .el-form-item {
    margin-bottom: unset !important;
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

    .header {
      margin-bottom: 30px;
    }
  }
</style>
