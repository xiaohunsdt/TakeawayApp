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

function showErrorToast (msg) {
  mpvue.showToast({
    title: msg,
    image: '/static/images/icon_error.png'
  })
}

const util = {
  showErrorToast,
  checkSession,
  login
}

export default util
