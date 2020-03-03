<template>
  <div class="container">
    <div class="gradientDiv"></div>
    <div class="container-contain">
      <div id="banner">
        <search-bar background="#FFD200"></search-bar>
        <div id="scrollImg">
          <swiper
            autoplay="true"
            circular="true"
            easing-function="easeInOutCubic"
            indicator-dots="true"
            style="height: 10rem">
            <swiper-item>
              <img alt="" class="itemImg" mode="widthFix" src="/static/images/banner/banner1.jpg">
            </swiper-item>
            <swiper-item>
              <img alt="" class="itemImg" mode="widthFix" src="/static/images/banner/banner2.jpg">
            </swiper-item>
          </swiper>
        </div>
      </div>
<!--      <div id="newUserCoupon">-->
<!--        <img mode="widthFix"-->
<!--             src="/static/images/newcoupon.png"-->
<!--             style="width: 100%"/>-->
<!--      </div>-->
      <food-panel :foodList="newGoodsList" title="新品"/>
      <food-panel :foodList="hotGoodsList" title="热门"/>
    </div>
  </div>
</template>

<script>
  import indexService from '@/services/index'
  import SearchBar from '@/components/SearchBar'
  import FoodPanel from './components/FoodPanel'

  export default {
    components: {
      SearchBar,
      FoodPanel
    },
    data () {
      return {
        newGoodsList: [],
        hotGoodsList: []
      }
    },
    methods: {
      init () {
        indexService.getNewGoodsList().then(res => {
          this.newGoodsList = res
        })

        indexService.getHotGoodsList().then(res => {
          this.hotGoodsList = res
        })
      }
    },
    onLoad () {
      this.init()
    },
    onPullDownRefresh () {
      this.init()
      mpvue.stopPullDownRefresh()
    },
    // 原生的分享功能
    onShareAppMessage: function () {
      return {
        title: '川湘苑',
        desc: '川湘苑品牌中餐厅',
        path: '/pages/index/index'
      }
    }
  }
</script>

<style scoped>
  .itemImg {
    width: 100%;
  }

  #newUserCoupon {
    padding: 0.20rem;
    background-color: white;
  }
</style>
