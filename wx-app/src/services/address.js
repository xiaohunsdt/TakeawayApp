import api from '@/utils/api'

/**
 * 获取用户的地址列表
 */
export function getAddressById (addressId) {
  return api.getAddressById(addressId)
}

/**
 * 获取用户的地址列表
 */
export function getMyAddressList () {
  return api.getMyAddressList()
}

/**
 * 获取用户的默认地址
 */
export function getDefaultAddress () {
  return api.getDefaultAddress()
}

/**
 * 新建一个地址
 */
export function createNewAddress (address) {
  return api.createNewAddress(address)
}

/**
 * 更新一个地址
 */
export function updateAddress (address) {
  return api.updateAddress(address)
}

export function setDefault (addressId) {
  return api.updateAddress({id: addressId, isDefault: true})
}

export function deleteAddress (addressId) {
  return api.deleteAddress(addressId)
}

export default {
  getAddressById,
  getMyAddressList,
  getDefaultAddress,
  createNewAddress,
  updateAddress,
  setDefault,
  deleteAddress
}
