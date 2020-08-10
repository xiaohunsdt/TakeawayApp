// pages/buy/remerk/index.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    inputVal: null
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {},
  addPs(event) {
    let ps = event.currentTarget.dataset.ps
    if (this.data.inputVal) {
      this.setData({
        inputVal: this.data.inputVal + `,${ps}`
      })
    } else {
      this.setData({
        inputVal: ps
      })
    }
  },
  setPs() {
    const eventChannel = this.getOpenerEventChannel()
    eventChannel.emit('setPsContent', {
      ps: this.data.inputVal
    })
    wx.navigateBack()
  }
})