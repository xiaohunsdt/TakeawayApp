<template>
  <div class="container">
    <div class="gradientDiv"></div>
    <div class="container-contain">
      <div id="header">
        <base-panel>
          <van-notice-bar
            left-icon="volume-o"
            text="家的味道,优惠的价格! 留学的期间, 有我有你! 让我们共同成长!"
          />
          <div id="activityInfo">
            <van-tag plain type="success">免费配送</van-tag>
            <van-tag plain type="success">满1w5送月饼</van-tag>
            <van-tag plain type="success">满2w送饮料</van-tag>
          </div>
        </base-panel>
      </div>
      <div id="order-content">
        <van-tabs
          :active="tagActive"
          @change="onChange"
          animated
          border
          custom-class="foodTab"
          nav-class="navClass"
          sticky
          swipeable>
          <van-tab title="素菜小炒">
            内容 1
          </van-tab>
          <van-tab title="荤菜小炒">
            <div class="foodContent">
              <FoodCard
                :food="food"
                :key="food.id"
                v-for="food in foodList"/>
            </div>
          </van-tab>
          <van-tab title="炖菜类">内容 3</van-tab>
          <van-tab title="主食">内容 4</van-tab>
          <van-tab title="饮料">内容 5</van-tab>
        </van-tabs>
      </div>
    </div>
  </div>
</template>

<script>
  import BasePanel from '@/components/BasePanel'
  import FoodCard from '@/components/FoodCard'

  export default {
    components: {
      BasePanel,
      FoodCard
    },
    data () {
      return {
        tagActive: 1,
        foodList: [
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
        ]
      }
    },
    methods: {
      onChange (event) {
        wx.showToast({
          title: `切换到标签 ${event.mp.detail.index + 1}`,
          icon: 'none'
        })
      }
    }
  }
</script>

<style>
  .navClass .van-tabs__line {
    background-color: #FFD200;
  }
</style>

<style scoped>
  #activityInfo {
    padding: 0 0.2rem;
  }

  #activityInfo > van-tag {
    margin-right: 0.1rem;
  }

  #order-content {
    margin-top: 0.2rem;
    background-color: white;
  }

  .foodContent {
    background-color: white;
    padding: 0.2rem;
  }
</style>
