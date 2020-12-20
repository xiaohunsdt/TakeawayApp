/**
 * 检查微信会话是否过期
 */
function checkSession () {
  return new Promise(function (resolve, reject) {
    wx.checkSession({
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
    wx.login({
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
    wx.getSetting({
      success: function (res) {
        if (res.authSetting['scope.userInfo']) {
          // 已经授权，可以直接调用 getUserInfo 获取头像昵称
          wx.getUserInfo({
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
  const tempVer = wx.getSystemInfoSync().version
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

function showErrorToast (msg) {
  wx.showToast({
    title: msg,
    image: '/static/images/error.png'
  })
}

/**
 * 对象转url参数
 * @param {*} data,对象
 * @param {*} isPrefix,是否自动加上"?"
 */
function urlEncode(data = {}, isPrefix = true, arrayFormat = 'brackets') {
  let prefix = isPrefix ? '?' : ''
  let _result = []
  if (['indices', 'brackets', 'repeat', 'comma'].indexOf(arrayFormat) == -1) arrayFormat = 'brackets';
  for (let key in data) {
      let value = data[key]
      // 去掉为空的参数
      if (['', undefined, null].indexOf(value) >= 0) {
          continue;
      }
      // 如果值为数组，另行处理
      if (value.constructor === Array) {
          // e.g. {ids: [1, 2, 3]}
          switch (arrayFormat) {
              case 'indices':
                  // 结果: ids[0]=1&ids[1]=2&ids[2]=3
                  for (let i = 0; i < value.length; i++) {
                      _result.push(key + '[' + i + ']=' + value[i])
                  }
                  break;
              case 'brackets':
                  // 结果: ids[]=1&ids[]=2&ids[]=3
                  value.forEach(_value => {
                      _result.push(key + '[]=' + _value)
                  })
                  break;
              case 'repeat':
                  // 结果: ids=1&ids=2&ids=3
                  value.forEach(_value => {
                      _result.push(key + '=' + _value)
                  })
                  break;
              case 'comma':
                  // 结果: ids=1,2,3
                  let commaStr = "";
                  value.forEach(_value => {
                      commaStr += (commaStr ? "," : "") + _value;
                  })
                  _result.push(key + '=' + commaStr)
                  break;
              default:
                  value.forEach(_value => {
                      _result.push(key + '[]=' + _value)
                  })
          }
      } else {
          _result.push(key + '=' + value)
      }
  }
  return _result.length ? prefix + _result.join('&') : ''
}


const util = {
  showErrorToast,
  checkSession,
  login,
  getUserInfo,
  checkVersion,
  urlEncode
}

export default util
