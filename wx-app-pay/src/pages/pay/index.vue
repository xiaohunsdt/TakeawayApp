<template>
  <div class="container">
    <div class="gradientDiv"></div>
    <div class="container-contain">
      {{orderId}}
      <van-button :loading="false" @click="pay" type="default">
        支付
      </van-button>
    </div>
  </div>
</template>

<script>
  import payApi from '@/services/pay'

  export default {
    data () {
      return {
        orderId: 'asdadasdsad'
      }
    },
    onLoad (options) {
      this.orderId = options.orderId
    },
    methods: {
      pay () {
        payApi.payOrder(this.orderId)
          .then(res => {
            console.log(res)
          })
      }
    }
  }
</script>

<style scoped>
  .container-contain {
    padding: 0.3rem 0.3rem;
  }
</style>
