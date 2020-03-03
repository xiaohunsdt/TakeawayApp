import request from '@/utils/request'
import Qs from 'qs'

export function getCouponById(id) {
  return request({
    url: '/coupon/getCouponById',
    params: { id }
  })
}

export function getCouponListByPage(page, args) {
  const data = Object.assign({}, page, args)
  return request({
    url: '/coupon/getCouponListByPage',
    method: 'post',
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    data,
    transformRequest: [function(data) {
      data = Qs.stringify(data)
      return data
    }]
  })
}

export function getCouponLogListByPage(page, args) {
  const data = Object.assign({}, page, args)
  return request({
    url: '/coupon/log/getCouponLogListByPage',
    method: 'post',
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    data,
    transformRequest: [function(data) {
      data = Qs.stringify(data)
      return data
    }]
  })
}

export function generateCoupon(data) {
  return request({
    url: '/coupon/generateCoupon',
    method: 'post',
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    data,
    transformRequest: [function(data) {
      data = Qs.stringify(data)
      return data
    }]
  })
}

export function deleteCoupon(id) {
  return request({
    url: '/coupon/deleteCoupon',
    method: 'post',
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    data: {
      id
    },
    transformRequest: [function(data) {
      data = Qs.stringify(data)
      return data
    }]
  })
}

export default {
  getCouponById,
  getCouponListByPage,
  getCouponLogListByPage,
  generateCoupon,
  deleteCoupon
}
