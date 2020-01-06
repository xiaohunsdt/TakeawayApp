<template>
  <base-panel>
    <div class="coupon-item">
      <div class="info">
        <div class="description">{{coupon.couponName}}</div>
        <van-tag color="#FFD200">{{couponType}}</van-tag>
        <div class="endDate">有限期至 <span v-if="coupon.expireDate">{{expireDate}}</span></div>
      </div>
      <div class="priceInfo">
        <div class="price">
          <div v-if="coupon.couponMoney > 0">
            ₩
            <div class="price-number">{{coupon.couponMoney}}</div>
          </div>
          <div v-if="coupon.couponDiscount > 0">
            <div class="price-number">{{coupon.couponDiscount}}</div>
            折
          </div>
        </div>
        <van-button
          @click="onClick"
          custom-class="use-btn"
          round
          size="small">
          立刻使用
        </van-button>
      </div>
    </div>
    <div class="background-icon">
      <van-icon color="white" name="point-gift" size="4rem"/>
    </div>
  </base-panel>
</template>

<script>
  import BasePanel from '@/components/BasePanel'
  import { formatCouponState, formatCouponType, formatDate } from '@/utils/index'

  export default {
    name: 'CouponCard',
    components: {
      BasePanel
    },
    props: {
      coupon: {
        type: Object,
        required: true
      }
    },
    computed: {
      expireDate () {
        return formatDate(new Date(this.coupon.expireDate))
      },
      couponType () {
        return formatCouponType(this.coupon.couponType)
      },
      CouponStateFormat: function (value) {
        return formatCouponState(value)
      }
    },
    data () {
      return {}
    },
    methods: {
      onClick () {
        mpvue.switchTab({
          url: '/pages/goods/main'
        })
      }
    }
  }
</script>

<style>
  .use-btn {
    margin-top: .2rem;
    background-color: #FFD200 !important;
    padding: 0 .3rem !important;
    font-weight: bolder;
    border: none !important;
  }
</style>

<style scoped>
  van-icon {
    display: flex;
    align-items: center;
  }

  .background-icon {
    display: inline-flex;
    align-content: center;
    justify-content: center;
    border-radius: 50%;
    width: 1.5rem;
    height: 1.5rem;
    background-color: #FFD200;
    opacity: 0.6;
    position: absolute;
    left: 3rem;
    bottom: .7rem;
    z-index: 0;
  }

  .coupon-item {
    display: flex;
  }

  .info {
    width: 70%;
  }

  .priceInfo {
    border-left: gainsboro 0.01rem solid;
    width: 30%;
    text-align: right;
  }

  .info .description {
    font-weight: bolder;
  }

  .info .endDate {
    margin-top: .2rem;
    color: #999999;
    font-size: .2rem;
  }

  .priceInfo .price {
    font-size: .3rem;
  }

  .priceInfo .price .price-number {
    font-size: .5rem;
    font-weight: bolder;
    display: inline-block;
  }
</style>
