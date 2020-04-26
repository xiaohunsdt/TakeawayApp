<template>
  <div class="container">
    <div class="gradientDiv"></div>
    <div class="container-contain">
      <base-panel>
        <div class="order-header" v-if="order!==undefined">
          <div class="order-number">
            <div class="number"># <span style="font-size: 1.8rem">{{order.number}}</span></div>
            <div class="text">
              <img class="text-img" src="/static/images/order/yellow_num.png"/>
              订餐号
            </div>
          </div>
          <div class="estimated-arrival-time">
            <div class="time" style="margin-top: .5rem">
              <div v-if="order.paymentWay==='ALI_PAY'">
                ¥ <span style="font-size: 2rem">{{order.realPrice * 6 / 1000}}</span>
              </div>
              <div v-if="order.paymentWay==='TRANSFER'">
                ₩ <span style="font-size: 2rem">{{order.realPrice}}</span>
              </div>
            </div>
          </div>
        </div>
      </base-panel>
      <order-card :order="order" :show-operation="false" v-if="order!==undefined"/>

      <base-panel>
        <button open-type="contact" style="margin-top: .5rem" type="primary">联系客服</button>
      </base-panel>

      <base-panel v-if="order!== undefined && order.paymentWay==='ALI_PAY'">
        <div class="pay-way-title">
          支付宝
        </div>
        <div class="pay-way-detail">
          第一步: 进入支付宝首页,并点击更多
          <img alt="" mode="aspectFit" src="/static/images/payment/alipay/1.jpg">
          第二步: 点击红包,再点击普通红包
          <img alt="" mode="aspectFit" src="/static/images/payment/alipay/2.png">
          <img alt="" mode="aspectFit" src="/static/images/payment/alipay/3.png">
          第三步: 输入金额和数量(数量为1)
          <img alt="" mode="aspectFit" src="/static/images/payment/alipay/4.png">
          第四步: 复制口令,返回微信,联系客服!将口令和订餐号告诉客服
          <img alt="" mode="aspectFit" src="/static/images/payment/alipay/5.png">
        </div>
      </base-panel>

      <base-panel v-if="order!== undefined && order.paymentWay==='TRANSFER'">
        <div class="pay-way-title">
          通帐转账
        </div>
        <div class="pay-way-detail">
          账号: 645-910961-66007
          <br/>
          银行: 하나은행
          <br/>
          户名: 왕잔용
          <br/>
          <br/>
          转账成功后,请联系客服!将转账截图和订餐号告诉客服
        </div>
      </base-panel>
    </div>
  </div>
</template>

<script>
  import BasePanel from '@/components/BasePanel'
  import OrderCard from '../order/components/OrderCard'
  import orderService from '@/services/order'

  export default {
    props: {
      orderId: {
        type: Object,
        required: true
      }
    },
    components: {
      BasePanel,
      OrderCard
    },
    data () {
      return {
        order: undefined
      }
    },
    onLoad (options) {
      if (options.orderId) {
        this.orderId = options.orderId
        this.init()
      }
    },
    onShow () {
      this.init()
    },
    onPullDownRefresh () {
      this.init()
    },
    methods: {
      init () {
        orderService.selectOrderById(this.orderId).then(res => {
          this.order = res
          if (this.order.payState === 'PAID') {
            // mpvue.reLaunch({
            //   url: `/pages/order/detail/main?orderId=${this.order.id}`
            // })
            mpvue.reLaunch({
              url: '/pages/order/main?state=WAIT_EAT'
            })
          }
        })
      }
    }
  }
</script>

<style scoped>
  .container-contain {
    padding: 0.3rem 0.1rem;
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

  .pay-way-title {
    font-size: .4rem;
    font-weight: bolder;
  }
</style>
