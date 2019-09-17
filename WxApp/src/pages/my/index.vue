<template>
  <div class="container">
    <div class="gradientDiv"></div>
    <div class="container-contain">
      <div class="my-header">
        <van-row>
          <van-col span="5">
            <img class="myThumb" mode="aspectFill" src="/static/images/food/food.jpg" alt="">
          </van-col>
          <van-col span="19">
            <div class="myName">Jeremy.</div>
            <div class="memberType">
              <van-icon name="diamond-o"/>
              一般会员
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
            <van-col span="4" offset="1">
              <div class="order-type-item">
                <img class="item-img" mode="widthFix" src="/static/images/order/payment.png" alt="">
                <div class="item-title">待付款</div>
              </div>
            </van-col>
            <van-col span="4" offset="2">
              <div class="order-type-item">
                <img class="item-img" mode="widthFix" src="/static/images/order/take_food.png" alt="">
                <div class="item-title">待就餐</div>
              </div>
            </van-col>
            <van-col span="4" offset="2">
              <div class="order-type-item">
                <img class="item-img" mode="widthFix" src="/static/images/order/evaluate.png" alt="">
                <div class="item-title">待评价</div>
              </div>
            </van-col>
            <van-col span="4" offset="2">
              <div class="order-type-item">
                <img class="item-img" mode="widthFix" src="/static/images/order/refund.png" alt="">
                <div class="item-title">退款</div>
              </div>
            </van-col>
          </van-row>
        </base-panel>
      </div>
      <div class="my-profile">
        <van-cell is-link custom-class="profile-cell">
          <view slot="title" class="profile-title">
            <img class="title-img" src="/static/images/profile/edit.png" alt="">
            <div>
              个人资料
            </div>
          </view>
        </van-cell>
        <van-cell is-link custom-class="profile-cell">
          <view slot="title" class="profile-title">
            <img class="title-img" src="/static/images/address/icon-location.png" alt="">
            <div>
              我的地址
            </div>
          </view>
        </van-cell>
        <van-cell is-link custom-class="profile-cell">
          <view slot="title" class="profile-title">
            <img class="title-img" src="/static/images/profile/coupon.png" alt="">
            <div>
              优惠卷
            </div>
          </view>
        </van-cell>
        <van-cell is-link custom-class="profile-cell">
          <view slot="title" class="profile-title">
            <img class="title-img" src="/static/images/profile/phone.png" alt="">
            <div>
              电话客服
            </div>
          </view>
        </van-cell>
        <van-cell is-link custom-class="profile-cell">
          <view slot="title" class="profile-title">
            <img class="title-img" src="/static/images/profile/onlineservice.png" alt="">
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

  export default {
    components: {
      BasePanel
    },
    data () {
      return {}
    },
    methods: {}
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

  .myName, .memberType {
    margin-top: 0.15rem;
    color: white;
  }

  .myName {
    font-weight: bolder;
  }

  .memberType {
    margin-top: 0.2rem;
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
