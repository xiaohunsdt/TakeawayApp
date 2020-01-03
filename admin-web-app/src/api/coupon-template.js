import request from '@/utils/request'
import Qs from 'qs'

export function getTemplateById(id) {
  return request({
    url: '/coupon/template/getTemplateById',
    params: { id }
  })
}

export function getTemplateListByPage(page, args) {
  const data = Object.assign({}, page, args)
  return request({
    url: '/coupon/template/getTemplateListByPage',
    method: 'post',
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    data,
    transformRequest: [function(data) {
      data = Qs.stringify(data)
      return data
    }]
  })
}

export function getAllTemplateList() {
  return request({
    url: '/coupon/template/getAllTemplateList',
    method: 'get',
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
  })
}

export function createNewTemplate(data) {
  const tempData = Object.assign({}, data)
  tempData.allowCategory = tempData.allowCategory.map(item => item.value).join()
  tempData.limitCategory = tempData.limitCategory.map(item => item.value).join()
  tempData.allowGoods = tempData.allowGoods.map(item => item.value).join()
  tempData.limitGoods = tempData.limitGoods.map(item => item.value).join()
  return request({
    url: '/coupon/template/createNewTemplate',
    method: 'post',
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    data: tempData,
    transformRequest: [function(data) {
      data = Qs.stringify(data)
      return data
    }]
  })
}

export function updateTemplate(data) {
  return request({
    url: '/coupon/template/updateTemplate',
    method: 'post',
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    data,
    transformRequest: [function(data) {
      data = Qs.stringify(data)
      return data
    }]
  })
}

export function deleteTemplate(id) {
  return request({
    url: '/coupon/template/deleteTemplate',
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
  getTemplateById,
  getTemplateListByPage,
  getAllTemplateList,
  createNewTemplate,
  updateTemplate,
  deleteTemplate
}
