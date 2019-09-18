<template>
  <div class="container">
    <div class="gradientDiv"></div>
    <div class="container-contain">
      <div id="header">
        <base-panel>
          <van-cell is-link title="啊萨达萨达萨达萨达萨达撒旦撒大苏打撒旦啊实打实的撒大苏打">
            <view slot="icon" style="margin-right: 0.2rem">
              <van-icon color="#FFD200" name="location" size="1.2rem"/>
            </view>
          </van-cell>
          <van-cell is-link title="01056511996">
            <view slot="icon" style="margin-right: 0.2rem">
              <van-icon color="#FFD200" name="phone" size="1.2rem"/>
            </view>
          </van-cell>
        </base-panel>
      </div>
      <div id="buy-content">
        <base-panel>
          <order-item
            :itemDetail="item"
            :key="item.name"
            v-for="item in orderItems"/>
          <div id="order-amount">
            共<span style="color: #FFD200">{{ orderItems.length }}</span>个商品,
            小计 <span style="color: #FFD200">₩ {{ allAmount }}</span>
          </div>
        </base-panel>
        <base-panel>
          优惠卷
        </base-panel>
        <base-panel>
          备注
        </base-panel>
        <!--        <base-panel>-->
        <!--          配送服务-->
        <!--        </base-panel>-->
      </div>
      <div id="footer">
        <van-submit-bar
          :loading="submitLoading"
          :price="3050"
          :tip="true"
          @submit="onSubmitOrder"
          button-class="submitBtn"
          button-text="立刻支付"
          custom-class="orderSubmitBar"
          price-class="orderPrice">
          <div id="orderBarLeftContent">
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
  import OrderItem from '@/components/OrderItem'

  export default {
    components: {
      BasePanel,
      OrderItem
    },
    computed: {
      allAmount () {
        return this.orderItems
          .map(item => item.count * item.price)
          .reduce((x, y) => x + y)
      }
    },
    data () {
      return {
        orderItems: [
          {
            orderId: null,
            goodsId: 1,
            name: '鸭血粉丝汤1',
            thumb: '/static/images/food/food.jpg',
            count: 2,
            price: 2000
          },
          {
            orderId: null,
            goodsId: 1,
            name: '鸭血粉丝汤2',
            thumb: '/static/images/food/food.jpg',
            count: 1,
            price: 2000
          }
        ]
      }
    },
    methods: {}
  }
</script>

<style scoped>
  #order-amount {
    font-weight: bolder;
    padding-top: .2rem;
    font-size: .25rem;
    text-align: right;
  }
</style>
