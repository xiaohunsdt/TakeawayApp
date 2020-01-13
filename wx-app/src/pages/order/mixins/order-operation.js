import orderService from '@/services/order'
import payService from '@/services/pay'
import Dialog from '/static/vant/dialog/dialog'

export default {
  methods: {
    payNow (event) {
      payService.payOrder(this.order.id, this.order.paymentWay)
    },
    confirmGetFood (event) {
      Dialog.confirm({
        title: '提示',
        message: '您确定已经收到订餐?!'
      }).then(() => {
        orderService.confirmGetOrder(this.order.id).then(res => {
          this.$emit('refresh-list')
          mpvue.showToast({
            title: res.message,
            duration: 2000
          })
        })
      })
    },
    shareOrder (event) {
      console.log(event)
    },
    comment () {
      mpvue.navigateTo({
        url: `/pages/order/comment/main?orderId=${this.order.id}`
      })
    },
    deleteOrder () {
      orderService.deleteOrder(this.order.id).then(res => {
        this.$emit('refresh-list')
        mpvue.showToast({
          title: res.message,
          duration: 2000
        })
      })
    }
  }
}
