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
              id: 1,
              name: '鸭血粉丝汤',
              desc: '好吃的鸭血粉丝汤好吃的鸭血粉丝',
              monthSale: 1000,
              rate: 5,
              thumb: '/static/images/food/food.jpg'
            }, {
              id: 2,
              name: '鸭血粉丝汤2',
              desc: '好吃的鸭血粉丝汤好吃的鸭血粉丝',
              monthSale: 10,
              rate: 5,
              thumb: '/static/images/food/food.jpg'
            }, {
              id: 3,
              name: '鸭血粉丝汤3',
              desc: '好吃的鸭血粉丝汤好吃的鸭血粉丝',
              monthSale: 10,
              rate: 5,
              thumb: '/static/images/food/food.jpg'
            }
          ],
          hot: [
            {
              id: 4,
              name: '鸭血粉丝汤',
              desc: '好吃的鸭血粉丝汤好吃的鸭血粉丝',
              monthSale: 10,
              rate: 5,
              thumb: '/static/images/food/food.jpg'
            }, {
              id: 5,
              name: '鸭血粉丝汤2',
              desc: '好吃的鸭血粉丝汤好吃的鸭血粉丝',
              monthSale: 10,
              rate: 5,
              thumb: '/static/images/food/food.jpg'
            }, {
              id: 6,
              name: '鸭血粉丝汤3',
              desc: '好吃的鸭血粉丝汤好吃的鸭血粉丝',
              monthSale: 10,
              rate: 5,
              thumb: '/static/images/food/food.jpg'
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

<style scoped>
  .itemImg {
    width: 100%;
  }

  #newUserCoupon {
    padding: 0.20rem;
    background-color: white;
  }
</style>
