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
          if (res.errno === 0) {
            // 存储用户信息
            mpvue.setStorageSync('token', res.data.token)
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

function setUserInfo () {
  util.getUserInfo()
    .then(res => {
      api.setUserInfo(res).then((response) => {
        if (response.code === 0) {
          mpvue.setStorageSync('userInfo', res)
        }
      })
    })
}

/**
 * 判断用户是否登录
 */
function checkLogin () {
  return new Promise(function (resolve, reject) {
    // if (mpvue.getStorageSync('userInfo') && mpvue.getStorageSync('token')) {
    if (mpvue.getStorageSync('token')) {
      util.checkSession().then(() => {
        resolve(true)
      }).catch(() => {
        // eslint-disable-next-line prefer-promise-reject-errors
        reject(false)
      })
    } else {
      // eslint-disable-next-line prefer-promise-reject-errors
      reject(false)
    }
  })
}

const user = {
  loginByWx,
  checkLogin,
  setUserInfo
}

export default user
