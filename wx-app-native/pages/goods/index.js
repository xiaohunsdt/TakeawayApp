import produceService from '../../services/produce'
import settingService from '../../services/setting'

Page({
  /**
   * 页面的初始数据
   */
  data: {
    height: 800,
    currentIndex: 0,
    categories: [],
    pageSettings: {}
  },
  onLoad: function (options) {
    this.init()
  },
  onShow: function () {},
  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
    this.init()
    wx.stopPullDownRefresh()
  },
  init() {
    // 获取相关设置项
    settingService.getGoodsPageSettings()
      .then(res => {
        this.setData({
          pageSettings: res
        })

        // 设置高度
        const _this = this
        setTimeout(function () {
          _this.setHeaderHeight()
        }, 1000)
      })

    // 获取所有商品
    wx.showLoading({
      title: '正在加载中...'
    })
    produceService.getAllList()
      .then(res => {
        this.setData({
          categories: res.categoryGoods
        })
      })
      .finally(() => {
        wx.hideLoading()
      })
  },
  setHeaderHeight() {
    const _this = this

    wx.createSelectorQuery().select('#header').boundingClientRect(function (headerRect) {
      wx.getSystemInfo({
        success: function (res, rect) {
          if (headerRect && headerRect.height) {
            _this.setData({
              height: res.windowHeight - headerRect.height - 10
            })
          }
        }
      })
    }).exec()
  }
})