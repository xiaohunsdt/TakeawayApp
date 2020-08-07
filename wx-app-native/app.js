//app.js
import userService from './services/user'

App({
  onLaunch: function () {
    // 检测权限是否打开
    wx.getSetting({
      success (res) {
        if (!res.authSetting['scope.userLocation']) {
          wx.authorize({
            scope: 'scope.userLocation',
            success () {
              console.log('userLocation 权限已经打开')
            }
          })
        }
      }
    })

    // 检测登录
    userService.checkLogin()
      .catch(() => {
        userService.loginByWx()
          .then((res) => {
            console.log('微信登陆成功')
            // 设置用户信息
            userService.setUserInfo()
          })
          .catch((res) => {
            console.log(res)
          })
      })
  },
  globalData: {
    userInfo: null
  }
})