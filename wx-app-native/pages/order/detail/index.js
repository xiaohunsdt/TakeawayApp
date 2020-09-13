import orderService from '../../../services/order'
import couponService from '../../../services/coupon'
import payService from '../../../services/pay'
import indexUtil from '../../../utils/index'

Page({

  /**
   * 页面的初始数据
   */
  data: {
    orderId: '',
    order: null,
    payStateStr: "",
    paymentWayStr: "",
    couponName: null,
    deliveryArriveTime: {
      date: '',
      time: ''
    }
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      orderId: options.orderId
    })
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
        order: res,
        payStateStr: indexUtil.formatPayState(res.payState),
        paymentWayStr: indexUtil.formatPaymentWay(res.paymentWay)
      })
      if (res.discountedPrices) {
        couponService.getCouponLogByOrderId(this.data.orderId)
          .then(res => {
            this.setData({
              couponName: res[0].couponName
            })
          })
      }
      if ((this.data.order.orderState === 'PRODUCING' || this.data.order.orderState === 'DELIVERING') && this.data.order.payState !== 'UN_PAY') {
        // 获取预计送达时间
        orderService.getDeliveryArriveTime(this.data.order.storeId, this.data.orderId)
          .then(res => {
            this.setData({
              deliveryArriveTime: res
            })
          })
      }
    })
  },
  payNow(){
    payService.payOrder(this.data.orderId, this.data.order.paymentWay)
  },
  comment(){
    wx.navigateTo({
      url: `/pages/order/comment/index?orderId=${this.data.orderId}`
    })
  }
})