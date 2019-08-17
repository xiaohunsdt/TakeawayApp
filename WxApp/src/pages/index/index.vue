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
          <view slot="action" @tap="onSearch" style="font-weight: 700">搜索</view>
        </van-search>
      </div>
      <div id="scrollImg">
        <swiper
          indicator-dots="true"
          autoplay="true"
          easing-function="easeInOutCubic"
          circular="true">
          <swiper-item>
            <img class="itemImg" mode="widthFix" src="/static/images/banner/banner1.jpg" alt="">
          </swiper-item>
          <swiper-item>
            <img class="itemImg" mode="widthFix" src="/static/images/banner/banner2.jpg" alt="">
          </swiper-item>
        </swiper>
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

<style>
  .van-search__action--hover {
    background-color: transparent !important;
  }
</style>

<style scoped>
  .container {
  }

  .gradientDiv {
    position: absolute;
    /*background: linear-gradient(#FFD200, white, white);*/
    background: #FFD200;
    width: 100%;
    height: 3rem;
    z-index: -1;
  }

  .itemImg {
    width: 100%;
  }
</style>
