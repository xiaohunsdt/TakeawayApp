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

export default {
  getCouponListU
}
