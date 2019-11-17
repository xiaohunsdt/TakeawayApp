/**
 * 首页相关服务
 */
import api from '@/utils/api'

export function getNewGoodsList () {
  return api.getSpecificFlagGoodsList('新品')
}

export function getHotGoodsList () {
  return api.getSpecificFlagGoodsList('热门')
}

export function getServiceState () {
  return api.getServiceState()
}

export function getExpressServiceState (addressId) {
  return api.getExpressServiceState(addressId)
}

export default {
  getNewGoodsList,
  getHotGoodsList,
  getServiceState,
  getExpressServiceState
}
