import couponService from '../../services/coupon'
Page({

  /**
   * 页面的初始数据
   */
  data: {
    isShowDialog: false,
    couponList: [],
    exchangeCouponId: null
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.init()
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
    this.init()
    wx.stopPullDownRefresh()
  },
  init() {
    wx.showLoading({
      title: '正在加载中...'
    })
    couponService.getCouponListU()
      .then(res => {
        this.setData({
          couponList: res
        })
        wx.hideLoading()
      })
      .catch(() => {
        wx.hideLoading()
      })
  },
  onUseCoupon(event) {
    getApp().globalData.currentCoupon = event.currentTarget.dataset.coupon

    const pages = getCurrentPages()
    if (pages.length > 2 && pages[pages.length - 2].route === 'pages/buy/index') {
      wx.navigateBack()
    } else {
      wx.switchTab({
        url: '/pages/goods/index'
      })
    }
  },
  onExchangeCouponIdChange(event) {
    this.setData({
      exchangeCouponId: event.detail
    })
  },
  exchangeCoupon(event) {
    couponService.exchangeCoupon(this.data.exchangeCouponId)
      .then(res => {
        event.detail.dialog.stopLoading()
        if (res.code === 0) {
          wx.showToast({
            title: res.message,
            icon: 'success',
            duration: 2000
          })
          this.setData({
            exchangeCouponId: null
          })
          this.init()
        } else {
          wx.showToast({
            title: res.message,
            image: '/static/images/error.png',
            duration: 2000
          })
        }
      })
      .catch(() => {
        event.detail.dialog.stopLoading()
      })
  },
  onOpenDialog() {
    this.setData({
      isShowDialog: true
    })
  },
  onCloseDialog() {
    this.setData({
      isShowDialog: false
    })
  }
})