<template>
  <div class="container">
    <div class="gradientDiv"></div>
    <div class="container-contain">
      {{orderId}}
      <van-button type="default" :loading="false" @click="pay">
        支付
      </van-button>
    </div>
  </div>
</template>

<script>
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

            }
        }
    }
</script>

<style scoped>
  .container-contain {
    padding: 0.3rem 0.3rem;
  }
</style>
