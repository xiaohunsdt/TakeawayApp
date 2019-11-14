import request from '@/utils/request'
import Qs from 'qs'

export function getAllSetting() {
  return request({
    url: '/setting/getAllSetting',
    method: 'get'
  })
}

export function getSettingByName(name, scope) {
  return request({
    url: '/setting/getSettingByName',
    method: 'post',
    data: { name, scope },
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    transformRequest: [function(data) {
      data = Qs.stringify(data)
      return data
    }]
  })
}

export function getSettingsByScope(scope) {
  return request({
    url: '/setting/getSettingsByScope',
    method: 'post',
    data: { scope },
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    transformRequest: [function(data) {
      data = Qs.stringify(data)
      return data
    }]
  })
}

export function getSettingsByName(name, scope) {
  return request({
    url: '/setting/getSettingByName',
    method: 'post',
    data: { name, scope },
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    transformRequest: [function(data) {
      data = Qs.stringify(data)
      return data
    }]
  })
}

export function updateSetting(settings, scope) {
  settings.scope = scope
  const data = settings
  return request({
    url: '/setting/updateSetting',
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
  getAllSetting,
  getSettingByName,
  getSettingsByScope,
  updateSetting
}
