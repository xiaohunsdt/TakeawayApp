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

function checkVersion (version) {
  const tempVer = mpvue.getSystemInfoSync().version
  const paltsfrom = wx.getSystemInfoSync().platform
  console.log('微信版本号')
  console.log(tempVer)
  console.log(paltsfrom)
  return compareVersion(tempVer, version)
}

function compareVersion (v1, v2) {
  v1 = v1.split('.')
  v2 = v2.split('.')
  var len = Math.max(v1.length, v2.length)

  while (v1.length < len) {
    v1.push('0')
  }
  while (v2.length < len) {
    v2.push('0')
  }

  for (var i = 0; i < len; i++) {
    var num1 = parseInt(v1[i])
    var num2 = parseInt(v2[i])

    if (num1 > num2) {
      return 1
    } else if (num1 < num2) {
      return -1
    }
  }
  return 0
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
  getPages,
  checkVersion
}

export default util
