import request from '@/utils/request'
import Qs from 'qs'

export function getAllGoods() {
  return request({
    url: '/goods/getAllGoods',
    method: 'get'
  })
}

export function getGoodsListByPage(page, args) {
  const data = Object.assign({}, page, args)
  return request({
    url: '/goods/getGoodsListByPage',
    method: 'post',
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    data,
    transformRequest: [function(data) {
      data = Qs.stringify(data)
      return data
    }]
  })
}

export function createNewGoods(data) {
  return request({
    url: '/goods/createNewGoods',
    method: 'post',
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    data,
    transformRequest: [function(data) {
      data = Qs.stringify(data)
      return data
    }]
  })
}

export function updateGoods(data) {
  return request({
    url: '/goods/updateGoods',
    method: 'post',
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    data,
    transformRequest: [function(data) {
      data = Qs.stringify(data)
      return data
    }]
  })
}

export function delteGoods(id) {
  return request({
    url: '/goods/delteGoods',
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
  getAllGoods,
  getGoodsListByPage,
  createNewGoods,
  updateGoods,
  delteGoods
}
