<template>
  <div id="search">
    <van-search
      placeholder="请输入搜索关键词"
      use-action-slot
      shape="round"
      input-align="center"
      background="transparent"
      @change="searchChanged">
      <view slot="action" @tap="onSearch" style="font-weight: 700">搜索</view>
    </van-search>
  </div>
</template>

<script>
export default {
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
      console.log(this.searchValue)
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

</style>
