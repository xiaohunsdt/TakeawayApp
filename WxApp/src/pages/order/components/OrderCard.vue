<template>
  <base-panel>
    <div @click.stop="openOrderDetail">
      <div class="order-header">
        <div class="header-left">{{order.createDate}}</div>
        <div class="header-right">待支付</div>
      </div>
      <div class="order-items">
        <order-item
          :itemDetail="itemDetial"
          :key="itemDetial.name"
          v-for="itemDetial in order.orderItems"/>
      </div>
      <div class="order-amount">
        共<span style="color: #FFD200">{{ order.orderItems.length }}</span>个商品,
        小计 <span style="color: #FFD200">₩ {{ order.orderAmount }}</span>
      </div>
    </div>

    <div class="action-btns">
      <van-button @click.stop="payNow" color="#FFD200" custom-class="action-btn" round size="small">立刻付款</van-button>
      <van-button @click.stop="confirmGetFood" color="#FFD200" custom-class="action-btn" round size="small">确认取餐</van-button>
      <van-button @click.stop="shareOrder" color="#FFD200" custom-class="action-btn" round size="small">分享领红包</van-button>
      <van-button @click.stop="comment" color="#FFD200" custom-class="action-btn" round size="small">评价</van-button>
    </div>
  </base-panel>
</template>

<script>
  import BasePanel from '@/components/BasePanel'
  import OrderItem from '@/components/OrderItem'

  export default {
    name: 'OrderCard',
    props: {
      order: {
        type: Object,
        required: true
      }
    },
    components: {
      BasePanel,
      OrderItem
    },
    methods: {
      openOrderDetail (event) {
        console.log(event)
      },
      payNow (event) {
        console.log(event)
      },
      confirmGetFood (event) {
        console.log(event)
      },
      shareOrder (event) {
        console.log(event)
      },
      comment (event) {
        console.log(event)
      }
    }
  }
</script>
<style>
  .base-panel {
    margin-bottom: 0.2rem;
  }

  .action-btn {
    color: black !important;
    margin-left: .1rem;
  }
</style>
<style scoped>
  .order-amount {
    font-weight: bolder;
    padding-top: .2rem;
    font-size: .25rem;
    text-align: right;
  }

  .action-btns {
    margin-top: .2rem;
    text-align: right;
  }

  .order-header {
    display: flex;
    justify-content: space-between;
  }

  .order-header .header-left {
    padding: .2rem;
  }

  .order-header .header-right {
    color: gray;
    font-size: .25rem;
  }
</style>
