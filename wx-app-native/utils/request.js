import md5 from 'js-md5'

let Base64 = require('js-base64').Base64

var Fly = require('flyio/index')
const request = new Fly()

// 设置请求基地址
// request.config.baseURL = 'http://localhost:8081/api/user'
request.config.baseURL = 'https://cxy.novaborn.net/api/user'

request.interceptors.request.use((request) => {
  // 给所有请求添加自定义header，带上token信息让服务器验证用户登陆
  request.headers['Authorization'] = 'Bearer ' + wx.getStorageSync('token').token
  // console.log(request)
  if (request.headers['Content-Type'] === 'application/json') {
    // 数据加密
    const randomKey = wx.getStorageSync('token').randomKey
    const jsonStr = Base64.encode(JSON.stringify(request.body))
    // const jsonStr = JSON.stringify(request.body)
    const encrypt = md5(jsonStr + randomKey)
    const transferEntity = {
      object: jsonStr,
      sign: encrypt
    }
    request.body = transferEntity
  } else {
    request.headers['Content-Type'] = 'application/x-www-form-urlencoded'
  }
  // console.log('flyio发请求,request为', request);
  wx.showNavigationBarLoading()
  return request
})

request.interceptors.response.use(
  (response, promise) => {
    wx.hideNavigationBarLoading()

    if (response.data.hasOwnProperty('code') && response.data.code !== 0) {
      wx.showToast({
        title: response.data.message,
        image: '/static/images/error.png',
        duration: 2000
      })
      return promise.reject(response.data)
    }
    return promise.resolve(response.data)
  },
  (err, promise) => {
    wx.hideNavigationBarLoading()
    if (err.response) {
      const response = err.response.data
      if (response.code === 700) {
        wx.navigateTo({
          url: '/pages/my/auth/index'
        })
      } else {
        wx.showToast({
          title: response.message,
          icon: 'none',
          duration: 2000
        })
      }
      return promise.reject(response)
    } else {
      wx.showToast({
        title: err.message,
        icon: 'none',
        duration: 2000
      })
      return promise.reject(null)
    }
  }
)

export default request
