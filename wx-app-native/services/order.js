/**
 * 订单相关服务
 */
import api from '../utils/api'

// /**
//  * 生成一个标准的订单
//  */
// export function generateOrder (paymentWay, ps, appointmentDate, from) {
//   // orderItems,allCount,allPrice,
//   // let allCount = 0
//   // let allPrice = 0
//   // orderItems.forEach(item => {
//   //   allCount += item.goodsCount
//   //   allPrice += item.goodsCount * item.goodsPrice
//   // })
//   let order = {
//     paymentWay,
//     ps,
//     appointmentDate,
//     from
//   }
//   return order
// }

/**
 * 创建订单
 */

export function createOrder(order, orderDetail, orderItems, coupon) {
  return new Promise(function (resolve, reject) {
    if ((order.orderType === 'NORMAL' || order.orderType === 'APPOINTMENT' || order.orderType === 'EXPRESS')) {
      if(!order.addressId){
        wx.showToast({
          title: '请设置地址!!',
          image: '/static/images/error.png'
        })
        return reject()
      }
      console.log(order.orderType)
      if(order.orderType === 'EXPRESS'){
        if(order.paymentWay === 'CREDIT_CARD' || order.paymentWay === 'CASH'){
          wx.showToast({
            icon: 'none',
            title: '快递订单无法刷卡或现金支付!!',
            duration: 2000
          })
          return reject()
        }
      }
    }
    const couponId = coupon ? coupon.id : null
    api.createOrder(order, orderDetail, orderItems, couponId)
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

export function selectOrderDetailById(orderId) {
  return api.selectOrderDetailById(orderId)
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
    storeId = getApp().globalData.currentStoreId
  }
  return api.getDeliveryArriveTime(storeId, orderId)
}

export function getCanOrderNow(storeId) {
  if (!storeId) {
    storeId = getApp().globalData.currentStoreId
  }
  return api.getCanOrderNow(storeId)
}

export default {
  selectOrderById,
  selectOrderDetailById,
  getOrderListByPage,
  getOrderCountByState,
  confirmGetOrder,
  // generateOrder,
  createOrder,
  deleteOrder,
  createComment,
  getDeliveryArriveTime,
  getCanOrderNow
}