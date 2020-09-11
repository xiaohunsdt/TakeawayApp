import orderService from '../../services/order'
import settingService from '../../services/setting'

Page({
  data: {
    orderId: null,
    order: null,
    bank: '',
    account: '',
    accountName: ''
  },
  onLoad: function (options) {
    if (options.orderId) {
      this.setData({
        orderId: options.orderId
      })
    }
  },
  onShow: function () {
    this.init()
  },
  onPullDownRefresh: function () {
    this.init()
    wx.stopPullDownRefresh()
  },
  init() {
    orderService.selectOrderById(this.data.orderId).then(res => {
      this.setData({
        order: res
      })
      if (this.data.order.payState === 'PAID') {
        wx.reLaunch({
          url: '/pages/order/index?state=WAIT_EAT'
        })
        return
      }

      if (this.data.order.paymentWay === 'TRANSFER') {
        wx.showLoading({
          title: '加载商家信息中...'
        })
        settingService.getPaymentSettings(this.data.order.storeId)
          .then(res => {
            this.setData({
              bank: res.bank,
              account: res.account,
              accountName: res.accountName
            })
          })
          .finally(() => {
            wx.hideLoading()
          })
      }
    })
  }
})