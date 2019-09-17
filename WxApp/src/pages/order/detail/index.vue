<template>
  <div class="container">
    <div class="gradientDiv"></div>
    <div class="container-contain">
      <base-panel>
        <div class="order-header">
          <div class="order-number">
            <div class="number"># <span style="font-size: 2rem">{{order.orderNumber}}</span></div>
            <div class="text">
              <van-icon name="bell" color="#18c675" size="1rem" style="margin-right: .2rem"/>
              订餐号
            </div>
          </div>
          <div class="estimated-arrival-time">
            <div class="time">
              今天
              <span style="font-size: 2rem">14:40</span>
            </div>
            <div class="text">
              <van-icon name="underway" color="#FFD200" size="1rem" style="margin-right: .2rem"/>
              预计到达
            </div>
          </div>
        </div>
      </base-panel>

      <base-panel>
        <div class="order-items">
          <order-item
            :itemDetail="itemDetial"
            :key="itemDetial.name"
            v-for="itemDetial in order.orderItems"/>
        </div>
        <div class="order-amount">
          <span style="font-size: .7rem;margin-right: .2rem">已支付</span>
          小计 <span style="color: #FFD200">₩ {{ order.orderAmount }}</span>
        </div>

        <div class="action-btns">
          <van-button @click.stop="payNow" color="#FFD200" custom-class="action-btn" round size="small">立刻付款
          </van-button>
          <van-button @click.stop="confirmGetFood" color="#FFD200" custom-class="action-btn" round size="small">确认取餐
          </van-button>
          <van-button @click.stop="shareOrder" color="#FFD200" custom-class="action-btn" round size="small">分享领红包
          </van-button>
          <van-button @click.stop="comment" color="#FFD200" custom-class="action-btn" round size="small">评价</van-button>
        </div>
      </base-panel>

      <base-panel>
        <div class="order-row">
          <div class="title">订单号</div>
          <div class="content">{{order.orderId}}</div>
        </div>
        <div class="order-row">
          <div class="title">下单时间</div>
          <div class="content">{{order.createDate}}</div>
        </div>
        <div class="order-row">
          <div class="title">支付方式</div>
          <div class="content">微信转账</div>
        </div>

      </base-panel>
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
        data () {
            return {
                order: {
                    orderId: '20190915gfhfghfbcvsdf',
                    orderNumber: 2,
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
                    ],
                    orderAmount: 67,
                    orderPayWay: 1,
                    orderState: 1,
                    createDate: '2019-09-15 12:23'
                }
            }
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
  .container-contain {
    padding: 0.3rem 0.1rem;
  }

  .order-amount {
    font-weight: bolder;
    padding-top: .2rem;
    font-size: .25rem;
    text-align: right;
  }

  .action-btns {
    margin-top: .35rem;
    text-align: right;
  }

  .order-row {
    display: flex;
    font-size: 0.25rem;
    margin: .2rem 0rem;
  }

  .order-row .title {
    color: gray;
    width: 1.5rem;
  }

  .order-header {
    display: flex;
    justify-content: space-between;
  }

  .order-number,.estimated-arrival-time {
    text-align: center;
    padding: .1rem .3rem;
  }

  .order-number .number,.estimated-arrival-time .time {
    font-weight: bolder;

  }

  .order-number .text, .estimated-arrival-time .text{
    font-size: .28rem;
    display: flex;
  }
</style>
