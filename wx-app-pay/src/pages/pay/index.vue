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
                orderId: '87696c5a71687ed7aa75e51655d0fdea'
            }
        },
        onLoad (options) {
            if (options.orderId) {
                this.orderId = options.orderId
            }
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
                            .catch(() => {
                                util.showErrorToast('支付失败')
                            })
                    })
                    .catch(() => {
                        userApi.loginByWx()
                            .then(res => {
                                this_.pay()
                            })
                            .catch(() => {
                                util.showErrorToast('登录失败')
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
