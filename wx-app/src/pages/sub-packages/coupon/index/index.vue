<template>
  <div style="display: unset">
    <div class="container" v-if="couponList.length > 0">
      <div class="gradientDiv"></div>
      <div class="container-contain">
        <view style="text-align: right;padding: 0 .7rem .5rem 0">
          <button @click="isShowDialog=true" class="exchange-btn" size="mini">兑换优惠卷</button>
        </view>
        <coupon-card
          :coupon="coupon"
          :key="coupon.id"
          v-for="coupon in couponList"/>
      </div>
    </div>
    <div class="none-content-div" v-if="couponList.length === 0">
      <img mode="aspectFit" src="/static/images/none/no_coupon.png" style="width: 10rem">
      <div style="margin-top: .5rem">暂无优惠卷信息</div>
      <button @click="isShowDialog=true" class="exchange-btn" size="mini">兑换优惠卷</button>
    </div>

    <van-dialog
      :async-close="true"
      :show="isShowDialog"
      @cancel="isShowDialog=false"
      @confirm="exchangeCoupon"
      cancel-button-text="关闭"
      confirm-button-color="#FFD200"
      confirm-button-text="兑换"
      show-cancel-button
      title="兑换优惠卷"
      use-slot>
      <textarea
        class="comment-textarea"
        maxlength="32"
        name="textarea"
        placeholder="请输入优惠卷ID"
        placeholder-style="color:gray"
        style="height: 3rem;padding: .5rem;font-size: 14px"
        v-model="exchangeCouponId"/>
    </van-dialog>
  </div>
</template>

<script>
  import CouponCard from '../components/CouponCard'
  import couponService from '@/services/coupon'

  export default {
    components: {
      CouponCard
    },
    data () {
      return {
        isShowDialog: false,
        couponList: [],
        exchangeCouponId: null
      }
    },
    onLoad () {
      this.init()
    },
    onPullDownRefresh () {
      this.init()
      mpvue.stopPullDownRefresh()
    },
    methods: {
      init () {
        couponService.getCouponListU()
          .then(res => {
            this.couponList = res
          })
      },
      onExchangeCouponIdChange (event) {
        console.log(event.mp.detail)
        this.exchangeCouponId = event.mp.detail
      },
      exchangeCoupon (event) {
        couponService.exchangeCoupon(this.exchangeCouponId)
          .then(res => {
            event.mp.detail.dialog.stopLoading()
            if (res.code === 0) {
              mpvue.showToast({
                title: res.message,
                icon: 'success',
                duration: 2000
              })
              this.exchangeCouponId = null
              this.init()
            } else {
              mpvue.showToast({
                title: res.message,
                image: '/static/images/error.png',
                duration: 2000
              })
            }
          })
          .catch(() => {
            event.mp.detail.dialog.stopLoading()
          })
      }
    }
  }
</script>

<style scoped>
  .none-content-div {
    position: relative;
    z-index: 100;
    width: 100%;
    height: 100%;
    text-align: center;
  }

  .exchange-btn {
    margin-top: .2rem;
    padding-left: 14px;
    padding-right: 14px;
    font-size: 16px;
    background: #FFD200;
    border: none;
    color: black;
  }

  .container-contain .exchange-btn{
    background: white;
    color: #FFD200;
    box-shadow: 0 5px 25px 0 rgba(0, 0, 0, .13);
  }
</style>
