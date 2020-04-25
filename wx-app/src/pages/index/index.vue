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
              <img class="itemImg" mode="widthFix" src="/static/images/banner/appointment.jpg">
            </swiper-item>
            <swiper-item>
              <img class="itemImg" mode="widthFix" src="/static/images/banner/yiqing.png">
            </swiper-item>
<!--            <swiper-item>-->
<!--              <img @click="gotoActivity" class="itemImg" mode="widthFix" src="/static/images/banner/yonsei.jpg">-->
<!--            </swiper-item>-->
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
  import { mapMutations } from 'vuex'

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
      ...mapMutations('from', [
        'SET_FROM'
      ]),
      init () {
        indexService.getNewGoodsList().then(res => {
          this.newGoodsList = res
        })

        indexService.getHotGoodsList().then(res => {
          this.hotGoodsList = res
        })
      },
      gotoActivity () {
        mpvue.navigateTo({
          url: '/pages/sub-packages/activity/index/main'
        })
      }
    },
    onLoad (option) {
      console.log(option)
      if (option.from) {
        this.SET_FROM(option.from)
        console.log(option.from)
      }
      this.init()
    },
    onPullDownRefresh () {
      this.init()
      mpvue.stopPullDownRefresh()
    },
    onShareAppMessage: function () {
      return {
        title: '川香苑',
        desc: '川香苑品牌中餐厅',
        path: '/pages/index/main'
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
