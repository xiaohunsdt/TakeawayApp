import request from '@/utils/request'
import Qs from 'qs'

export function getOrderListByPage(page, args) {
  const data = Object.assign({}, page, args)
  return request({
    url: '/order/getOrderListByPage',
    method: 'post',
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    data,
    transformRequest: [function(data) {
      data = Qs.stringify(data)
      return data
    }]
  })
}

export function getOrderDetail(orderId) {
  return request({
    url: '/order/getOrderDetail',
    method: 'post',
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    data: { orderId },
    transformRequest: [function(data) {
      data = Qs.stringify(data)
      return data
    }]
  })
}

export function printOrder(order) {
  const orderId = order.id
  return request({
    baseURL: 'http://localhost:11996',
    url: '/print',
    method: 'post',
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    data: { orderId },
    transformRequest: [function(data) {
      data = Qs.stringify(data)
      return data
    }]
  })
}

export function confirmPay(orderId) {
  return request({
    url: '/order/confirmPay',
    method: 'post',
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    data: { orderId },
    transformRequest: [function(data) {
      data = Qs.stringify(data)
      return data
    }]
  })
}

export function receiveOrder(orderId) {
  return request({
    url: '/order/receiveOrder',
    method: 'post',
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    data: { orderId },
    transformRequest: [function(data) {
      data = Qs.stringify(data)
      return data
    }]
  })
}

export function deliveryOrder(orderId) {
  return request({
    url: '/order/deliveryOrder',
    method: 'post',
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    data: { orderId },
    transformRequest: [function(data) {
      data = Qs.stringify(data)
      return data
    }]
  })
}

export function finishOrder(orderId) {
  return request({
    url: '/order/finishOrder',
    method: 'post',
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    data: { orderId },
    transformRequest: [function(data) {
      data = Qs.stringify(data)
      return data
    }]
  })
}

export function refundOrder(orderId) {
  return request({
    url: '/order/refundOrder',
    method: 'post',
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    data: { orderId },
    transformRequest: [function(data) {
      data = Qs.stringify(data)
      return data
    }]
  })
}

export function deleteOrder(orderId) {
  return request({
    url: '/order/deleteOrder',
    method: 'post',
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    data: { orderId },
    transformRequest: [function(data) {
      data = Qs.stringify(data)
      return data
    }]
  })
}

export default {
  getOrderListByPage,
  getOrderDetail,
  printOrder,
  confirmPay,
  receiveOrder,
  deliveryOrder,
  finishOrder,
  refundOrder,
  deleteOrder
}
