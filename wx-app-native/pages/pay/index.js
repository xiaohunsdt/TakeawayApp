import orderService from '../../services/order'

Page({

  /**
   * 页面的初始数据
   */
  data: {
    orderId: null,
    order: null
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    if (options.orderId) {
      this.setData({
        orderId: options.orderId
      })
      this.init()
    }
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
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
    orderService.selectOrderById(this.data.orderId).then(res => {
      this.setData({
        order: res
      })
      if (this.data.order.payState === 'PAID') {
        // mpvue.reLaunch({
        //   url: `/pages/order/detail/main?orderId=${this.order.id}`
        // })
        wx.reLaunch({
          url: '/pages/order/index?state=WAIT_EAT'
        })
      }
    })
  }
})