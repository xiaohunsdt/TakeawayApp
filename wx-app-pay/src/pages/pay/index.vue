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
  import userApi from '@/services/user'
  import payApi from '@/services/pay'
  import util from '@/utils/util'

  export default {
    data () {
      return {
        orderId: this.$store.getters.orderId
      }
    },
    onLoad () {
      this.pay()
    },
    methods: {
      pay () {
        const this_ = this
        userApi.checkLogin()
          .then(() => {
            payApi.payOrder(this.orderId)
              .then(res => {
                console.log(res)
              })
              .catch((res) => {
                util.showErrorToast(res.message)
              })
          })
          .catch(() => {
            userApi.loginByWx()
              .then(res => {
                this_.pay()
              })
              .catch(res => {
                util.showErrorToast(res.message)
              })
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
