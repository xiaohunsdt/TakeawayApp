<template>
  <base-panel>
    <div class="coupon-item">
      <div class="info">
        <div class="description">满8减2</div>
        <van-tag color="#FFD200">满减</van-tag>
        <div class="endDate">有限期至 2019-10-24</div>
      </div>
      <div class="priceInfo">
        <div class="price">₩
          <div class="price-number">{{coupon.price}}</div>
        </div>
        <van-button
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

  export default {
    name: 'CouponCard',
    props: {
      coupon: {
        type: Object,
        required: true
      }
    },
    data () {
      return {}
    },
    components: {
      BasePanel
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
    font-size: .2rem;
  }

  .priceInfo .price .price-number {
    font-size: .5rem;
    font-weight: bolder;
    display: inline-block;
  }
</style>
