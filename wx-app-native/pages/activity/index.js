import activityService from '../../services/activity'

Page({

  /**
   * 页面的初始数据
   */
  data: {
    activityList: []
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.init()
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
    this.init()
    wx.stopPullDownRefresh()
  },
  init() {
    activityService.getAllActivityList().then(res => {
      this.setData({
        activityList: res
      })
    })
  },
  onActivityClick(event) {
    const id = event.currentTarget.dataset.activityId
    wx.navigateTo({
      url: `./detail/index?activityId=${id}`
    })
  }
})