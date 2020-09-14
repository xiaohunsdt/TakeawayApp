//app.js
import userService from './services/user'

App({
  onLaunch: function () {
    // 检测权限是否打开
    wx.getSetting({
      success(res) {
        if (!res.authSetting['scope.userLocation']) {
          wx.authorize({
            scope: 'scope.userLocation',
            success() {
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

    if (!Promise.prototype.finally) {
      Promise.prototype.finally = function (callback) {
        this.then(res => {
          callback && callback(res)
        }, error => {
          callback && callback(error)
        })
      }
    }
  },
  globalData: {
    cart: {
      cartList: [],
      cartAllCount: 0,
      cartAllPrice: 0
    },
    currentStoreId: null,
    currentStore: null,
    currentAddress: null,
    currentCoupon: null,
    from: null
  }
})

      // {
      //   "text": "点餐",
      //   "pagePath": "pages/goods/index",
      //   "iconPath": "static/tabs/order.png",
      //   "selectedIconPath": "static/tabs/order_active.png"
      // },