<template>
  <div class="container">
    <div class="gradientDiv"></div>
    <div class="container-contain">
      <div id="header">
        <base-panel>
          <div v-if="address">
            <van-cell
              :title="address.address + ' ' + address.detail"
              is-link
              url="/pages/my/address/main">
              <view slot="icon" style="margin-right: 0.2rem">
                <van-icon color="#FFD200" name="location" size="1.2rem"/>
              </view>
            </van-cell>
            <van-cell
              :title="address.phone"
              is-link
              url="/pages/my/address/main">
              <view slot="icon" style="margin-right: 0.2rem">
                <van-icon color="#FFD200" name="phone" size="1.2rem"/>
              </view>
            </van-cell>
          </div>
          <div v-else>
            <van-cell is-link title="选择地址" url="/pages/my/address/main"/>
          </div>
        </base-panel>
      </div>
      <div :class="{'show-order-tip':showOrderTip}" id="buy-content">
        <base-panel>
          <order-item
            :itemDetail="item"
            :key="item.goodsName"
            v-for="item in orderItems"/>
          <div id="order-amount">
            共<span style="color: #FFD200">{{ cartCount }}</span>个商品,
            小计 <span style="color: #FFD200">₩ {{ cartAllPrice }}</span>
          </div>
        </base-panel>
        <base-panel>
          <div>支付方式</div>
          <div class="pay-way-btns">
            <van-button
              :color="payWay==='WEIXIN_PAY'?'#FFD200':null"
              @click="payWayChange('WEIXIN_PAY')"
              custom-class="pay-way-btn"
              icon="wechat"
              round
              size="small"
              type="primary">微信支付
            </van-button>
            <van-button
              custom-class="pay-way-btn"
              disabled
              icon="balance-list"
              round
              size="small"
              type="primary">余额支付
            </van-button>
            <van-button
              :color="payWay==='ALI_PAY'?'#FFD200':null"
              @click="payWayChange('ALI_PAY')"
              custom-class="pay-way-btn"
              icon="alipay"
              round
              size="small"
              type="primary">支付宝
            </van-button>
            <van-button
              :color="payWay==='TRANSFER'?'#FFD200':null"
              @click="payWayChange('TRANSFER')"
              custom-class="pay-way-btn"
              icon="bill"
              round
              size="small"
              type="primary">通帐转帐
            </van-button>
            <van-button
              :color="payWay==='CREDIT_CARD'?'#FFD200':null"
              @click="payWayChange('CREDIT_CARD')"
              custom-class="pay-way-btn"
              icon="card"
              round
              size="small"
              type="primary">刷卡支付
            </van-button>
            <van-button
              :color="payWay==='CASH'?'#FFD200':null"
              @click="payWayChange('CASH')"
              custom-class="pay-way-btn"
              icon="bill"
              round
              size="small"
              type="primary">现金支付
            </van-button>
          </div>
        </base-panel>
        <base-panel>
          <div @click="setCoupon">
            <div>优惠卷</div>
          </div>
        </base-panel>
        <base-panel @panel-click="setPs">
          <div @click="setPs">
            <div>备注</div>
            <div v-if="psData!==''">
              <van-cell :title="psData"/>
            </div>
          </div>
        </base-panel>
      </div>
      <div id="footer">
        <van-submit-bar
          :disabled="disableService"
          :loading="submitLoading"
          :decimal-length="0"
          :price="cartAllPrice * 100"
          :tip="true"
          @submit="onSubmitOrder"
          button-class="submitBtn"
          button-text="立刻支付"
          currency="₩"
          custom-class="orderSubmitBar"
          price-class="orderPrice">
          <div id="order-bar-left-content">
            <img alt="" src="/static/images/order/cart.png">
            <div style="display: inline-block;font-weight: bolder; font-size:1.4rem;margin-left: 0.4rem;">
              {{ cartCount }}
            </div>
          </div>
          <view slot="tip" v-if="disableService || showOrderTip">{{tipNotice}}</view>
        </van-submit-bar>
      </div>
    </div>
  </div>
</template>

