import activityService from '../../../services/activity'

Page({

  /**
   * 页面的初始数据
   */
  data: {
    activityId: null,
    activity: null
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    if (options.activityId) {
      this.setData({
        activityId: options.activityId
      })
      this.init()
    }
  },
  onShareAppMessage: function () {
    return {
      title: this.data.activity.title,
      desc: '川香苑品牌中餐厅',
      path: `/pages/activity/detail/index?activityId=${this.data.activityId}`
    }
  },
  init () {
    activityService.getActivityById(this.data.activityId).then(res => {
      this.setData({
        activity: res
      })
    })
  }
})