import orderService from '@/services/order'
import payService from '@/services/pay'

export default {
  methods: {
    payNow (event) {
      payService.payOrder(this.order.id, this.order.paymentWay)
    },
    confirmGetFood (event) {
      const this_ = this
      mpvue.showModal({
        title: '提示',
        content: '您确定已经收到订餐？',
        success: function (res) {
          if (res.confirm) {
            orderService.confirmGetOrder(this_.order.id)
              .then(res => {
                this_.$emit('refresh-list')
                mpvue.showToast({
                  title: res.message,
                  duration: 2000
                })
              })
          }
        }
      })
    },
    shareOrder (event) {
      mpvue.showToast({
        title: '正在开发中...',
        duration: 2000
      })
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
