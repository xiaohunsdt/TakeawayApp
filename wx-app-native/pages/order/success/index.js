Page({
  data: {
    orderId: null
  },
  onLoad: function (options) {
    this.setData({
      orderId: options.orderId
    })
  },
  onShareAppMessage: function (event) {
    const userInfo = wx.getStorageSync('userInfo')
    console.log(event.target.dataset.detail)
    return {
      title:  `${userInfo.nickName} 分享的红包！快来领红包点外卖吧！`,
      path: `/pages/index/index?orderId=${event.target.dataset.detail}`,
      imageUrl: '/static/images/red_packet.jpg',
      success: function (e) {},
      fail: function (e) {}
    }
  },
  gotoPage(event) {
    let page = event.currentTarget.dataset.pagePath

    if (page === '') {
      return
    }
    wx.reLaunch({
      url: page,
    })
  },
})