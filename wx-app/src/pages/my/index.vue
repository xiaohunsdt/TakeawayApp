<template>
  <div class="container">
    <div class="gradientDiv"></div>
    <div class="container-contain">
      <div class="my-header">
        <van-row>
          <van-col span="5">
            <img
              :src="userInfo.avatarUrl"
              class="myThumb"
              mode="aspectFill"
              v-if="userInfo">
            <img
              class="myThumb"
              mode="aspectFill"
              src="/static/images/profile/un-avatar.png" v-else>
          </van-col>
          <van-col span="19">
            <div class="myName">
              <span v-if="userInfo">{{userInfo.nickName}}</span>
              <button
                @getuserinfo="getWxUserInfo"
                class="login-button"
                open-type="getUserInfo"
                v-else>
                点击授权个人信息 >
              </button>
            </div>
            <div class="memberType">
              <van-icon name="diamond-o"/>
              <span style="margin-left:0.3rem;position: relative;top:-0.1rem">一般会员</span>
            </div>
          </van-col>
        </van-row>
      </div>
      <div class="my-order-bar">
        <base-panel>
          <van-row>
            <div class="my-order-header">
              <div class="title">我的订单</div>
              <div @click="gotoOrderPage(null)" class="view-all">
                查看所有订单
                <van-icon name="arrow"></van-icon>
              </div>
            </div>
            <van-col offset="1" span="4">
              <div @click="gotoOrderPage('WAIT_PAY')" class="order-type-item">
                <van-info :info="orderCount.waitPay" v-if="orderCount.waitPay > 0"></van-info>
                <img class="item-img" mode="widthFix" src="/static/images/order/payment.png">
                <div class="item-title">待付款</div>
              </div>
            </van-col>
            <van-col offset="2" span="4">
              <div @click="gotoOrderPage('WAIT_EAT')" class="order-type-item">
                <van-info :info="orderCount.waitEat" v-if="orderCount.waitEat > 0"></van-info>
                <img class="item-img" mode="widthFix" src="/static/images/order/take_food.png">
                <div class="item-title">待就餐</div>
              </div>
            </van-col>
            <van-col offset="2" span="4">
              <div @click="gotoOrderPage('WAIT_COMMENT')" class="order-type-item">
                <van-info :info="orderCount.waitComment" v-if="orderCount.waitComment > 0"></van-info>
                <img class="item-img" mode="widthFix" src="/static/images/order/evaluate.png">
                <div class="item-title">待评价</div>
              </div>
            </van-col>
            <van-col offset="2" span="4">
              <div @click="gotoOrderPage('REFUND')" class="order-type-item">
                <van-info :info="orderCount.refund" v-if="orderCount.refund > 0"></van-info>
                <img class="item-img" mode="widthFix" src="/static/images/order/refund.png">
                <div class="item-title">退款</div>
              </div>
            </van-col>
          </van-row>
        </base-panel>
      </div>
      <div class="my-sign-in">
        <base-panel v-if="signInDays">
          <div class="title">我的签到</div>
          <van-calendar
            :default-date="signInDays.signInedDay"
            :max-date="signInDays.endOfMonth"
            :min-date="signInDays.beginOfMonth"
            :poppable="false"
            :row-height="32"
            :show-confirm="false"
            :show-subtitle="false"
            :show-title="false"
            class="calendar"
            color="#FFD200"
            type="multiple"
          />
        </base-panel>
      </div>

      <div class="my-profile">
        <van-cell clickable custom-class="profile-cell" is-link>
          <view class="profile-title" slot="title">
            <img class="title-img" src="/static/images/profile/edit.png">
            <div>
              个人资料
            </div>
          </view>
        </van-cell>
        <van-cell clickable custom-class="profile-cell" is-link url="/pages/my/address/main">
          <view class="profile-title" slot="title">
            <img class="title-img" src="/static/images/address/icon-location.png">
            <div>
              我的地址
            </div>
          </view>
        </van-cell>
        <van-cell clickable custom-class="profile-cell" is-link url="/pages/sub-packages/coupon/index/main">
          <view class="profile-title" slot="title">
            <img class="title-img" src="/static/images/profile/coupon.png">
            <div>
              优惠卷
            </div>
          </view>
        </van-cell>
        <van-cell @click="callCSPhone" clickable custom-class="profile-cell" is-link>
          <view class="profile-title" slot="title">
            <img class="title-img" src="/static/images/profile/phone.png">
            <div>
              电话客服
            </div>
          </view>
        </van-cell>
        <van-cell clickable custom-class="profile-cell" is-link>
          <view class="profile-title contact-cell" slot="title">
            <button class="contact-btn" open-type="contact" plain>
              <img class="title-img" src="/static/images/profile/onlineservice.png">
              <div style="display: inline">
                在线客服
              </div>
            </button>
          </view>
        </van-cell>
      </div>
    </div>
  </div>
