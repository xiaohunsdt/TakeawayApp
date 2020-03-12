<template>
  <div class="container">
    <div class="gradientDiv"></div>
    <div class="container-contain">
      <base-panel>
        <div class="order-header">
          <div class="order-number">
            <div class="number"># <span style="font-size: 2rem">{{order.number}}</span></div>
            <div class="text">
              <img class="text-img" src="/static/images/order/yellow_num.png"/>
              订餐号
            </div>
          </div>
          <div class="estimated-arrival-time"
               v-if="order.payState !== 'UN_PAY' && (order.orderState === 'PRODUCING' || order.orderState === 'DELIVERING')">
            <div class="time">
              {{deliveryArriveTime.date}}
              <span style="font-size: 2rem">{{deliveryArriveTime.time}}</span>
            </div>
            <div class="text">
              <img class="text-img" src="/static/images/order/yellow_time.png"/>
              预计到达
            </div>
          </div>
          <div class="estimated-arrival-time"
               v-if="order.payState === 'UN_PAY' || order.orderState === 'WAITING_RECEIVE'">
            <div class="time" style="margin-top: .5rem">
              <span style="font-size: 2rem">未知</span>
            </div>
          </div>
          <div class="estimated-arrival-time" v-if="order.orderState === 'FINISHED'">
            <div class="time" style="margin-top: .5rem">
              <span style="font-size: 2rem">已完成</span>
            </div>
          </div>
          <div class="estimated-arrival-time" v-if="order.orderState === 'REFUND'">
            <div class="time" style="margin-top: .5rem">
              <span style="font-size: 2rem">已退款</span>
            </div>
          </div>
        </div>
      </base-panel>

      <base-panel>
        <div class="order-items">
          <order-item
            :itemDetail="itemDetial"
            :key="itemDetial.goodsName"
            v-for="itemDetial in order.orderItemList"/>
        </div>
        <div class="order-amount">
          <span style="font-size: .7rem;margin-right: .2rem">{{ payStateStr }}</span>
          实付 <span style="color: #FFD200">₩ {{ order.realPrice }}</span>
        </div>

        <div class="action-btns">
          <van-button
            @click.stop="payNow" color="#FFD200" custom-class="action-btn" round
            size="small"
            v-if="order.payState === 'UN_PAY' && order.orderState !== 'EXPIRED'">
            立刻付款
          </van-button>
          <van-button
            @click.stop="confirmGetFood"
            color="#FFD200" custom-class="action-btn" round size="small"
            v-if="order.payState !== 'UN_PAY' && (order.orderState=== 'WAITING_RECEIVE' || order.orderState=== 'PRODUCING' || order.orderState=== 'DELIVERING')">
            确认取餐
          </van-button>
          <van-button
            @click.stop="comment" color="#FFD200" custom-class="action-btn"
            round size="small"
            v-if="order.payState !== 'UN_PAY' && order.orderState=== 'FINISHED' && !order.isCommented">
            评价
          </van-button>
          <van-button
            @click.stop="shareOrder" color="#FFD200" custom-class="action-btn"
            round size="small"
            v-if="order.payState !== 'UN_PAY' && order.orderState!== 'REFUND' && order.orderState!== 'EXPIRED'">
            分享领红包
          </van-button>
          <van-button
            @click.stop="shareOrder" color="#FFD200" custom-class="action-btn"
            round size="small"
            v-if="order.payState === 'UN_PAY' || order.orderState === 'REFUND' || order.orderState === 'EXPIRED'">
            删除
          </van-button>
        </div>
      </base-panel>

      <base-panel>
        <div class="order-row">
          <div class="title">订单号</div>
          <div class="content">{{order.id}}</div>
        </div>
        <div class="order-row">
          <div class="title">订单类型</div>
          <div class="content" v-if="order.appointmentDate===''">一般订单</div>
          <div class="content" v-else>预约订单</div>
        </div>
        <div class="order-row" v-if="order.appointmentDate!==''">
          <div class="title">预约时间</div>
          <div class="content">{{order.appointmentDate}}</div>
        </div>
        <div class="order-row">
          <div class="title">下单时间</div>
          <div class="content">{{order.createDate}}</div>
        </div>
        <div class="order-row">
          <div class="title">支付方式</div>
          <div class="content">{{paymentWayStr}}</div>
        </div>
        <div class="order-row" v-if="couponName">
          <div class="title">优惠卷</div>
          <div class="content">{{couponName}}</div>
        </div>
        <div class="order-row" v-if="order.discount > 0">
          <div class="title">优惠折扣</div>
          <div class="content">{{order.discount}}折</div>
        </div>
        <div class="order-row" v-if="order.discountedPrices > 0">
          <div class="title">优惠金额</div>
          <div class="content">{{order.discountedPrices}}</div>
        </div>
      </base-panel>
    </div>
  </div>
</template>

<script>
  import BasePanel from '@/components/BasePanel'
  import OrderItem from '@/components/OrderItem'
  import orderService from '@/services/order'
  import couponService from '@/services/coupon'
  import indexUtil from '@/utils/index'
  import orderOperation from '../mixins/order-operation'

  export default {
    components: {
      BasePanel,
      OrderItem
    },
    mixins: [orderOperation],
    onLoad (options) {
      this.orderId = options.orderId
    },
    onShow () {
      this.init()
    },
    onPullDownRefresh () {
      this.init()
      mpvue.stopPullDownRefresh()
    },
    computed: {
      payStateStr () {
        return indexUtil.formatPayState(this.order.payState)
      },
      paymentWayStr () {
        return indexUtil.formatPaymentWay(this.order.paymentWay)
      }
    },
    data () {
      return {
        orderId: '',
        order: {},
        couponName: null,
        deliveryArriveTime: {
          date: '',
          time: ''
        }
      }
    },
    methods: {
      init () {
        orderService.selectOrderById(this.orderId).then(res => {
          this.order = res
          if (this.order.discountedPrices) {
            couponService.getCouponLogByOrderId(this.orderId)
              .then(res => {
                this.couponName = res[0].couponName
              })
          }
          if ((this.order.orderState === 'PRODUCING' || this.order.orderState === 'DELIVERING') && this.order.payState !== 'UN_PAY') {
            // 获取预计送达时间
            orderService.getDeliveryArriveTime(this.orderId)
              .then(res => {
                if (res.code) {
                  this.deliveryArriveTime = null
                }
                this.deliveryArriveTime = res
              })
          }
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

  .order-number, .estimated-arrival-time {
    text-align: center;
    padding: .1rem .3rem;
  }

  .order-number .number, .estimated-arrival-time .time {
    font-weight: bolder;
  }

  .order-number .text, .estimated-arrival-time .text {
    font-size: .28rem;
    display: flex;
    justify-content: center;
  }

  .order-number .text .text-img, .estimated-arrival-time .text .text-img {
    margin-right: .1rem;
    margin-top: .05rem;
    width: .3rem;
    height: .3rem;
  }
</style>
