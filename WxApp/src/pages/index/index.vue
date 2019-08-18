<template>
  <div class="container">
    <div class="gradientDiv"></div>
    <div id="banner">
      <search-bar background="#FFD200"></search-bar>
      <div id="scrollImg">
        <swiper
          indicator-dots="true"
          autoplay="true"
          easing-function="easeInOutCubic"
          circular="true"
          style="height: 300rpx">
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
    <food-panel title="新品" :foodList="foodPanel.new"/>
    <food-panel title="热门" :foodList="foodPanel.hot"/>
  </div>
</template>

<script>
  // Use Vuex
  // import store from '@/store/store'
  import SearchBar from '@/components/SearchBar'
  import FoodPanel from './components/FoodPanel'

  export default {
    components: {
      SearchBar,
      FoodPanel
    },
    data () {
      return {
        foodPanel: {
          new: [
            {
              title: '鸭血粉丝汤',
              monthSale: 10,
              rate: 5,
              image: '/static/images/food/food.jpg'
            }, {
              title: '鸭血粉丝汤2',
              monthSale: 10,
              rate: 5,
              image: '/static/images/food/food.jpg'
            }, {
              title: '鸭血粉丝汤3',
              monthSale: 10,
              rate: 5,
              image: '/static/images/food/food.jpg'
            }
          ],
          hot: [
            {
              title: '鸭血粉丝汤',
              monthSale: 10,
              rate: 5,
              image: '/static/images/food/food.jpg'
            }, {
              title: '鸭血粉丝汤2',
              monthSale: 10,
              rate: 5,
              image: '/static/images/food/food.jpg'
            }, {
              title: '鸭血粉丝汤3',
              monthSale: 10,
              rate: 5,
              image: '/static/images/food/food.jpg'
            }
          ]
        }
      }
    },
    methods: {},
    created () {
      let app = getApp()
      console.log(app)
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

<style>
  page {
    height: 100%;
  }
</style>

<style scoped>
  .container {
    background-color: #F0F0F0;
    min-height: 100%;
    width: 100%;
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

  #newUserCoupon {
    padding: 0.20rem;
    background-color: white;
  }
</style>
