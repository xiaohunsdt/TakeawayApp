/**
 * 支付相关服务
 */
import orderService from '@/services/order'

export function payOrder (orderId, payWay) {
  orderService.selectOrderById(orderId)
    .then(res => {
      if (res.payState === 'PAID') {
        mpvue.reLaunch({
          url: '/pages/order/main?state=WAIT_EAT'
        })
        return
      }

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
            url: '/pages/order/main?state=WAIT_EAT'
          })
      }
    })
}

export default {
  payOrder
}
