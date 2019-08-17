/**
 * 用户相关服务
 */
import wx from 'wx'
import api from '@/utils/api'
import util from '@/utils/util'

/**
 * 调用微信登录
 */
function loginByWeixin () {
  let code = null
  return new Promise(function (resolve, reject) {
    return util.login().then((res) => {
      code = res.code
      return util.getUserInfo()
    }).then((userInfo) => {
      // 把用户信息传给后台，存入数据库，并计算一个token给前台存起来
      util.request(api.AuthLoginByWeixin, {
        code: code,
        userInfo: userInfo
      }, 'POST').then(res => {
        if (res.errno === 0) {
          // 存储用户信息
          mpvue.setStorageSync('userInfo', res.data.userInfo)
          mpvue.setStorageSync('token', res.data.token)

          resolve(res)
        } else {
          reject(res)
        }
      }).catch((err) => {
        reject(err)
      })
    }).catch((err) => {
      reject(err)
    })
  })
}

/**
 * 判断用户是否登录
 */
function checkLogin () {
  return new Promise(function (resolve, reject) {
    if (mpvue.getStorageSync('userInfo') && mpvue.getStorageSync('token')) {
      util.checkSession().then(() => {
        resolve(true)
      }).catch(() => {
        reject(false)
      })
    } else {
      reject(false)
    }
  })
}

const user = {
  loginByWeixin,
  checkLogin
}

export default user