</template>

<script>
  import BasePanel from '@/components/BasePanel'
  import userService from '@/services/user'
  import orderService from '@/services/order'

  export default {
    components: {
      BasePanel
    },
    data () {
      return {
        userInfo: null,
        orderCount: {
          waitPay: 0,
          waitEat: 0,
          waitComment: 0,
          refund: 0
        },
        signInDays: null
      }
    },
    onShow () {
      this.init()
    },
    onPullDownRefresh () {
      this.init()
      mpvue.stopPullDownRefresh()
    },
    methods: {
      init () {
        // 获取用户信息
        if (!this.userInfo && mpvue.getStorageSync('userInfo')) {
          this.userInfo = mpvue.getStorageSync('userInfo')
        }
        orderService.getOrderCountByState('WAIT_PAY').then(res => {
          this.orderCount.waitPay = res
        })
        orderService.getOrderCountByState('WAIT_EAT').then(res => {
          this.orderCount.waitEat = res
        })
        orderService.getOrderCountByState('WAIT_COMMENT').then(res => {
          this.orderCount.waitComment = res
        })
        orderService.getOrderCountByState('REFUND').then(res => {
          this.orderCount.refund = res
        })
        userService.getSignInDays().then(res => {
          let signInedDay = []
          res.signInedDay.forEach(item => {
            signInedDay.push(parseInt(item))
          })

          res.beginOfMonth = parseInt(res.beginOfMonth)
          res.endOfMonth = parseInt(res.endOfMonth)
          res.signInedDay = signInedDay
          this.signInDays = res
        })
        console.log(new Date().getTime())
      },
      getWxUserInfo (event) {
        if (event.mp.detail.userInfo) {
          // 将用户信息保存到服务器，保存成功后将被存储到本地
          userService.setUserInfo()
            .then(() => {
              this.userInfo = event.mp.detail.userInfo
            })
        } else {
          console.error('授权失败!!!')
        }
      },
      callCSPhone () {
        mpvue.makePhoneCall({
          phoneNumber: '01056511996'
        })
      },
      gotoOrderPage (state) {
        mpvue.navigateTo({
          url: `/pages/order/main?state=${state}`
        })
      }
    }
  }
</script>

<style>
  .my-sign-in .title {
    margin-left: .2rem;
    /*margin-bottom: .2rem;*/
    font-size: .35rem;
  }

  .my-profile {
    margin-top: .4rem;
  }

  .my-profile .profile-title {
    display: flex;
    flex-direction: row;
  }

  .my-profile .profile-title .title-img {
    height: 0.55rem;
    width: 0.55rem;
    margin-right: 0.1rem;
  }

  .my-profile .profile-cell {
    background-color: transparent !important;
  }

  .my-profile .profile-cell:active {
    background-color: rgba(200, 200, 200, 0.2) !important;
  }

  .my-profile .contact-cell van-button {
    width: 100% !important;
  }

  .contact-btn {
    width: 100%;
    font-size: unset !important;
    font-weight: normal !important;
    line-height: unset !important;
    background: transparent;
    border: unset !important;
    padding: unset !important;
    display: flex !important;
    flex-direction: row;
    align-content: center;
    align-items: center;
    color: black !important;
  }

  .contact-btn:after {
    content: unset !important;
  }

  .order-type-item {
    position: relative;
  }

  .order-type-item .van-info {
    right: unset;
    top: .1rem;
    left: .6rem;
  }

  .van-calendar__month-title,.van-calendar__header {
    display: none;
  }
</style>
<style scoped>
  .my-header {
    padding: 0.3rem 0.4rem;
  }

  .login-button {
    display: unset;
    background: transparent;
    border: unset;
    font-size: 0.28rem;
    color: white;
    line-height: unset;
    padding-left: unset;
  }

  .login-button:after {
    content: unset;
  }

  .myName, .memberType {
    font-weight: bolder;
    color: white;
  }

  .myName {
    margin-top: 0.1rem;
  }

  .memberType {
    margin-top: 0.1rem;
    font-size: 0.28rem;
  }

  .myThumb {
    width: 1.2rem;
    height: 1.2rem;
    border-radius: 50%;
  }

  .my-order-header {
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    font-size: 0.35rem;
    margin-bottom: 0.3rem;
  }

  .my-order-header .title {
    margin-left: 0.2rem;
  }

  .my-order-header .view-all {
    font-size: 0.3rem;
    color: #999999;
  }

  .order-type-item .item-img {
    width: 100%;
  }

  .order-type-item .item-title {
    width: 100%;
    font-size: 0.25rem;
    font-weight: lighter;
    text-align: center;
  }
</style>
