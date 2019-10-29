/**
 * 订单相关服务
 */
import api from '@/utils/api'

/**
 * 创建订单
 */
export function createOrder (orderItems, address, paymentWay, coupon, ps) {
  let allCount = 0
  let allPrice = 0
  orderItems.forEach(item => {
    allCount += item.goodsCount
    allPrice += item.goodsCount * item.goodsPrice
  })
  let order = {
    addressId: address.id,
    goodsCount: allCount,
    discount: 0,
    discountedPrices: 0,
    allPrice,
    realPrice: allPrice,
    paymentWay,
    ps
  }
  return api.createOrder(order, orderItems)
}

/**
 * 根据订单id查询订单
 */
export function selectOrderById (orderId) {
  return api.selectOrderById(orderId)
}

export function getOrderListByPage (page, orderState) {
  return api.getOrderListByPage(orderState)
}

export function getOrderCountByState (page, orderState) {
  return api.getOrderCountByState(orderState)
}

export default {
  selectOrderById,
  getOrderListByPage,
  getOrderCountByState,
  createOrder
}
