<script>
  import { mapMutations } from 'vuex'

  export default {
    methods: {
      ...mapMutations('order', [
        'SET_ORDER_ID'
      ])
    },
    created () {
      console.log('created')
    },
    onLaunch (options) {
      console.log(options)
      if (options.referrerInfo.extraData.orderId) {
        console.log(`当前OrderId: ${options.referrerInfo.extraData.orderId}`)
        this.SET_ORDER_ID(options.referrerInfo.extraData.orderId)
      }
    }
  }
</script>

<style>
  ::-webkit-scrollbar {
    width: 0;
    height: 0;
    color: transparent;
  }

  page {
    height: 100%;
  }

  .container {
    background-color: #F0F0F0;
    min-height: 100%;
    width: 100%;
  }

  .container-contain {
    position: relative;
    z-index: 100;
  }

  .gradientDiv {
    position: absolute;
    background: linear-gradient(#FFD200, white, #F0F0F0);
    width: 100%;
    height: 7rem;
  }
</style>
