<template>
  <div style="display: unset">
    <div class="container" v-if="couponList.length > 0">
      <div class="gradientDiv"></div>
      <div class="container-contain">
        <coupon-card
          :coupon="coupon"
          :key="coupon.id"
          v-for="coupon in couponList"/>
      </div>
    </div>
    <div class="none-content-div" v-if="couponList.length === 0">
      <img mode="aspectFit" src="/static/images/none/no_coupon.png" style="width: 10rem">
      <div style="margin-top: .5rem">暂无优惠卷信息</div>
    </div>
  </div>
</template>

<script>
  import CouponCard from './components/CouponCard'

  export default {
    components: {
      CouponCard
    },
    data () {
      return {
        couponList: [
          // {
          //   id: 1,
          //   type: 1,
          //   factor: 15000,
          //   price: 1000,
          //   discount: 8,
          //   endDate: new Date().toString(),
          //   state: 1
          // },
          // {
          //   id: 2,
          //   type: 1,
          //   price: 1000,
          //   discount: 8,
          //   endDate: new Date().toString(),
          //   state: 1
          // }
        ]
      }
    },
    methods: {}
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
</style>
