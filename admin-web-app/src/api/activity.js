import request from '@/utils/request'
import { parseTime } from '@/utils/index'
import Qs from 'qs'

export function getActivityById(id) {
  return request({
    url: '/activity/getActivityById',
    params: { id }
  })
}

export function getActivityListByPage(page, args) {
  const data = Object.assign({}, page, args)
  return request({
    url: '/activity/getActivityListByPage',
    method: 'post',
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    data,
    transformRequest: [function(data) {
      data = Qs.stringify(data)
      return data
    }]
  })
}

export function getAllActivityList() {
  return request({
    url: '/activity/getAllActivityList',
    method: 'get',
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
  })
}

export function createNewActivity(data) {
  return request({
    url: '/activity/createNewActivity',
    method: 'post',
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    data,
    transformRequest: [function(data) {
      data = Qs.stringify(data)
      return data
    }]
  })
}

export function updateActivity(data) {
  return request({
    url: '/activity/updateActivity',
    method: 'post',
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    data,
    transformRequest: [function(data) {
      data = Qs.stringify(data)
      return data
    }]
  })
}

export function changeIsShow(id, isShow) {
  const args = { id, isShow }
  return request({
    url: '/activity/changeIsShow',
    method: 'post',
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    data: args,
    transformRequest: [function(data) {
      data = Qs.stringify(data)
      return data
    }]
  })
}

export function deleteActivity(id) {
  return request({
    url: '/activity/deleteActivity',
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
  getActivityById,
  getActivityListByPage,
  getAllActivityList,
  createNewActivity,
  updateActivity,
  changeIsShow,
  deleteActivity
}
