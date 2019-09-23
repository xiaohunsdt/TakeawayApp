import request from '@/utils/request'
// import Qs from 'qs'

export function getRebate() {
  return request({
    url: '/marketer/getRebate',
    method: 'get'
  })
}

export function getRecentCalculateHistory() {
  return request({
    url: '/marketer/getRecentCalculateHistory',
    method: 'get'
  })
}

export default {
  getRebate,
  getRecentCalculateHistory
}
