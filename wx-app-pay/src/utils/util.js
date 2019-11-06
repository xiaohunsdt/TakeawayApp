/**
 * 检查微信会话是否过期
 */
function checkSession () {
  return new Promise(function (resolve, reject) {
    mpvue.checkSession({
      success: function () {
        resolve(true)
      },
      fail: function () {
        // eslint-disable-next-line prefer-promise-reject-errors
        reject(false)
      }
    })
  })
}

/**
 * 调用微信登录
 */
function login () {
  return new Promise(function (resolve, reject) {
    mpvue.login({
      success: function (res) {
        if (res.code) {
          // 登录远程服务器
          console.log(res)
          resolve(res)
        } else {
          reject(res)
        }
      },
      fail: function (err) {
        reject(err)
      }
    })
  })
}

/**
 * 调用微信获取用户信息接口，需要button授权
 */
function getUserInfo () {
  return new Promise(function (resolve, reject) {
    mpvue.getSetting({
      success: function (res) {
        if (res.authSetting['scope.userInfo']) {
          // 已经授权，可以直接调用 getUserInfo 获取头像昵称
          mpvue.getUserInfo({
            withCredentials: true,
            success: function (res) {
              // console.log('获取用户信息成功', res);
              resolve(res)
            },
            fail: function (err) {
              console.warn('获取用户信息失败', err)
              reject(err)
            }
          })
        } else {
          // 没有授权
          console.warn('获取用户信息失败，未授权')
        }
      }
    })
  })
}

export function getPages () {
  /* 获取当前路由栈数组 */
  // eslint-disable-next-line no-undef
  const pages = getCurrentPages()
  return pages
}

function showErrorToast (msg) {
  mpvue.showToast({
    title: msg,
    image: '/static/images/icon_error.png'
  })
}

const util = {
  showErrorToast,
  checkSession,
  login,
  getUserInfo,
  getPages
}

export default util
