<template>
  <div>
    <search-bar @search="onSearch" background="#FFD200"></search-bar>
    <div class="container-contain" v-if="goodsList.length > 0">
      <main-goods-card
        :food="food"
        :key="food.id"
        v-for="food in goodsList"/>
    </div>
    <div class="none-content-div" v-if="goodsList.length === 0">
      <img mode="aspectFit" src="/static/images/none/no_search.png" style="width: 10rem">
      <div style="margin-top: .5rem">没有搜索到您想要的商品</div>
    </div>
  </div>
</template>

<script>
import SearchBar from '@/components/SearchBar'
import MainGoodsCard from '@/components/GoodsCard/MainGoodsCard'
import searchApi from '@/services/search'

export default {
  components: {
    SearchBar,
    MainGoodsCard
  },
  data () {
    return {
      goodsList: []
    }
  },
  methods: {
    onSearch (val) {
      this.keyword = val
      this.goodsList = []
      searchApi.searchGoods(this.keyword).then(res => {
        this.goodsList = res
      })
    }
  },
  onLoad (option) {
    if (option.keyword) {
      this.keyword = option.keyword
      this.onSearch(this.keyword)
    }
  },
  onPullDownRefresh () {
    this.onSearch(this.keyword)
    mpvue.stopPullDownRefresh()
  }
}
</script>

<style scoped>
.container-contain {
  padding: 0.3rem 0.3rem;
}

.none-content-div {
  position: relative;
  z-index: 100;
  width: 100%;
  height: 100%;
  text-align: center;
}
</style>
