import orderService from '../../../services/order'

Page({

  /**
   * 页面的初始数据
   */
  data: {
    submitLoad: false,
    orderId: null,
    order: {},
    rateData: {
      delicious: 0,
      express: 0,
      service: 0,
      comment: ''
    }
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
  init() {
    orderService.selectOrderById(this.data.orderId).then(res => {
      this.setData({
        order: res
      })
    })
  },
  onDeliciousChange(event) {
    this.setData({
      "rateData.delicious":  event.detail
    })
  },
  onExpressChange(event) {
    this.setData({
      "rateData.express":  event.detail
    })
  },
  onServiceChange(event) {
    this.setData({
      "rateData.service":  event.detail
    })
  },
  onCommentChange(event) {
    this.setData({
      "rateData.comment":  event.detail.value
    })
  },
  onSubmit() {
    this.setData({
      submitLoad: true
    })
    orderService.createComment(this.data.orderId, this.data.rateData)
      .then(res => {
        this.setData({
          submitLoad: false
        })
        wx.navigateBack()
      })
      .catch(res => {
        this.setData({
          submitLoad: false
        })
      })
  }
})