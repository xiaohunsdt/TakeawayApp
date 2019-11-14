/**
 * 设置项相关服务
 */
import api from '@/utils/api'

export function getSystemSettings () {
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

export function getStoreSettings () {
  return api.getSettingsByScope('STORE')
}

export function getExpressSettings () {
  return api.getSettingsByScope('EXPRESS')
}

export default {
  getSystemSettings,
  getStoreSettings,
  getExpressSettings
}
