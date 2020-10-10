import axios from 'axios'
import store from '@/store'
import md5 from 'js-md5'
import { getRandomKey, getToken } from '@/utils/auth'
import { Message, MessageBox } from 'element-ui'

const Base64 = require('js-base64').Base64

// create an axios instance
const service = axios.create({
    baseURL: process.env.VUE_APP_BASE_API + '/api/admin', // url = base url + request url
    // withCredentials: true, // send cookies when cross-domain requests
    timeout: 8000 // request timeout
})

// request interceptor
service.interceptors.request.use(
  config => {
      // do something before request is sent
      if (store.getters.token) {
          config.headers['Authorization'] = `Bearer ${getToken()}`
      }
      if (config.headers['Content-Type'] === 'application/json') {
          // 数据加密
          console.log(config.data)

          const randomKey = getRandomKey()
          const jsonStr = Base64.encode(JSON.stringify(config.data))
          const encrypt = md5(jsonStr + randomKey)
          config.data = {
              object: jsonStr,
              sign: encrypt
          }
      }
      return config
  },
  error => {
      // do something with request error
      console.log(error) // for debug
      return Promise.reject(error)
  }
)

// response interceptor
service.interceptors.response.use(
  /**
   * If you want to get http information such as headers or status
   * Please return  response => response
   */

  /**
   * Determine the request status by custom code
   * Here is just an example
   * You can also judge the status by HTTP Status Code
   */
  response => {
      const res = response.data

      // eslint-disable-next-line no-prototype-builtins
      if (res.hasOwnProperty('code') && res.code !== 0) {
          Message({
              message: res.message || 'Error',
              type: 'error',
              duration: 3 * 1000
          })
          return Promise.reject(new Error(res.message || 'Error'))
      } else {
          return res
      }
  },
  error => {
      console.log('err' + error) // for debug
      if (!error.response) {
          return Promise.reject(error)
      }
      const data = error.response.data
      if (data.code === 700) {
          // to re-login
          MessageBox.confirm('You have been logged out, you can cancel to stay on this page, or log in again', 'Confirm logout', {
              confirmButtonText: 'Re-Login',
              cancelButtonText: 'Cancel',
              type: 'warning'
          }).then(() => {
              store.dispatch('admin/resetToken').then(() => {
                  location.reload()
              })
          })
      } else {
          Message({
              message: data.message,
              type: 'error',
              duration: 5 * 1000
          })
      }
      return Promise.reject(error)
  }
)

export default service
