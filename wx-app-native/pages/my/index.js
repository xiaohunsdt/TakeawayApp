import userService from '../../services/user'
import orderService from '../../services/order'

Page({
  data: {
    userInfo: null,
    orderCount: {
      waitPay: 0,
      waitEat: 0,
      waitComment: 0,
      refund: 0
    },
    signInDays: null
  },
  onShow: function () {
    this.init()
  },
  onPullDownRefresh: function () {
    this.init()
    wx.stopPullDownRefresh()
  },
  init() {
    // 获取用户信息
    if (!this.userInfo && wx.getStorageSync('userInfo')) {
      this.setData({
        userInfo: wx.getStorageSync('userInfo')
      })
    }
    orderService.getOrderCountByState('WAIT_PAY').then(res => {
      this.setData({
        "orderCount.waitPay": res
      })
    })
    orderService.getOrderCountByState('WAIT_EAT').then(res => {
      this.setData({
        "orderCount.waitEat": res
      })
    })
    orderService.getOrderCountByState('WAIT_COMMENT').then(res => {
      this.setData({
        "orderCount.waitComment": res
      })
    })
    orderService.getOrderCountByState('REFUND').then(res => {
      this.setData({
        "orderCount.refund": res
      })
    })
    userService.getSignInDays().then(res => {
      let signInedDay = []
      if (res.signInedDay) {
        res.signInedDay.forEach(item => {
          signInedDay.push(parseInt(item))
        })
      }

      res.beginOfMonth = parseInt(res.beginOfMonth)
      res.endOfMonth = parseInt(res.endOfMonth)
      res.signInedDay = signInedDay

      this.setData({
        "signInDays": res
      })
    })
  },
  getWxUserInfo(event) {
    if (event.mp.detail.userInfo) {
      // 将用户信息保存到服务器，保存成功后将被存储到本地
      userService.setUserInfo()
        .then(() => {
          this.userInfo = event.mp.detail.userInfo
        })
    } else {
      console.error('授权失败!!!')
    }
  },
  callCSPhone() {
    wx.makePhoneCall({
      phoneNumber: '01056511996'
    })
  },
  gotoOrderPage(event) {
    let state = event.currentTarget.dataset.orderType
    wx.navigateTo({
      url: `/pages/order/main?state=${state}`
    })
  }
})