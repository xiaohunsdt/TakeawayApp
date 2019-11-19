<template>
  <div class="container">
    <div class="gradientDiv"></div>
    <div class="container-contain">
<!--      <coupon-card-->
<!--        v-for="coupon in couponList"-->
<!--        :key="coupon.id"-->
<!--        :coupon="coupon" />-->
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
          {
            id: 1,
            type: 1,
            factor: 15000,
            price: 1000,
            discount: 8,
            endDate: new Date().toString(),
            state: 1
          },
          {
            id: 2,
            type: 1,
            price: 1000,
            discount: 8,
            endDate: new Date().toString(),
            state: 1
          }
        ]
      }
    },
    methods: {}
  }
</script>

<style scoped>

</style>
