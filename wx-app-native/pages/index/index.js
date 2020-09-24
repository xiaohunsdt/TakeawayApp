
const app = getApp()
import indexService from '../../services/index'
import storeService from '../../services/store'
Page({
  data: {
    bannerList: [],
    newGoodsList: [],
    hotGoodsList: [],
    storeList: []
  },
  onLoad: function (option) {
    // console.log(option)
    if (option.from) {
      app.globalData.from = option.from
    }
    this.init()
  },
  onPullDownRefresh () {
    this.init()
    wx.stopPullDownRefresh()
  },
  onShareAppMessage: function () {
    return {
      title: '川香苑',
      desc: '川香苑品牌中餐厅',
      path: '/pages/index/index'
    }
  },
  init() {
    indexService.getBannersList().then(res => {
      this.setData({
        bannerList: res
      })
    })
    storeService.getAllStoreList().then(res =>{
      this.setData({
        storeList: res
      })
    })
    // indexService.getNewGoodsList().then(res => {
    //   this.setData({
    //     newGoodsList: res
    //   })
    // })
    // indexService.getHotGoodsList().then(res => {
    //   this.setData({
    //     hotGoodsList: res
    //   })
    // })
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