import api from '@/utils/api'

export function getCouponListU () {
  return api.getCouponListU()
}

/**
 * 检查优惠卷可以获取折扣的价格
 * @param order
 */
export function checkCouponDiscountPrice (order, orderItems, coupon) {
  return api.checkCouponDiscountPrice(order, orderItems, coupon.id)
}

export function getCouponDetail (coupon) {
  let detail = ''
  if (coupon.allowCategory || coupon.allowGoods) {
    let newArr = []
    if (coupon.allowCategory) {
      newArr.push(...coupon.allowCategory)
    }
    if (coupon.allowGoods) {
      newArr.push(...coupon.allowGoods)
    }
    detail = `只允许${newArr.join(',')}使用。`
  }
  if (coupon.limitCategory || coupon.limitGoods) {
    let newArr = []
    if (coupon.limitCategory) {
      newArr.push(...coupon.limitCategory)
    }
    if (coupon.limitGoods) {
      newArr.push(...coupon.limitGoods)
    }
    detail = `禁止${newArr.join(',')}使用。`
  }
  detail += '刷卡不支持本优惠卷! 本优惠卷只能使用一次，且退款时不退回!'
  return detail
}

export default {
  getCouponListU,
  checkCouponDiscountPrice,
  getCouponDetail
}
