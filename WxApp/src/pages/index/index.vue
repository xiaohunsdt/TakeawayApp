<template>
  <div class="container">
    <div class="gradientDiv"></div>
    <div id="banner">
      <search-bar></search-bar>
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
    <div></div>
  </div>
</template>

<script>
  // Use Vuex
  // import store from '@/store/store'
  import SearchBar from '@/components/SearchBar'

  export default {
    components: {
      SearchBar
    },
    data () {
      return {}
    },
    methods: {},
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
