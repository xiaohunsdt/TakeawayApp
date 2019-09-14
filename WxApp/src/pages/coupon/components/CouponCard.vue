<template>
  <base-panel>
    <div class="info">
      <div class="description">满8减2</div>
      <van-tag color="#FFD200">满减</van-tag>
      <div class="endDate">有限期至 {{coupon.endDate}}</div>
    </div>
    <div class="priceInfo">
      ₩
      <div class="price">{{coupon.price}}</div>
      <van-button
        class="use-btn"
        round
        size="small">
        立刻使用
      </van-button>
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
    background-color: #FFD200 !important;
    padding: 0 .3rem !important;
    font-weight: bolder;
  }
</style>

<style scoped>
  van-icon {
    display: flex;
    align-items: center;
  }

  .background-icon {
    display: flex;
    align-content: center;
    justify-content: center;
    border-radius: 50%;
    width: 1.5rem;
    height: 1.5rem;
    background-color: #FFD200;
    opacity: 0.6;
    position: relative;
    right: -5.6rem;
    bottom: -.5rem;
    z-index: 0;
  }
</style>
