<template>
  <div class="container">
    <div class="gradientDiv"></div>
    <div id="banner">
      <div id="search">
        <van-search
          placeholder="请输入搜索关键词"
          use-action-slot
          shape="round"
          input-align="center"
          background="transparent"
          @change="searchChanged">
          <view slot="action" @tap="onSearch">搜索</view>
        </van-search>
      </div>
      <div id="scrollImg">

      </div>
    </div>
  </div>
</template>

<script>
  // Use Vuex
  // import store from '@/store/store'

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
    },
    created () {
      let app = getApp()
      console.log(app)
    }
  }
</script>

<style scoped>
  .container {}

  .gradientDiv {
    position: absolute;
    background: linear-gradient(#FFD200, white, white);
    width: 100%;
    height: 9rem;
    z-index: -1;
  }

  #search {
    background: transparent;
  }
</style>
