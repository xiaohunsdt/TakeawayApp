/**
 * 用户相关服务
 */
import api from '@/utils/api'
import util from '@/utils/util'

/**
 * 调用微信登录
 */
function loginByWx () {
  let code = null
  return new Promise(function (resolve, reject) {
    return util.login().then((res) => {
      code = res.code
      return util.getUserInfo()
    }).then((userInfo) => {
      // 把用户信息传给后台，存入数据库，并计算一个token给前台存起来
      api.authLoginByWeixin(code, userInfo).then(res => {
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
  checkLogin
}

export default user
