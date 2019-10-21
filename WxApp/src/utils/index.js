function formatNumber (n) {
  const str = n.toString()
  return str[1] ? str : `0${str}`
}

export function formatTime (date) {
  const year = date.getFullYear()
  const month = date.getMonth() + 1
  const day = date.getDate()

  const hour = date.getHours()
  const minute = date.getMinutes()
  const second = date.getSeconds()

  const t1 = [year, month, day].map(formatNumber).join('/')
  const t2 = [hour, minute, second].map(formatNumber).join(':')

  return `${t1} ${t2}`
}

let orderStateMap = new Map()
orderStateMap.set('WAITING_RECEIVE', '等待接单')
orderStateMap.set('PRODUCING', '生产中')
orderStateMap.set('DELIVERING', '配送中')
orderStateMap.set('WAIT_COMMENT', '等待评论')
orderStateMap.set('FINISHED', '已完成')
orderStateMap.set('REFUND', '退款')

let payStateMap = new Map()
payStateMap.set('UN_PAY', '未支付')
payStateMap.set('PAID', '已支付')

let paymentWayMap = new Map()
paymentWayMap.set('BALANCE', '账户余额')
paymentWayMap.set('TRANSFER', '通帐转帐')
paymentWayMap.set('WEIXIN_PAY', '微信支付')
paymentWayMap.set('ALI_PAY', '支付宝支付')
paymentWayMap.set('CREDIT_CARD', '刷卡支付')
paymentWayMap.set('CASH', '现金')

export function formatOrderState (orderState) {
  return orderStateMap.get(orderState)
}

export function formatPayState (payState) {
  return payStateMap.get(payState)
}

export function formatPaymentWay (paymentWay) {
  return paymentWayMap.get(paymentWay)
}

export default {
  formatNumber,
  formatTime,
  formatOrderState,
  formatPayState,
  formatPaymentWay
}
