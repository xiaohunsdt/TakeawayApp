import orderService from '../../../services/order'
import payService from '../../../services/pay'

export default Behavior({
  methods: {
    payNow (event) {
      payService.payOrder(this.data.order.id, this.data.order.paymentWay)
    },
    confirmGetFood (event) {
      const this_ = this
      wx.showModal({
        title: '提示',
        content: '您确定已经收到订餐？',
        success: function (res) {
          if (res.confirm) {
            orderService.confirmGetOrder(this_.data.order.id)
              .then(res => {
                this_.triggerEvent('refresh-list')
                wx.showToast({
                  title: res.message,
                  duration: 2000
                })
              })
          }
        }
      })
    },
    shareOrder (event) {
      wx.showToast({
        title: '正在开发中...',
        duration: 2000
      })
    },
    comment () {
      wx.navigateTo({
        url: `/pages/order/comment/index?orderId=${this.data.order.id}`
      })
    },
    deleteOrder () {
      orderService.deleteOrder(this.data.order.id).then(res => {
        this.triggerEvent('refresh-list')
        wx.showToast({
          title: res.message,
          duration: 2000
        })
      })
    }
  }
})
