import userService from '../../../services/user'
Page({
  data: {
    storeId: '1302193963869949953',
    signInDays: null
  },
  onLoad: function (options) {
    this.init()
  },
  onPullDownRefresh: function () {
    this.init()
    wx.stopPullDownRefresh()
  },
  init(){
    userService.getSignInDays(this.data.storeId).then(res => {
      let signInedDay = []
      if (res.signInedDay) {
        res.signInedDay.forEach(item => {
          signInedDay.push(parseInt(item))
        })
      }

      res.beginOfMonth = parseInt(res.beginOfMonth)
      res.endOfMonth = parseInt(res.endOfMonth)
      res.signInedDay = signInedDay

      this.setData({
        "signInDays": res
      })
    })
  }
})