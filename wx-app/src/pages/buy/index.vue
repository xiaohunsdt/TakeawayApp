<template>
  <div class="container">
    <div class="gradientDiv"></div>
    <div class="container-contain">
      <div id="header">
        <base-panel>
          <div>
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
          </div>
          <van-cell
            :title="deliveryType"
            :value="deliveryArriveTime"
            @click="showTimePicker=true">
            <view slot="icon" style="margin-right: 0.2rem">
              <van-icon color="#FFD200" name="clock" size="1.2rem"/>
            </view>
          </van-cell>
        </base-panel>
      </div>
      <van-action-sheet
        :show="showTimePicker"
        :z-index="99999"
        @cancel="showTimePicker =false"
        @click-overlay="showTimePicker =false"
        @close="showTimePicker =false"
        close-on-click-overlay
        overlay
        title="预约时间">
        <view class="time-picker-content">
          <picker-view :value="appointmentIndex" @change="onTimePickerChange" indicator-style="height: 50px"
                       style="width: 100%;height: 100%;">
            <picker-view-column>
              <view style="line-height: 50px" v-for="item in appointmentTimes[0]">{{item}}</view>
            </picker-view-column>
            <picker-view-column>
              <view style="line-height: 50px" v-for="item in appointmentTimes[1]">
                <view v-if="item!=='尽快配送'">{{item}}点</view>
                <view v-else>{{item}}</view>
              </view>
            </picker-view-column>
            <picker-view-column>
              <view style="line-height: 50px" v-for="item in appointmentTimes[2]">{{item}}分</view>
            </picker-view-column>
          </picker-view>
        </view>
      </van-action-sheet>
      <div :class="{'show-order-tip':showOrderTip}" id="buy-content">
        <base-panel>
          <order-item
            :itemDetail="item"
            :key="item.goodsName"
            v-for="item in orderItems"/>
          <div id="order-amount">
            共<span style="color: #FFD200">{{ cartAllCount }}</span>个商品,
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
          <div @click="setCoupon" class="coupon-panel">
            <div>优惠卷</div>
            <div v-if="coupon">{{coupon.couponName}}
              <van-icon @click.stop="deleteCoupon" class="coupon-delete-btn" color="red" name="close"/>
            </div>
          </div>
          <div class="coupon-discounted-prices" v-if="couponDiscountPrice > 0">
            - ₩{{ couponDiscountPrice }}
          </div>
          <div class="coupon-discounted-prices" v-if="couponInfoTip">
            {{ couponInfoTip }}
          </div>
          <div @click="setCoupon" class="coupon-detail-info" v-if="coupon">
            {{ couponInfoDetail }}
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
          :decimal-length="0"
          :disabled="disableService"
          :loading="submitLoading"
          :price="(cartAllPrice - couponDiscountPrice) * 100"
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
              {{ cartAllCount }}
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
  import couponService from '@/services/coupon'

  let times = null

  export default {
    components: {
      BasePanel,
      OrderItem
    },
    watch: {
      address (newVal) {
        // 检查当前的价格和位置是否可以下单
        this.checkExpressState(newVal.id, this.cartAllPrice)
      },
      coupon (newVal) {
        if (newVal) {
          this.checkCouponDiscountPrice()
        } else {
          this.couponDiscountPrice = 0
          this.couponInfoTip = null
          this.couponInfoDetail = null
        }
      },
      payWay (newVal) {
        if (newVal && this.coupon) {
          this.checkCouponDiscountPrice()
        }
      },
      showTimePicker (newVal) {
        if (!newVal && times) {
          const day = this.appointment[0]
          const hour = this.appointment[1]
          const minute = this.appointment[2]
          if (day === '今天' && hour === '尽快配送') {
            this.deliveryType = '尽快配送'
          } else {
            this.deliveryType = '预约点餐'
            this.deliveryArriveTime = `${day} ${hour}:${minute}`
          }
        }
      },
      deliveryType (newVal) {
        if (newVal === '尽快配送') {
          orderService.getDeliveryArriveTime()
            .then(res => {
              this.deliveryArriveTime = `大约 ${res.date} ${res.time}`
            })
        }
      }
    },
    computed: {
      cartAllCount () {
        return this.$store.getters.cartAllCount
      },
      cartAllPrice () {
        return this.$store.getters.cartAllPrice
      },
      address () {
        return this.$store.getters.currentAddress
      },
      coupon () {
        return this.$store.getters.currentCoupon
      }
    },
    data () {
      return {
        submitLoading: false,
        showOrderTip: false,
        disableService: false,
        tipNotice: '',
        orderId: '',
        order: {},
        orderItems: [],
        payWay: 'WEIXIN_PAY',
        couponDiscountPrice: 0,
        couponInfoTip: null,
        couponInfoDetail: null,
        psData: '',
        showTimePicker: false,
        appointment: [],
        appointmentIndex: [],
        appointmentTimes: [],
        deliveryType: '',
        deliveryArriveTime: null
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

      if (this.coupon) {
        this.checkCouponDiscountPrice()
      }
    },
    onShow () {
      if (this.orderId !== '') {
        mpvue.showLoading({
          title: '正在核对信息,请稍等...'
        })
        orderService.selectOrderById(this.orderId)
          .then(res => {
            mpvue.hideLoading()
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
                url: '/pages/order/main?state=WAIT_PAY'
              })
            }
          })
          .catch(() => {
            mpvue.hideLoading()
          })
      } else if (this.address) {
        this.checkExpressState(this.address.id, this.cartAllPrice)
      }
    },
    methods: {
      ...mapMutations('cart', [
        'CLEAR_CART'
      ]),
      ...mapMutations('address', [
        'SET_ADDRESS'
      ]),
      ...mapMutations('coupon', [
        'CLEAR_COUPON'
      ]),
      init () {
        Object.assign(this.$data, this.$options.data())

        // 获取订单项
        const cartGoodsList = this.$store.getters.cartGoodsList
        cartGoodsList.forEach(item => {
          let orderItem = {}
          orderItem.goodsId = item.goodsId
          orderItem.goodsName = item.goods.name
          orderItem.goodsThumb = item.goods.thumb
          orderItem.goodsPrice = item.goods.price
          orderItem.goodsCount = item.count
          this.orderItems.push(orderItem)
        })

        // 获取预约时间项
        indexService.getAppointmentTimes()
          .then(res => {
            times = res.appointmentTimes
            const canDeliveryNow = res.canDeliveryNow
            if (canDeliveryNow && !Object.keys(times).includes('今天')) {
              times = Object.assign({}, { '今天': { '尽快配送': [] } }, times)
            }

            const days = Object.keys(times)
            const day = days[0]
            const hours = Object.keys(times[day])
            const hour = hours[0]
            let minutes = times[day][hour]

            if (canDeliveryNow) {
              times['今天'] = Object.assign({}, { '尽快配送': [] }, times['今天'])
              if (!hours.includes('尽快配送')) {
                hours.splice(0, 0, '尽快配送')
              }
              minutes = []
            }

            if (days[0] === '今天' && hours[0] === '尽快配送') {
              this.deliveryType = '尽快配送'
            } else {
              this.deliveryType = '预约点餐'
              this.deliveryArriveTime = `${days[0]} ${hours[0]}:${minutes[0]}`
            }

            this.appointmentTimes = [days, hours, minutes]
            this.appointmentIndex = [0, 0, 0]
            this.appointment = [days[0], hours[0], minutes[0]]
          })
      },
      setCoupon () {
        mpvue.navigateTo({
          url: '/pages/sub-packages/coupon/index/main'
        })
      },
      deleteCoupon () {
        this.CLEAR_COUPON()
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
        orderService.createOrder(
          orderService.generateOrder(this.payWay, this.psData, indexService.formatAppointmentTime(this.deliveryType, this.appointment)),
          this.orderItems,
          this.coupon,
          this.address
        ).then(res => {
          this.submitLoading = false
          this.CLEAR_CART()
          this.CLEAR_COUPON()
          this.orderId = res.message
          payService.payOrder(this.orderId, this.payWay)
        }).catch(res => {
          this.submitLoading = false
          if (res) {
            console.error(res)
          }
        })
      },
      checkExpressState (addressId, allPrice) {
        this.disableService = false
        this.tipNotice = null
        indexService.getExpressServiceState(addressId, allPrice)
          .then(res => {
            if (res.state !== 0) {
              this.disableService = true
              this.tipNotice = res.message
            }
          })
      },
      checkCouponDiscountPrice () {
        this.couponDiscountPrice = 0
        this.couponInfoTip = null
        this.couponInfoDetail = couponService.getCouponDetail(this.coupon)
        couponService.checkCouponDiscountPrice(
          orderService.generateOrder(this.payWay),
          this.orderItems,
          this.coupon
        ).then(res => {
          this.couponDiscountPrice = res.message
        }).catch(res => {
          if (res) {
            this.couponInfoTip = res.message
          }
        })
      },
      onTimePickerChange (event) {
        const { value } = event.mp.detail
        console.log(value)

        const days = Object.keys(times)
        const day = days[value[0]]
        const hours = Object.keys(times[day])
        if (hours.includes('尽快配送')) {
          hours.splice(hours.length - 1, 1)
          hours.splice(0, 0, '尽快配送')
        }
        const hour = hours[value[1]]
        const minutes = times[day][hour]

        // const minute = times[day][hour][value[2]]
        if (this.appointment[0] !== days[value[0]]) {
          this.appointmentTimes[1] = hours
          this.appointmentTimes[2] = minutes
        }

        if (this.appointment[1] !== hours[value[1]]) {
          this.appointmentTimes[2] = minutes
        }

        this.appointmentIndex = value

        if (this.showTimePicker) {
          if (value[0] < days.length && value[1] < hours.length && value[2] < minutes.length) {
            this.appointment = [days[value[0]], hours[value[1]], minutes[value[2]]]
          } else {
            let minute = null
            if (minutes && minutes.length > 0) {
              minute = minutes[0]
            }
            this.appointment = [days[0], hours[0], minute]
          }
        }
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

  .coupon-panel {
    display: flex;
    justify-content: space-between;
  }

  .coupon-discounted-prices {
    color: red;
    text-align: right;
    font-weight: bold;
    font-size: .27rem;
  }

  .coupon-detail-info {
    color: #999999;
    margin-top: .2rem;
    font-size: .25rem;
  }

  .coupon-delete-btn {
    position: relative;
    top: .05rem;
  }

  .time-picker-content {
    height: 4rem;
    padding: 0 .8rem;
    text-align: center
  }
</style>
