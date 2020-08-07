/**
 * 支付相关服务
 */
import orderService from './order'

export function payOrder (orderId, payWay) {
  orderService.selectOrderById(orderId)
    .then(res => {
      if (res.payState === 'PAID') {
        wx.reLaunch({
          url: '/pages/order/main?state=WAIT_EAT'
        })
        return
      }

      // 支付逻辑
      switch (payWay) {
        case 'WEIXIN_PAY':
          wx.navigateToMiniProgram({
            appId: 'wxdd0eeefb8c1c5ceb',
            path: `/pages/pay/main?orderId=${orderId}`
          })
          break
        case 'ALI_PAY':
          wx.navigateTo({
            url: `/pages/pay/main?orderId=${orderId}`
          })
          break
        case 'TRANSFER':
          wx.navigateTo({
            url: `/pages/pay/main?orderId=${orderId}`
          })
          break
        default:
          wx.redirectTo({
            url: '/pages/order/main?state=WAIT_EAT'
          })
      }
    })
}

export default {
  payOrder
}
