/**
 * 用户相关服务
 */
import api from '../utils/api'
import util from '../utils/util'

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
            wx.setStorageSync('token', res)
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
  return new Promise(function (resolve, reject) {
    util.getUserInfo()
      .then(res => {
        const userInfo = res.userInfo
        api.setUserInfo(userInfo)
          .then((response) => {
            if (response.code === 0) {
              wx.setStorageSync('userInfo', userInfo)
              resolve(response)
            } else {
              reject(response)
            }
          })
          .catch((err) => {
            reject(err)
          })
      })
      .catch((err) => {
        reject(err)
      })
  })
}

/**
 * 判断用户是否登录
 */
function checkLogin () {
  return new Promise(function (resolve, reject) {
    // if (wx.getStorageSync('userInfo') && wx.getStorageSync('token')) {
    if (wx.getStorageSync('token')) {
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

function getSignInDays (storeId) {
  return api.getSignInDays(storeId)
}

function getSignInedCount (storeId) {
  return api.getSignInedCount(storeId)
}

export default {
  loginByWx,
  checkLogin,
  setUserInfo,
  getSignInDays,
  getSignInedCount
}
