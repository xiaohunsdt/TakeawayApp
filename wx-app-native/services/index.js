/**
 * 首页相关服务
 */
import api from '../utils/api'
import indexUtil from '../utils/index'

export function getBannersList () {
  return api.getBannersList()
}

export function getNewGoodsList () {
  return api.getSpecificFlagProduceList('新品')
}

export function getHotGoodsList () {
  return api.getSpecificFlagProduceList('热门')
}

export function getServiceState () {
  return api.getServiceState()
}

export function getExpressServiceState (addressId, allPrice) {
  return api.getExpressServiceState(addressId, allPrice)
}

export function getDeliveryPrice () {
  return api.getDeliveryPrice()
}

export function getAppointmentTimes () {
  return api.getAppointmentTimes()
}

export function formatAppointmentTime (orderType, appointment) {
  let appointmentDate = null
  if (appointment === null || appointment.length !== 3) {
    return appointmentDate
  }
  if (orderType === 'APPOINTMENT') {
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

export function getFormerNotice (fromer) {
  return api.getFormerNotice(fromer)
}

export default {
  getBannersList,
  getNewGoodsList,
  getHotGoodsList,
  getServiceState,
  getExpressServiceState,
  getDeliveryPrice,
  getAppointmentTimes,
  formatAppointmentTime,
  getFormerNotice
}
