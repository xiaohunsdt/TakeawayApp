/**
 * 支付相关服务
 */

export function payOrder (orderId, payWay) {
  // 支付逻辑
  switch (payWay) {
    case 'WEIXIN_PAY':
      mpvue.navigateToMiniProgram({
        appId: 'wxdd0eeefb8c1c5ceb',
        path: `/pages/pay/main?orderId=${orderId}`
      })
      break
    case 'ALI_PAY':
      mpvue.navigateTo({
        url: `/pages/pay/main?orderId=${orderId}`
      })
      break
    case 'TRANSFER':
      mpvue.navigateTo({
        url: `/pages/pay/main?orderId=${orderId}`
      })
      break
    default:
      mpvue.redirectTo({
        url: '/pages/order/main?state=waitEat'
      })
  }
}

export default {
  payOrder
}
