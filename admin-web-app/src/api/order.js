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
  const data = {
    orderId
  }
  return request({
    url: '/order/getOrderDetail',
    method: 'post',
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    data,
    transformRequest: [function(data) {
      data = Qs.stringify(data)
      return data
    }]
  })
}

export default {
  getOrderListByPage,
  getOrderDetail
}
