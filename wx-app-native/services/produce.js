/**
 * 商品相关服务
 */
import api from '../utils/api'


export function getDetailById (produceId) {
  return api.getProduceDetailById(produceId)
}

/**
 * 根据分类id获取商品
 */
export function getListByCategoryId (categoryId) {
  return api.getProduceListByCategoryId(categoryId)
}

/**
 * 获取所有商品
 */
export function getAllList () {
  return api.getAllProduceList(getApp().globalData.currentStoreId)
}

export default {
  getDetailById,
  getListByCategoryId,
  getAllList
}
