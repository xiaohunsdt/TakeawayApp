/**
 * 订单相关服务
 */
import api from '../utils/api'
import indexUtil from '../utils/index'

export function getStoreById(storeId) {
  return api.getStoreById(storeId)
}

export function getAvailableStoreList(x, y) {
  return api.getAvailableStoreList(x, y)
}

export function getAllStoreList() {
  return api.getAllStoreList()
}

export function getServiceState() {
  return api.getServiceState(getApp().globalData.currentStoreId)
}

export function getExpressServiceState(addressId, allPrice) {
  return api.getExpressServiceState(getApp().globalData.currentStoreId, addressId, allPrice)
}

export function getDeliveryPrice() {
  return api.getDeliveryPrice(getApp().globalData.currentStoreId)
}

export function getAppointmentTimes() {
  return api.getAppointmentTimes(getApp().globalData.currentStoreId)
}

export function formatAppointmentTime(deliveryType, appointment) {
  let appointmentDate = null
  if (appointment === null || appointment.length !== 3) {
    return appointmentDate
  }
  if (deliveryType === '预约点餐') {
    appointmentDate = new Date()
    switch (appointment[0]) {
      case '今天':
        break
      case '明天':
        appointmentDate.setTime(appointmentDate.getTime() + 24 * 60 * 60 * 1000)
        break
      case '后天':
        appointmentDate.setTime(appointmentDate.getTime() + 2 * 24 * 60 * 60 * 1000)
        break
      default:
        if (appointment[0].includes('-')) {
          const dateStr = appointment[0].split('-')
          appointmentDate = new Date(appointmentDate.getFullYear(), dateStr[0], dateStr[1])
        }
        break
    }
    appointmentDate.setHours(appointment[1], appointment[2], 0)
  }
  return appointmentDate ? indexUtil.formatDateTime(appointmentDate) : null
}

export default {
  getStoreById,
  getAvailableStoreList,
  getAllStoreList,
  getServiceState,
  getExpressServiceState,
  getDeliveryPrice,
  getAppointmentTimes,
  formatAppointmentTime
}