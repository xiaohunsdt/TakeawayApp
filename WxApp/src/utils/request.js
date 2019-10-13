var Fly = require('flyio/dist/npm/wx')

const request = new Fly()

// 设置请求基地址
request.config.baseURL = 'http://localhost:8081/api/user'

request.interceptors.request.use((request) => {
  // 给所有请求添加自定义header，带上token信息让服务器验证用户登陆
  request.headers['Authorization'] = 'Bearer ' + mpvue.getStorageSync('token')
  request.headers['content-type'] = 'application/x-www-form-urlencoded'
  // console.log('flyio发请求,request为', request);
  mpvue.showNavigationBarLoading()
  return request
})

request.interceptors.response.use(
  (response, promise) => {
    mpvue.hideNavigationBarLoading()
    return promise.resolve(response.data)
  },
  (err, promise) => {
    mpvue.hideNavigationBarLoading()
    mpvue.showToast({
      title: err.message,
      icon: 'none'
    })
    return promise.resolve()
  }
)

export default request
