import request from '@/utils/request'
import Qs from 'qs'

export function getAllSetting() {
  return request({
    url: '/address/getAddressListByPage',
    method: 'get'
  })
}

export function getSettingByName(name) {
  return request({
    url: '/address/getAddressListByPage',
    method: 'post',
    data: { name },
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    transformRequest: [function(data) {
      data = Qs.stringify(data)
      return data
    }]
  })
}

export function updateSetting(settingList) {
  return request({
    url: '/address/getAddressListByPage',
    method: 'post',
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    data: settingList,
    transformRequest: [function(data) {
      data = Qs.stringify(data)
      return data
    }]
  })
}

export default {
  getAllSetting,
  getSettingByName,
  updateSetting
}
