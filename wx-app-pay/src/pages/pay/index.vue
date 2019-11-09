<template>
  <div class="container">
    <div class="gradientDiv"></div>
    <div class="container-contain">
      <base-panel>
        <div style="text-overflow:ellipsis;word-wrap:break-word">
          {{orderId}}
        </div>
      </base-panel>
      <van-button @click="pay" type="primary" size="small">支付</van-button>
    </div>
  </div>
</template>

<script>
  import userApi from '@/services/user'
  import payApi from '@/services/pay'
  import util from '@/utils/util'
  import BasePanel from '@/components/BasePanel'

  export default {
    components: {
      BasePanel
    },
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
                console.log(res)
                util.showErrorToast(res.errMsg)
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
    text-align: center;
  }
</style>
