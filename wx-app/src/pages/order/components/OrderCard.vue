<template>
  <base-panel>
    <div @click.stop="openOrderDetail">
      <div class="order-header">
        <div class="header-left">
          <div v-if="order.appointmentDate!==''">
            <van-icon name="clock"  color="#FFD200" size="1.3rem"/>
          </div>
        </div>
        <div class="header-right">
          <div v-if="order.payState === 'PAID' || order.payState === 'PAY_LATER'">
            {{orderStateStr}}
          </div>
          <div style="display: inline" v-if="order.payState === 'UN_PAY' || order.orderState === 'EXPIRED'">
            <span v-if="order.payState === 'UN_PAY' && remainingTime > 0">
              未支付(过期: <van-count-down :time="remainingTime" format="mm:ss" style="display: inline-flex"/>)
            </span>
            <span v-if="order.orderState === 'EXPIRED' || remainingTime <= 0">
              已过期
            </span>
          </div>
        </div>
      </div>
      <div class="order-items">
        <order-item
          :itemDetail="itemDetail"
          :key="itemDetail.goodsName"
          v-for="itemDetail in order.orderItemList"/>
      </div>
      <div class="order-amount">
        <div class="left">
          {{order.createDate}}
        </div>
        <div class="right">
          共<span style="color: #FFD200">{{ order.orderItemList.length }}</span>个商品,
          实付 <span style="color: #FFD200">₩ {{ order.realPrice }}</span>
        </div>
      </div>
    </div>

    <div v-if="showOperation" class="action-btns">
      <van-button
        @click.stop="payNow" color="#FFD200" custom-class="action-btn" round
        size="small"
        v-if="order.payState === 'UN_PAY' && order.orderState !== 'EXPIRED'">
        立刻付款
      </van-button>
<!--      <van-button-->
<!--        @click.stop="confirmGetFood"-->
<!--        color="#FFD200" custom-class="action-btn" round size="small"-->
<!--        v-if="order.payState !== 'UN_PAY' && (order.orderState=== 'WAITING_RECEIVE' || order.orderState=== 'PRODUCING' || order.orderState=== 'DELIVERING')">-->
<!--        确认取餐-->
<!--      </van-button>-->
      <van-button
        @click.stop="comment" color="#FFD200" custom-class="action-btn"
        round size="small"
        v-if="order.payState !== 'UN_PAY' && order.orderState=== 'FINISHED' && !order.isCommented">
        评价
      </van-button>
<!--      <van-button-->
<!--        @click.stop="shareOrder" color="#FFD200" custom-class="action-btn"-->
<!--        round size="small"-->
<!--        v-if="order.payState !== 'UN_PAY' && order.orderState!== 'REFUND' && order.orderState!== 'EXPIRED'">-->
<!--        分享领红包-->
<!--      </van-button>-->
      <van-button
        @click.stop="deleteOrder" color="#FFD200" custom-class="action-btn"
        round size="small"
        v-if="order.payState === 'UN_PAY' || order.orderState === 'REFUND' || order.orderState === 'EXPIRED'">
        删除
      </van-button>
    </div>
  </base-panel>
</template>

<script>
  import BasePanel from '@/components/BasePanel'
  import OrderItem from '@/components/OrderItem'
  import indexUtil from '@/utils/index'
  import orderOperation from '../mixins/order-operation'

  export default {
    name: 'OrderCard',
    props: {
      order: {
        type: Object,
        required: true
      },
      showOperation: {
        type: Boolean,
        required: false,
        default: true
      }
    },
    components: {
      BasePanel,
      OrderItem
    },
    mixins: [orderOperation],
    computed: {
      payStateStr () {
        return indexUtil.formatPayState(this.order.payState)
      },
      orderStateStr () {
        return indexUtil.formatOrderState(this.order.orderState)
      },
      remainingTime () {
        if (this.order.payState === 'UN_PAY') {
          return indexUtil.orderRemainingTime(this.order.createDate)
        } else {
          return 0
        }
      }
    },
    methods: {
      openOrderDetail () {
        mpvue.navigateTo({
          url: `/pages/order/detail/main?orderId=${this.order.id}`
        })
      }
    }
  }
</script>
<style>
  .action-btn {
    color: black !important;
    margin-left: .1rem;
  }
</style>
<style scoped>
  .order-amount {
    display: flex;
    justify-content: space-between;
    padding-top: .2rem;
    font-size: .25rem;
  }

  .order-amount .left {
    margin-left: .2rem;
  }

  .order-amount .right {
    font-weight: bolder;
    text-align: right;
  }

  .action-btns {
    margin-top: .35rem;
    text-align: right;
  }

  .order-header {
    display: flex;
    justify-content: space-between;
  }

  .order-header .header-left {
    font-size: .25rem;
  }

  .order-header .header-right {
    color: gray;
    font-size: .25rem;
  }
</style>
