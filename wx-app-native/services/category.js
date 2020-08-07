/**
 * 分类相关服务
 */
import api from '../utils/api'

/**
 * 获取所有分类
 */
export function getAllCategory () {
  return api.getAllCategory()
}

export default {
  getAllCategory
}
