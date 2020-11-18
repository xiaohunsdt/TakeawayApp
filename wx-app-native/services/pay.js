/**
 * 支付相关服务
 */
import orderService from './order'

export function payOrder (orderId, paymentWay) {
  orderService.selectOrderById(orderId)
    .then(res => {
      if (res.payState === 'PAID') {
        wx.reLaunch({
          // url: '/pages/order/index?state=WAIT_EAT'
          url: `/pages/order/success/index?orderId=${orderId}`
        })
        return
      }

      // 支付逻辑
      switch (paymentWay) {
        case 'WEIXIN_PAY':
          wx.navigateToMiniProgram({
            appId: 'wxdd0eeefb8c1c5ceb',
            path: `/pages/pay/main?orderId=${orderId}`
          })
          break
        case 'ALI_PAY':
          wx.navigateTo({
            url: `/pages/pay/index?orderId=${orderId}`
          })
          break
        case 'TRANSFER':
          wx.navigateTo({
            url: `/pages/pay/index?orderId=${orderId}`
          })
          break
        default:
          wx.redirectTo({
            // url: '/pages/order/index?state=WAIT_EAT'
            url: `/pages/order/success/index?orderId=${orderId}`
          })
      }
    })
}

export default {
  payOrder
}
