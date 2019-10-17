/**
 * 订单相关服务
 */
import api from '@/utils/api'

/**
 * 创建订单
 */
export function createOrder (cartList, addressId, paymentWay) {
  let orderItemList = []
  let allCount = 0
  let allPrice = 0
  cartList.forEach(item => {
    let orderItem = {}
    orderItem.goodsId = item.goodsId
    orderItem.goodsName = item.goods.name
    orderItem.goodsThumb = item.goods.thumb
    orderItem.goodsPrice = item.goods.price
    orderItem.goodsCount = item.count
    orderItemList.push(orderItem)

    allCount += item.count
    allPrice += item.count * item.goods.price
  })
  let order = {
    addressId,
    goodsCount: allCount,
    discount: 0,
    discountedPrices: 0,
    allPrice,
    realPrice: allPrice,
    paymentWay
  }
  return api.createOrder(order)
}

/**
 * 根据订单id查询订单
 */
export function selectOrderById (orderId) {
  return api.selectOrderById(orderId)
}

export default {
  createOrder,
  selectOrderById
}
