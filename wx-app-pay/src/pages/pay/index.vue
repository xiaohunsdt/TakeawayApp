<template>
  <div class="container">
    <div class="gradientDiv"></div>
    <div class="container-contain">
      <base-panel>
        <div style="text-overflow:ellipsis;word-wrap:break-word">
          {{orderId}}
        </div>
      </base-panel>
      <van-button
        :disabled="loading"
        :loading="loading"
        @click="pay"
        loading-text="数据请求中..."
        type="primary">支付
      </van-button>
    </div>
  </div>
</template>

<script>
  import userService from '@/services/user'
  import payService from '@/services/pay'
  import util from '@/utils/util'
  import BasePanel from '@/components/BasePanel'

  export default {
    components: {
      BasePanel
    },
    data () {
      return {
        orderId: '',
        loading: false
      }
    },
    onLoad (options) {
      console.log(options)

      this.loading = false
      if (options.orderId) {
        console.log(`当前OrderId: ${options.orderId}`)
        this.orderId = options.orderId
        this.pay()
      }
    },
    methods: {
      pay () {
        const this_ = this
        this.loading = true
        userService.checkLogin()
          .then(() => {
            payService.payOrder(this.orderId)
              .then(res => {
                this.loading = false
                payService.callWxPayApi(res)
                  .then(res => {
                    payService.confirmOrder(this.orderId)
                    wx.navigateBackMiniProgram({
                      extraData: {
                        orderId: this_.orderId,
                        res
                      }
                    })
                  })
                  .catch((res) => {
                    console.log(res)
                    wx.navigateBackMiniProgram({
                      extraData: {
                        orderId: this_.orderId,
                        res
                      }
                    })
                  })
              })
              .catch(res => {
                this.loading = false
                util.showErrorToast(res.message)
              })
          })
          .catch(() => {
            this.loading = false
            userService.loginByWx()
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
