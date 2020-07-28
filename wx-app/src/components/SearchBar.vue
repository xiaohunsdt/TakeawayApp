<template>
  <div id="search">
    <van-search
      placeholder="请输入搜索关键词"
      use-action-slot
      shape="round"
      input-align="center"
      :background="background"
      @change="searchChanged">
      <view slot="action" @tap="onSearch" style="font-weight: 700">搜索</view>
    </van-search>
  </div>
</template>

<script>
  export default {
    props: {
      background: {
        type: String,
        default: 'transparent'
      }
    },
    data () {
      return {
        searchValue: ''
      }
    },
    methods: {
      searchChanged (event) {
        this.searchValue = event.mp.detail
      },
      onSearch () {
        this.$emit('search', this.searchValue)
      }
    }
  }
</script>

<style>
  .van-search__action--hover {
    background-color: transparent !important;
  }
</style>

<style scoped>
  #search{
    position: relative;
    z-index: 100;
  }
</style>
