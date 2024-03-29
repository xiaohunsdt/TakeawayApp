import userApi from '../../../services/user'

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
        } while (pre.route === 'pages/my/auth/index' && pages.length >= index)

        wx.reLaunch({
          url: `/${pre.route}`
        })
      })
      .finally(err => {
        wx.hideLoading()
      })
  }
})