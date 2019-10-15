<template>
  <div class="container">
    <div class="gradientDiv"></div>
    <div class="container-contain">
      <div id="banner">
        <search-bar background="#FFD200"></search-bar>
        <div id="scrollImg">
          <swiper
            indicator-dots="true"
            autoplay="true"
            easing-function="easeInOutCubic"
            circular="true"
            style="height: 10rem">
            <swiper-item>
              <img class="itemImg" mode="widthFix" src="/static/images/banner/banner1.jpg" alt="">
            </swiper-item>
            <swiper-item>
              <img class="itemImg" mode="widthFix" src="/static/images/banner/banner2.jpg" alt="">
            </swiper-item>
          </swiper>
        </div>
      </div>
      <div id="newUserCoupon">
        <img src="/static/images/newcoupon.png"
             mode="widthFix"
             style="width: 100%"
             alt=""/>
      </div>
      <food-panel title="新品" :foodList="newGoodsList"/>
      <food-panel title="热门" :foodList="hotGoodshot"/>
    </div>
  </div>
</template>

<script>
    // Use Vuex
    // import store from '@/store/store'
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
                hotGoodshot: []
            }
        },
        methods: {},
        created () {
            // let app = getApp()
            // console.log(app)
            indexService.getNewGoodsList().then(res => {
                this.newGoodsList = res
            })

            indexService.getHotGoodsList().then(res => {
                this.hotGoodshot = res
            })
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
