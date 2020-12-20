import userService from '../../services/user'
import payService from '../../services/pay'
import util from '../../utils/util'

Page({

  /**
   * Page initial data
   */
  data: {
    orderId: '',
    loading: false
  },

  /**
   * Lifecycle function--Called when page load
   */
  onLoad(options) {
    console.log(options)

    this.loading = false
    if (options.orderId) {
      console.log(`当前OrderId: ${options.orderId}`)
      this.setData({
        orderId: options.orderId
      })
      this.pay()
    }
  },
  pay() {
    const this_ = this

    this.setData({
      loading: true
    })
    userService.checkLogin()
      .then(() => {
        payService.payOrder(this.data.orderId)
          .then(res => {
            payService.callWxPayApi(res)
              .then(res => {
                // payService.confirmOrder(this.orderId)
              })
              .finally(() => {
                wx.navigateBackMiniProgram({
                  extraData: {
                    orderId: this_.data.orderId,
                    res
                  }
                })
              })
          })
          .catch(res => {
            util.showErrorToast(res.message)
          })
          .finally(() => {
            this.setData({
              loading: false
            })
          })
      })
      .catch(res => {
        util.showErrorToast(res.message)
      })
      .finally(() => {
        this.setData({
          loading: false
        })
      })
  }
})