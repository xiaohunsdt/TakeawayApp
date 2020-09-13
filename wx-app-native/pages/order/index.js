import orderService from '../../services/order'

Page({

  /**
   * 页面的初始数据
   */
  data: {
    page: {
      current: 1,
      size: 5,
      total: 0
    },
    orderState: '',
    orderList: []
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    console.log(options.state)
    if (options.state && options.state !== "undefined") {
      this.setData({
        orderState: options.state
      })
    }

    let title = '全部订单'
    switch (this.data.orderState) {
      case 'WAIT_PAY':
        title = '未支付的订单'
        break
      case 'WAIT_EAT':
        title = '待就餐的订单'
        break
      case 'WAIT_COMMENT':
        title = '待评价的订单'
        break
      case 'REFUND':
        title = '退款的订单'
        break
    }

    wx.setNavigationBarTitle({
      title
    })
  },
  onShow: function () {
    this.init()
  },
  onPullDownRefresh: function () {
    this.init()
    wx.stopPullDownRefresh()
  },
  onReachBottom: function () {
    if (this.data.page.current + 1 <= this.data.page.total) {
      this.data.page.current++
      this.getOrderList()
    }
  },
  init() {
    this.data.page.current = 1
    this.setData({
      orderList: []
    })
    this.getOrderList()
  },
  getOrderList() {
    wx.showLoading({
      title: '加载中'
    })
    orderService.getOrderListByPage(this.data.page, this.data.orderState)
      .then(res => {
        this.data.orderList.push(...res.records)
        this.setData({
          orderList: this.data.orderList
        })
        this.data.page.total = parseInt(res.total)
      })
      .finally(err => {
        wx.hideLoading()
      })
  }
})