import request from '@/utils/request'
import Qs from 'qs'

export function getUserListByPage(page, args) {
  const data = Object.assign({}, page, args)
  return request({
    url: '/user/getUserListByPage',
    method: 'post',
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    data,
    transformRequest: [function(data) {
      data = Qs.stringify(data)
      return data
    }]
  })
}

export function updateUser(data) {
  return request({
    url: '/user/updateUser',
    method: 'post',
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    data,
    transformRequest: [function(data) {
      data = Qs.stringify(data)
      return data
    }]
  })
}

export function deleteUser(id) {
  return request({
    url: '/user/deleteUser',
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
  getUserListByPage,
  updateUser,
  deleteUser
}
