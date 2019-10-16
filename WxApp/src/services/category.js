/**
 * 分类相关服务
 */
import api from '@/utils/api'

/**
 * 调用微信登录
 */
export function getAllCategory () {
  return api.getAllCategory()
}

export default {
  getAllCategory
}
