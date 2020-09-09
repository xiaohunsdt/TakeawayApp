/**
 * 首页相关服务
 */
import api from '../utils/api'

export function getBannersList () {
  return api.getBannersList()
}

export function getNewGoodsList () {
  return api.getSpecificFlagGoodsList('新品')
}

export function getHotGoodsList () {
  return api.getSpecificFlagGoodsList('热门')
}

export function getFormerNotice (fromer) {
  return api.getFormerNotice(fromer)
}

export default {
  getBannersList,
  getNewGoodsList,
  getHotGoodsList,
  getFormerNotice
}
