var moment = require('moment')

function formatNumber (n) {
  const str = n.toString()
  return str[1] ? str : `0${str}`
}

export function formatDateTime (date) {
  const year = date.getFullYear()
  const month = date.getMonth() + 1
  const day = date.getDate()

  const hour = date.getHours()
  const minute = date.getMinutes()
  const second = date.getSeconds()

  const t1 = [year, month, day].map(formatNumber).join('-')
  const t2 = [hour, minute, second].map(formatNumber).join(':')

  return `${t1} ${t2}`
}

export function formatDate (date) {
  const year = date.getFullYear()
  const month = date.getMonth() + 1
  const day = date.getDate()

  const t1 = [year, month, day].map(formatNumber).join('-')
  return t1
}

export function isObjectValueEqual(a, b) {
  var aProps = Object.getOwnPropertyNames(a);
  var bProps = Object.getOwnPropertyNames(b);
  if (aProps.length != bProps.length) {
      return false;
  }
  for (var i = 0; i < aProps.length; i++) {
      var propName = aProps[i];
      if (a[propName] !== b[propName]) {
          return false;
      }
  }
  return true;
}

let orderStateMap = new Map()
orderStateMap.set('WAITING_RECEIVE', '等待接单')
orderStateMap.set('PRODUCING', '制作中')
orderStateMap.set('DELIVERING', '配送中')
orderStateMap.set('WAIT_COMMENT', '等待评论')
orderStateMap.set('FINISHED', '已完成')
orderStateMap.set('REFUND', '退款')

let payStateMap = new Map()
payStateMap.set('UN_PAY', '未支付')
payStateMap.set('PAID', '已支付')
payStateMap.set('PAY_LATER', '后付')

let paymentWayMap = new Map()
paymentWayMap.set('BALANCE', '账户余额')
paymentWayMap.set('TRANSFER', '通帐转帐')
paymentWayMap.set('WEIXIN_PAY', '微信支付')
paymentWayMap.set('ALI_PAY', '支付宝支付')
paymentWayMap.set('CREDIT_CARD', '刷卡支付')
paymentWayMap.set('CASH', '现金')

const couponTypeMap = new Map()
couponTypeMap.set('MONEY', '现金卷')
couponTypeMap.set('DISCOUNT', '折扣卷')

const couponStateMap = new Map()
couponStateMap.set('UN_USE', '未使用')
couponStateMap.set('USED', '已使用')
couponStateMap.set('EXPIRED', '已过期')

export function formatOrderState (orderState) {
  return orderStateMap.get(orderState)
}

export function formatPayState (payState) {
  return payStateMap.get(payState)
}

export function formatPaymentWay (paymentWay) {
  return paymentWayMap.get(paymentWay)
}

export function orderRemainingTime (createDate) {
  let m = moment(createDate)
  m.add(30, 'm')
  return m.diff(moment())
}

export function formatCouponType (couponType) {
  return couponTypeMap.get(couponType)
}

export function formatCouponState (couponType) {
  return couponStateMap.get(couponType)
}

export default {
  formatNumber,
  formatDate,
  formatDateTime,
  isObjectValueEqual,
  formatOrderState,
  formatPayState,
  formatPaymentWay,
  orderRemainingTime,
  formatCouponType,
  formatCouponState
}
