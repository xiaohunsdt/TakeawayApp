/**
 * 设置项相关服务
 */
import api from '../utils/api'

export function getSystemSettings() {
  return new Promise((resolve, reject) => {
    api.getSettingsByScope('SYSTEM')
      .then(res => {
        let data = {}
        res.forEach(item => {
          if (item.key === 'goods_page_tags') {
            data.goods_page_tags = item.value.split(',')
          } else if (item.key === 'service_running') {
            data.service_running = item.value === 'true'
          } else {
            data[item.key] = item.value
          }
        })
        resolve(data)
      })
      .catch(res => {
        reject(res)
      })
  })
}

export function getStoreSettings() {
  return new Promise((reslove, reject) => {
    api.getSettingsByScope('STORE')
      .then(res => {
        let result = {}
        res.forEach(item => {
          result[item.key] = item.value
        })
        reslove(result)
      })
      .catch(res => {
        reject(res)
      })
  })
}

export function getExpressSettings() {
  return new Promise((reslove, reject) => {
    api.getSettingsByScope('EXPRESS')
      .then(res => {
        let result = {}
        res.forEach(item => {
          result[item.key] = item.value
        })
        reslove(result)
      })
      .catch(res => {
        reject(res)
      })
  })
}

export function getPaymentSettings() {
  return new Promise((reslove, reject) => {
    api.getSettingsByScope('PAYMENT')
      .then(res => {
        let result = {}
        res.forEach(item => {
          result[item.key] = item.value
        })
        reslove(result)
      })
      .catch(res => {
        reject(res)
      })
  })
}

export function getGoodsPageSettings() {
  return api.getGoodsPageSettings()
}

export default {
  getSystemSettings,
  getStoreSettings,
  getExpressSettings,
  getPaymentSettings,
  getGoodsPageSettings
}