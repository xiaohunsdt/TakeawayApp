Page({
  data: {},
  onLoad: function (options) {},
  onShow: function () {},
  onShareAppMessage: function () {

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