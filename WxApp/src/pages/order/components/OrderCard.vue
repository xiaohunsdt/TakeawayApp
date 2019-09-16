<template>
  <base-panel>
    <div class="order-header">
      <div class="order-id">{{order.orderId}}</div>
      <div class="pay-status">待支付</div>
    </div>
    <div class="order-items">
      <order-item
        v-for="itemDetial in order.orderItems"
        :key="itemDetial.name"
        :itemDetail="itemDetial"/>
    </div>
    <div class="order-amount">
      共<span style="color: #FFD200">{{ order.orderItems.length }}</span>个商品,
      小计 <span style="color: #FFD200">₩ {{ order.orderAmount }}</span>
    </div>
    <div class="action-btns">
      <van-button custom-class="action-btn" color="#FFD200" round size="small">立刻付款</van-button>
      <van-button custom-class="action-btn" color="#FFD200" round size="small">确认取餐</van-button>
      <van-button custom-class="action-btn" color="#FFD200" round size="small">分享领红包</van-button>
      <van-button custom-class="action-btn" color="#FFD200" round size="small">评价</van-button>
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

  .order-header{
    display: flex;
    justify-content: space-between;
  }
</style>
