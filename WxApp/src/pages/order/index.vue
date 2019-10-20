<template>
  <div class="container">
    <div class="gradientDiv"></div>
    <div class="container-contain">
      <order-card
        :key="order.orderId"
        :order="order"
        v-for="order in orderList"/>
    </div>
  </div>
</template>

<script>
  import OrderCard from './components/OrderCard'
  import orderService from '@/services/order'

  export default {
    components: {
      OrderCard
    },
    onLoad (options) {
      this.orderState = options.state
      this.init()
    },
    onPullDownRefresh () {
      this.init()
    },
    data () {
      return {
        page: {
          current: 1,
          size: 10,
          total: 0
        },
        orderState: '',
        orderList: []
      }
    },
    methods: {
      init () {
        orderService.getOrderListByPage(this.state).then(res => {
          res.records.forEach(item => {
            this.orderList.push(item)
          })
          this.page.total = parseInt(res.total)
        })
      }
    }
  }
</script>

<style scoped>
  .container-contain {
    padding: 0.3rem 0.1rem;
  }
</style>
