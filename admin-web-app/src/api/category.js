import request from '@/utils/request'
import Qs from 'qs'

export function getAllCategory() {
  return request({
    url: '/category/getAllCategory',
    method: 'get'
  })
}

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

export function createNewCategory(data) {
  return request({
    url: '/category/createNewCategory',
    method: 'post',
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    data,
    transformRequest: [function(data) {
      data = Qs.stringify(data)
      return data
    }]
  })
}

export function updateCategory(data) {
  return request({
    url: '/category/updateCategory',
    method: 'post',
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    data,
    transformRequest: [function(data) {
      data = Qs.stringify(data)
      return data
    }]
  })
}

export function deleteCategory(id) {
  return request({
    url: '/category/deleteCategory',
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
  getAllCategory,
  getCategoryListByPage,
  createNewCategory,
  updateCategory,
  deleteCategory
}
