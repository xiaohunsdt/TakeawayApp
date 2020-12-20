import userApi from '../../services/user'
import util from '../../utils/util'

Page({
  login () {
    wx.showLoading({
      title: '正在登陆中...'
    })
    userApi.loginByWx()
      .then(() => {
        const pages = getCurrentPages()
        let pre = null
        let index = 2
        do {
          pre = pages[pages.length - index++]
        } while (pre.route === 'pages/auth/index' && pages.length >= index)
        console.log(pre)
        wx.reLaunch({
          url: `/${pre.route}${util.urlEncode(pre.options,true)}`
        })
      })
      .finally(err => {
        wx.hideLoading()
      })
  }
})