<template>
  <div class="container">
    <div class="gradientDiv"></div>
    <div class="container-contain">
      <base-panel>
        <div style="text-overflow:ellipsis;word-wrap:break-word">
          {{orderId}}
        </div>
      </base-panel>
      <van-button @click="pay" size="small" type="primary">支付</van-button>
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
        orderId: ''
      }
    },
    onLoad (options) {
      console.log(options)
      if (options.orderId) {
        console.log(`当前OrderId: ${options.orderId}`)
        this.orderId = options.orderId
        this.pay()
      }
    },
    methods: {
      pay () {
        const this_ = this
        userApi.checkLogin()
          .then(() => {
            payApi.payOrder(this.orderId)
              .then(res => {
                wx.navigateBackMiniProgram({
                  extraData: {
                    orderId: this_.orderId,
                    res
                  }
                })
              })
              .catch((res) => {
                wx.navigateBackMiniProgram({
                  extraData: {
                    orderId: this_.orderId,
                    res
                  }
                })
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
