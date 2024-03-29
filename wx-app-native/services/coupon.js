import api from '../utils/api'

export function getCouponListU () {
  return api.getCouponListU()
}

export function getCouponLogByOrderId (orderId) {
  return api.getCouponLogByOrderId(orderId)
}

/**
 * 检查优惠卷可以获取折扣的价格
 * @param order
 */
export function checkCouponDiscountPrice (order, orderItems, couponId) {
  return api.checkCouponDiscountPrice(order, orderItems, couponId)
}

export function getCouponDetail (coupon) {
  let detail = '刷卡不支持本优惠卷! '
  if (coupon.allowCategory || coupon.allowGoods) {
    let newArr = []
    if (coupon.allowCategory) {
      newArr.push(...coupon.allowCategory)
    }
    if (coupon.allowGoods) {
      newArr.push(...coupon.allowGoods)
    }
    detail += `只允许${newArr.join(',')}使用。`
  }
  if (coupon.limitCategory || coupon.limitGoods) {
    let newArr = []
    if (coupon.limitCategory) {
      newArr.push(...coupon.limitCategory)
    }
    if (coupon.limitGoods) {
      newArr.push(...coupon.limitGoods)
    }
    detail += `禁止${newArr.join(',')}使用。`
  }
  if (coupon.minimumMoney > 0) {
    detail += `最低消费金额${coupon.minimumMoney}。`
  }
  detail += '如果订单中存在不打折的商品项，系统自动踢出优惠! 本优惠卷只能使用一次，且退款时不退回!'
  return detail
}

export function exchangeCoupon (couponId) {
  return api.exchangeCoupon(couponId)
}

export default {
  getCouponListU,
  getCouponLogByOrderId,
  checkCouponDiscountPrice,
  getCouponDetail,
  exchangeCoupon
}
