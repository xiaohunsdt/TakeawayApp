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
              <div class="view-all">
                查看所有订单
                <van-icon name="arrow"></van-icon>
              </div>
            </div>
            <van-col offset="1" span="4">
              <div class="order-type-item">
                <img alt="" class="item-img" mode="widthFix" src="/static/images/order/payment.png">
                <div class="item-title">待付款</div>
              </div>
            </van-col>
            <van-col offset="2" span="4">
              <div class="order-type-item">
                <img alt="" class="item-img" mode="widthFix" src="/static/images/order/take_food.png">
                <div class="item-title">待就餐</div>
              </div>
            </van-col>
            <van-col offset="2" span="4">
              <div class="order-type-item">
                <img alt="" class="item-img" mode="widthFix" src="/static/images/order/evaluate.png">
                <div class="item-title">待评价</div>
              </div>
            </van-col>
            <van-col offset="2" span="4">
              <div class="order-type-item">
                <img alt="" class="item-img" mode="widthFix" src="/static/images/order/refund.png">
                <div class="item-title">退款</div>
              </div>
            </van-col>
          </van-row>
        </base-panel>
      </div>
      <div class="my-profile">
        <van-cell custom-class="profile-cell" is-link>
          <view class="profile-title" slot="title">
            <img alt="" class="title-img" src="/static/images/profile/edit.png">
            <div>
              个人资料
            </div>
          </view>
        </van-cell>
        <van-cell custom-class="profile-cell" is-link>
          <view class="profile-title" slot="title">
            <img alt="" class="title-img" src="/static/images/address/icon-location.png">
            <div>
              我的地址
            </div>
          </view>
        </van-cell>
        <van-cell custom-class="profile-cell" is-link>
          <view class="profile-title" slot="title">
            <img alt="" class="title-img" src="/static/images/profile/coupon.png">
            <div>
              优惠卷
            </div>
          </view>
        </van-cell>
        <van-cell custom-class="profile-cell" is-link>
          <view class="profile-title" slot="title">
            <img alt="" class="title-img" src="/static/images/profile/phone.png">
            <div>
              电话客服
            </div>
          </view>
        </van-cell>
        <van-cell custom-class="profile-cell" is-link>
          <view class="profile-title" slot="title">
            <img alt="" class="title-img" src="/static/images/profile/onlineservice.png">
            <div>
              在线客服
            </div>
          </view>
        </van-cell>
      </div>
    </div>
  </div>
</template>

<script>
  import BasePanel from '@/components/BasePanel'
  import userApi from '@/services/user'

  export default {
    components: {
      BasePanel
    },
    data () {
      return {
        userInfo: null
      }
    },
    created () {
      // 获取用户信息
      if (mpvue.getStorageSync('userInfo')) {
        this.userInfo = mpvue.getStorageSync('userInfo')
      }
    },
    methods: {
      getWxUserInfo (event) {
        if (event.mp.detail.userInfo) {
          // 将用户信息保存到服务器，保存成功后将被存储到本地
          userApi.setUserInfo()
            .then(() => {
              this.userInfo = mpvue.getStorageSync('userInfo')
            })
        } else {
          console.error('授权失败!!!')
        }
      }
    }
  }
</script>

<style>
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

  .my-profile {
    margin-top: 0.2rem;
  }
</style>
