/**
 * 设置项相关服务
 */
import api from '../utils/api'

export function getSystemSettings(storeId) {
  return new Promise((resolve, reject) => {
    if (!storeId) {
      storeId = getApp().globalData.currentStoreId
    }
    api.getSettingsByScope(storeId, 'SYSTEM')
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

export function getStoreSettings(storeId) {
  return new Promise((reslove, reject) => {
    if (!storeId) {
      storeId = getApp().globalData.currentStoreId
    }
    api.getSettingsByScope(storeId, 'STORE')
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

export function getExpressSettings(storeId) {
  return new Promise((reslove, reject) => {
    if (!storeId) {
      storeId = getApp().globalData.currentStoreId
    }
    api.getSettingsByScope(storeId, 'EXPRESS')
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

export function getPaymentSettings(storeId) {
  return new Promise((reslove, reject) => {
    if (!storeId) {
      storeId = getApp().globalData.currentStoreId
    }
    api.getSettingsByScope(storeId, 'PAYMENT')
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

export function getGoodsPageSettings(storeId) {
  if (!storeId) {
    storeId = getApp().globalData.currentStoreId
  }
  return api.getGoodsPageSettings(storeId)
}

export function getSettingByName(storeId, key, scope) {
  if (!storeId) {
    storeId = getApp().globalData.currentStoreId
  }
  return api.getSettingByName(storeId, key, scope)
}

export default {
  getSystemSettings,
  getStoreSettings,
  getExpressSettings,
  getPaymentSettings,
  getGoodsPageSettings,
  getSettingByName
}