<script>
  import { mapMutations } from 'vuex'
  import BasePanel from '@/components/BasePanel'
  import OrderItem from '@/components/OrderItem'
  import indexService from '@/services/index'
  import orderService from '@/services/order'
  import payService from '@/services/pay'
  import addressService from '@/services/address'

  export default {
    components: {
      BasePanel,
      OrderItem
    },
    watch: {
      address (newVal) {
        // 检查当前的价格和位置是否可以下单
        this.disableService = false
        this.checkExpressState(newVal.id, this.cartAllPrice)
      }
    },
    computed: {
      orderItems () {
        const cartGoodsList = this.$store.getters.cartGoodsList
        let orderItemList = []
        cartGoodsList.forEach(item => {
          let orderItem = {}
          orderItem.goodsId = item.goodsId
          orderItem.goodsName = item.goods.name
          orderItem.goodsThumb = item.goods.thumb
          orderItem.goodsPrice = item.goods.price
          orderItem.goodsCount = item.count
          orderItemList.push(orderItem)
        })

        return orderItemList
      },
      cartCount () {
        return this.$store.getters.cartAllCount
      },
      cartAllPrice () {
        return this.$store.getters.cartAllPrice
      },
      address () {
        return this.$store.getters.currentAddress
      }
    },
    onLoad () {
      this.init()
      if (!this.address) {
        addressService.getDefaultAddress().then(res => {
          if (res.address) {
            this.SET_ADDRESS(res)
          }
        })
      }
    },
    onShow () {
      if (this.orderId !== '') {
        orderService.selectOrderById(this.orderId).then(res => {
          this.order = res
          if (this.order.payState === 'PAID') {
            // mpvue.redirectTo({
            //   url: '/pages/order/main?state=waitEat'
            // })
            mpvue.reLaunch({
              url: `/pages/order/detail/main?orderId=${this.order.id}`
            })
          } else {
            mpvue.reLaunch({
              url: '/pages/order/main?state=waitPay'
            })
          }
        })
      }
    },
    data () {
      return {
        submitLoading: false,
        showOrderTip: false,
        disableService: false,
        tipNotice: '当前下单高峰期, 您可能需要等待较长时间才能就餐!',
        orderId: '',
        order: {},
        payWay: 'WEIXIN_PAY',
        coupon: null,
        psData: ''
      }
    },
    methods: {
      ...mapMutations('cart', [
        'CLEAR_CART'
      ]),
      ...mapMutations('address', [
        'SET_ADDRESS'
      ]),
      init () {
        this.submitLoading = false
        this.showOrderTip = false
        this.disableService = false
        this.orderId = ''
        this.order = {}
        this.payWay = 'WEIXIN_PAY'
        this.coupon = null
        this.psData = ''
      },
      setCoupon () {
        const $this = this
        mpvue.navigateTo({
          url: '/pages/coupon/main',
          events: {
            setCoupon (data) {
              $this.coupon = data.coupon
              console.log($this.coupon)
            }
          }
        })
        // mpvue.showToast({
        //   title: '暂时还没有开放1',
        //   icon: 'none'
        // })
      },
      setPs () {
        const $this = this
        mpvue.navigateTo({
          url: '/pages/buy/remark/main',
          events: {
            setPsContent (data) {
              $this.psData = data.ps
            }
          }
        })
      },
      payWayChange (payWay) {
        this.payWay = payWay
      },
      onSubmitOrder () {
        this.submitLoading = true
        orderService.createOrder(this.orderItems, this.address, this.payWay, this.coupon, this.psData)
          .then(res => {
            this.submitLoading = false
            this.CLEAR_CART()
            this.orderId = res.message
            payService.payOrder(this.orderId, this.payWay)
          })
          .catch(res => {
            this.submitLoading = false
          })
      },
      checkExpressState (addressId, allPrice) {
        indexService.getExpressServiceState(addressId, allPrice)
          .then(res => {
            if (res.state !== 0) {
              this.disableService = true
              this.tipNotice = res.message
            }
          })
      }
    }
  }
</script>

<style>
  .pay-way-btns {
    margin-top: .1rem;
    text-align: center;
  }

  .pay-way-btns .pay-way-btn {
    display: inline-block;
    margin: .08rem;
    width: 45%;
  }
</style>

<style scoped>
  #buy-content {
    padding-bottom: 1.6rem;
  }

  #buy-content.show-order-tip {
    padding-bottom: 2.5rem;
  }

  #order-amount {
    font-weight: bolder;
    padding-top: .2rem;
    font-size: .25rem;
    text-align: right;
  }

  #order-bar-left-content {
    padding-left: 0.2rem;
    padding-top: 0.2rem;
    display: flex;
  }

  #order-bar-left-content img {
    width: 0.6rem;
    height: 0.5rem;
  }
</style>
