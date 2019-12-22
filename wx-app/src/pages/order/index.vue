<template>
  <div style="display: unset">
    <div class="container" v-if="orderList.length > 0">
      <div class="gradientDiv"></div>
      <div class="container-contain">
        <order-card
          :key="order.orderId"
          :order="order"
          @refresh-list="init"
          v-for="order in orderList"/>
      </div>
    </div>
    <div class="none-content-div" v-if="orderList.length === 0">
      <img mode="aspectFit" src="/static/images/none/no_order.png" style="width: 10rem">
      <div style="margin-top: .5rem">暂无订单信息</div>
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
    onShow () {
      this.init()
    },
    onPullDownRefresh () {
      this.init()
      mpvue.stopPullDownRefresh()
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
          this.orderList.push(...res.records)
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
  .none-content-div {
    position: relative;
    z-index: 100;
    width: 100%;
    height: 100%;
    text-align: center;
  }
</style>
