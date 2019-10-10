import request from '@/utils/request'
import Qs from 'qs'

export function getAddressListByPage(page, args) {
  const data = Object.assign({}, page, args)
  return request({
    url: '/address/getAddressListByPage',
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
  getAddressListByPage
}