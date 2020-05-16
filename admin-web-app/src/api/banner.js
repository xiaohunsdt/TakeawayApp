import request from '@/utils/request'
import Qs from 'qs'

export function getBannerById(id) {
  return request({
    url: '/banner/getBannerById',
    params: { id }
  })
}

export function getBannerListByPage(page, args) {
  const data = Object.assign({}, page, args)
  return request({
    url: '/banner/getBannerListByPage',
    method: 'post',
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    data,
    transformRequest: [function(data) {
      data = Qs.stringify(data)
      return data
    }]
  })
}

export function createNewBanner(data) {
  return request({
    url: '/banner/createNewBanner',
    method: 'post',
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    data,
    transformRequest: [function(data) {
      data = Qs.stringify(data)
      return data
    }]
  })
}

export function updateBanner(data) {
  return request({
    url: '/banner/updateBanner',
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
    url: '/banner/changeIsShow',
    method: 'post',
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    data: args,
    transformRequest: [function(data) {
      data = Qs.stringify(data)
      return data
    }]
  })
}

export function deleteBanner(id) {
  return request({
    url: '/banner/deleteBanner',
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
  getBannerById,
  getBannerListByPage,
  createNewBanner,
  updateBanner,
  changeIsShow,
  deleteBanner
}
