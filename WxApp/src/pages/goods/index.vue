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
            <van-tag type="success">免费配送</van-tag>
            <van-tag type="success">满1w5送月饼</van-tag>
            <van-tag type="success">满2w送饮料</van-tag>
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
              <goods-card
                :food="food"
                :key="food.goodsId"
                v-for="food in foodList"/>
            </div>
          </van-tab>
          <van-tab title="炖菜类">内容 3</van-tab>
          <van-tab title="主食">内容 4</van-tab>
          <van-tab title="饮料">内容 5</van-tab>
        </van-tabs>
      </div>
      <div id="footer">
        <van-submit-bar
          :loading="submitLoading"
          :price="3050"
          :tip="true"
          @submit="onSubmitOrder"
          button-class="submitBtn"
          button-text="提交订单"
          custom-class="orderSubmitBar"
          price-class="orderPrice">
          <div id="orderBarLeftContent">
            <img alt="" src="/static/images/order/cart.png">
            <div style="display: inline-block;font-weight: bolder; font-size:1.4rem;margin-left: 0.4rem;">
              {{ orderCount }}
            </div>
          </div>
          <!--          <view slot="tip">当前下单高峰期, 您可能需要等待较长时间才能就餐!</view>-->
        </van-submit-bar>
      </div>
    </div>
  </div>
</template>

<script>
    import BasePanel from '@/components/BasePanel'
    import GoodsCard from '@/components/GoodsCard'

    export default {
        components: {
            BasePanel,
            GoodsCard
        },
    data () {
      return {
        tagActive: 1,
        submitLoading: false,
        foodList: [
          {
            goodsId: 1,
            name: '鸭血粉丝汤',
            desc: '好吃的鸭血粉丝汤好吃的鸭血粉丝',
            monthSale: 1000,
            rate: 5,
            thumb: '/static/images/food/food.jpg'
          }, {
            goodsId: 2,
            name: '鸭血粉丝汤2',
            desc: '好吃的鸭血粉丝汤好吃的鸭血粉丝',
            monthSale: 10,
            rate: 5,
            thumb: '/static/images/food/food.jpg'
          }, {
            goodsId: 3,
            name: '鸭血粉丝汤3',
            desc: '好吃的鸭血粉丝汤好吃的鸭血粉丝',
            monthSale: 10,
            rate: 5,
            thumb: '/static/images/food/food.jpg'
          }
        ],
        orderCount: 3
      }
    },
    methods: {
      onChange (event) {
        wx.showToast({
          title: `切换到标签 ${event.mp.detail.index + 1}`,
          icon: 'none'
        })
      },
      onSubmitOrder () {
        wx.showToast({
          title: '提交订单',
          icon: 'none'
        })
      }
    }
  }
</script>

<style>
  .van-notice-bar {
    height: auto !important;
  }

  .navClass .van-tabs__line {
    background-color: #FFD200;
  }

  #activityInfo .van-tag {
    display: unset !important;
  }

  .orderSubmitBar {
    box-shadow: 0 5px 25px 0 rgba(0, 0, 0, .13);
  }

  .submitBtn {
    font-weight: bolder !important;
    background-color: #FFD200 !important;
    border-color: #FFD200 !important;
  }

  .orderPrice {
    font-weight: bolder;
  }
</style>

<style scoped>
  #activityInfo {
    padding: 0 0.2rem;
    margin-top: 0.2rem;
  }

  #activityInfo > van-tag {
    margin-right: 0.1rem;
  }

  #order-content {
    min-height: 5rem;
    margin-top: 0.2rem;
    background-color: white;
  }

  .foodContent {
    background-color: white;
    padding: 0.2rem;
  }

  #orderBarLeftContent {
    padding-left: 0.2rem;
    padding-top: 0.2rem;
    display: flex;
  }

  #orderBarLeftContent img {
    width: 0.6rem;
    height: 0.5rem;
  }
</style>
