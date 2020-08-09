import userApi from '../../../services/user'
import util from '../../../utils/util'

Page({
  login () {
    mpvue.showLoading({
      title: '正在登陆中...'
    })
    userApi.loginByWx()
      .then(() => {
        mpvue.hideLoading()
        const pages = util.getPages()
        let pre = null
        let index = 2
        do {
          pre = pages[pages.length - index++]
        } while (pre.route === 'pages/my/auth/index' && pages.length >= index)

        wx.reLaunch({
          url: `/${pre.route}`
        })
      })
      .catch(err => {
        wx.hideLoading()
        console.error(err)
      })
  }
})