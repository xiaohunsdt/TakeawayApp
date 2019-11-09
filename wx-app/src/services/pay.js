/**
 * 支付相关服务
 */

function payOrder (orderId) {
  mpvue.navigateToMiniProgram({
    appId: 'wxdd0eeefb8c1c5ceb',
    path: `/pages/pay/main?orderId=${orderId}`
  })
}

export default {
  payOrder
}
