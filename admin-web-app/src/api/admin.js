import request from '@/utils/request'
import Qs from 'qs'

export function login(data) {
  return request({
    url: '/auth',
    method: 'post',
    data,
    transformRequest: [function(data) {
      data = Qs.stringify(data)
      return data
    }]
  })
}

export function getInfo() {
  return request({
    url: '/getAdminInfo',
    method: 'get'
  })
}

export function refreshToken() {
  return request({
    url: '/auth/refresh',
    method: 'post'
  })
}

export function logout() {
  return request({
    url: '/logout',
    method: 'post'
  })
}
