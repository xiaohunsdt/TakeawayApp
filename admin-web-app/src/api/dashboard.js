import request from '@/utils/request'
// import Qs from 'qs'

export function getDashboardData() {
  return request({
    url: '/getDashboardData',
    method: 'get'
  })
}

export default {
  getDashboardData
}
