import indexUtil from '../../../../utils/index'
import orderOperation from '../../mixins/order-operation'

Component({
  properties: {
    order: Object,
    showOperation: Boolean
  },
  behaviors: [orderOperation],
  data: {
    payStateStr: null,
    orderStateStr: null,
    remainingTime: null,
  },
  lifetimes: {
    attached() {
      this.setData({
        payStateStr: indexUtil.formatPayState(this.data.order.payState),
        orderStateStr: indexUtil.formatOrderState(this.data.order.orderState),
        remainingTime: this.data.order.payState === 'UN_PAY' ? indexUtil.orderRemainingTime(this.data.order.createDate) : 0,
      })
    }
  },
  methods: {
    openOrderDetail() {
      wx.navigateTo({
        url: `/pages/order/detail/index?orderId=${this.data.order.id}`
      })
    }
  }
})