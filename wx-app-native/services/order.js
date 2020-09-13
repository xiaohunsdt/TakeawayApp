/**
 * 订单相关服务
 */
import api from '../utils/api'

/**
 * 生成一个标准的订单
 */
export function generateOrder(paymentWay, ps, appointmentDate, from) {
  // orderItems,allCount,allPrice,
  // let allCount = 0
  // let allPrice = 0
  // orderItems.forEach(item => {
  //   allCount += item.goodsCount
  //   allPrice += item.goodsCount * item.goodsPrice
  // })
  let order = {
    storeId: getApp().globalData.currentStore.id,
    paymentWay,
    ps,
    appointmentDate,
    from
  }

  return order
}

/**
 * 创建订单
 */
export function createOrder(order, orderItems, coupon, address) {
  return new Promise(function (resolve, reject) {
    if (!address) {
      wx.showToast({
        title: '请设置地址!!',
        image: '/static/images/error.png'
      })
      // eslint-disable-next-line prefer-promise-reject-errors
      reject()
    }
    order.addressId = address.id
    const couponId = coupon ? coupon.id : null
    api.createOrder(order, orderItems, couponId)
      .then(res => resolve(res))
      .catch(err => reject(err))
  })
}

/**
 * 根据订单id查询订单
 */
export function selectOrderById(orderId) {
  return api.selectOrderById(orderId)
}

export function getOrderListByPage(page, orderState) {
  const args = orderState !== 'null' ? Object.assign({}, page, {
    orderState
  }) : page
  return api.getOrderListByPage(args)
}

export function getOrderCountByState(orderState) {
  return api.getOrderCountByState(orderState)
}

export function confirmGetOrder(orderId) {
  return api.confirmGetOrder(orderId)
}

export function deleteOrder(orderId) {
  return api.deleteOrder(orderId)
}

export function createComment(orderId, commentData) {
  commentData.orderId = orderId
  return api.createComment(commentData)
}

export function getDeliveryArriveTime(storeId, orderId) {
  if (!storeId) {
    storeId = getApp().globalData.currentStore.id
  }
  return api.getDeliveryArriveTime(storeId, orderId)
}

export function getCanOrderNow() {
  return api.getCanOrderNow(getApp().globalData.currentStore.id)
}

export default {
  selectOrderById,
  getOrderListByPage,
  getOrderCountByState,
  confirmGetOrder,
  generateOrder,
  createOrder,
  deleteOrder,
  createComment,
  getDeliveryArriveTime,
  getCanOrderNow
}