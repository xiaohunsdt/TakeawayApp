/**
 * 用户相关服务
 */
import api from '@/utils/api'
import util from '@/utils/util'

/**
 * 调用微信登录
 */
function loginByWx () {
  return new Promise(function (resolve, reject) {
    return util.login().then((res) => {
      const code = res.code
      api.authLoginByWeixin(code)
        .then(res => {
          if (res.hasOwnProperty('token')) {
            // 存储用户信息
            mpvue.setStorageSync('token', res)
            resolve(res)
          } else {
            reject(res)
          }
        })
        .catch((err) => {
          reject(err)
        })
    })
  })
}

export default {
  loginByWx
}
