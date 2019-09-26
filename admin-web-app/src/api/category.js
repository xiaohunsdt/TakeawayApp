import request from '@/utils/request'
import Qs from 'qs'

export function getCategoryListByPage(page, args) {
  const data = Object.assign({}, page, args)
  return request({
    url: '/category/getCategoryListByPage',
    method: 'post',
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    data,
    transformRequest: [function(data) {
      data = Qs.stringify(data)
      return data
    }]
  })
}

export function getAllCategory() {
  return request({
    url: '/category/getAllCategory',
    method: 'get'
  })
}

export default {
  getAllCategory,
  getCategoryListByPage
}
