import userService from './services/user'

App({
  onLaunch: function () {
    // 检测登录
    userService.checkLogin()
      .catch(() => {
        userService.loginByWx()
          .then((res) => {
            console.log('微信登陆成功')
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
    if(!Array.prototype.contains){
      Array.prototype.contains = function (obj) {
        var i = this.length;
        while (i--) {
            if (this[i] === obj) {
                return true;
            }
        }
        return false;
      }
    }
  },
  globalData: {}
})