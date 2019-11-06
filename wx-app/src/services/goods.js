/**
 * 商品相关服务
 */
import api from '@/utils/api'

/**
 * 根据分类id获取商品
 */
export function getGoodsListByCategoryId (categoryId) {
  return api.getGoodsListByCategoryId(categoryId)
}

export default {
  getGoodsListByCategoryId
}
