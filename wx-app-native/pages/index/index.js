
const app = getApp()
import indexService from '../../services/index'

Page({
  data: {
    bannerList: [],
    newGoodsList: [],
    hotGoodsList: []
  },
  onLoad: function (option) {
    console.log(option)
    if (option.from) {
      app.globalData.form = option.from
    }
    this.init()
  },
  init() {
    indexService.getBannersList().then(res => {
      this.setData({
        bannerList: res
      })
    })
    indexService.getNewGoodsList().then(res => {
      this.setData({
        newGoodsList: res
      })
    })
    indexService.getHotGoodsList().then(res => {
      this.setData({
        hotGoodsList: res
      })
    })
  },
  gotoPage(event) {
    let page = event.currentTarget.dataset.pagePath
    
    if (page === '') {
      return
    }
    
    wx.navigateTo({
      url: page
    })
  },
  onSearch(event) {
    wx.navigateTo({
      url: `./search/index?keyword=${event.detail}`
    })
  }
})