import request from '@/utils/request'

export function getAllActivityList() {
  return request({
    url: '/activity/getAllActivityList',
    method: 'get',
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
  })
}

export default {
  getAllActivityList
}
