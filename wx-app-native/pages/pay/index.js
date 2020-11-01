import orderService from '../../services/order'
import settingService from '../../services/setting'

Page({
  data: {
    orderId: null,
    order: null,
    showContactDialog: false,
    storeWxContact: '',
    bank: '',
    account: '',
    accountName: ''
  },
  onLoad: function (options) {
    if (options.orderId) {
      this.setData({
        orderId: options.orderId
      })
      this.init()
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
          // url: '/pages/order/index?state=WAIT_EAT'
          url: '/pages/order/success/index'
        })
        return
      }

      if (this.data.order.paymentWay === 'TRANSFER') {
        wx.showLoading({
          title: '加载商家信息中...'
        })
        settingService.getPaymentSettings()
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
  },
  openContactDialog() {
    settingService.getSettingByName('store_wx_account', 'STORE')
      .then(res => {
        wx.showLoading({
          title: '正在加载中...'
        })
        const storeWxContact = res.value || ''
        this.setData({
          storeWxContact,
          showContactDialog: true
        })
      })
      .finally(() => {
        wx.hideLoading()
      })
  },
  copyStoreWxContact() {
    if (this.data.storeWxContact !== '') {
      wx.setClipboardData({
        data: this.data.storeWxContact
      });
    }
  }
})