/**
 * 商品相关服务
 */
import api from '@/utils/api'

/**
 * 调用微信登录
 */
export function getGoodsListByCategoryId (categoryId) {
  return api.getGoodsListByCategoryId(categoryId)
}

export default {
  getGoodsListByCategoryId
}
