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

      let title = '全部订单'
      switch (this.orderState) {
        case 'waitPay':
          title = '未支付的订单'
          break
        case 'waitEat':
          title = '待就餐的订单'
          break
        case 'waitComment':
          title = '待评价的订单'
          break
        case 'refund':
          title = '退款的订单'
          break
      }
      wx.setNavigationBarTitle({
        title
      })
    },
    onPullDownRefresh () {
      this.init()
    },
    onReachBottom () {
      if (this.page.current + 1 <= this.page.total) {
        this.page.current += 1
        this.getOrderList()
      }
    },
    data () {
      return {
        page: {
          current: 1,
          size: 5,
          total: 0
        },
        orderState: '',
        orderList: []
      }
    },
    methods: {
      init () {
        this.orderList.splice(0, this.orderList.length)
        this.page.current = 1
        this.getOrderList()
      },
      getOrderList () {
        mpvue.showLoading({
          title: '加载中'
        })
        orderService.getOrderListByPage(this.page, this.orderState).then(res => {
          res.records.forEach(item => {
            this.orderList.push(item)
          })
          this.page.total = parseInt(res.total)
          mpvue.hideLoading()
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
