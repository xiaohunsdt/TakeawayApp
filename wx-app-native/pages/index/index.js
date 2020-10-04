const app = getApp()
import indexService from '../../services/index'
import storeService from '../../services/store'
import addressService from '../../services/address'
const watch = require("../../utils/watch.js")

Page({
  watch: {
    currentAddress(newVal) {
      console.log(newVal.x)
      if (newVal.x === 0 || newVal.y === 0) {
        return;
      }
      storeService.getAvailableStoreList(newVal.x, newVal.y).then(res => {
        this.setData({
          storeList: res
        })
      })
    }
  },
  data: {
    statusBarHeight: 0,
    bannerList: [],
    newGoodsList: [],
    hotGoodsList: [],
    storeList: [],
    currentAddress: {
      address: '加载中...',
      x: 0.0,
      y: 0.0
    }
  },
  onLoad: function (option) {
    watch.setWatcher(this)
    this.setData({
      statusBarHeight: wx.getSystemInfoSync()['statusBarHeight']
    })
    if (option.from) {
      app.globalData.from = option.from
    }
    this.init()
  },
  onShow: function () {
    if (getApp().globalData.currentAddress && getApp().globalData.currentAddress !== this.data.currentAddress) {
      this.setData({
        currentAddress: getApp().globalData.currentAddress
      })
    }
  },
  onPullDownRefresh() {
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
    if (!app.globalData.currentAddress) {
      addressService.getDefaultAddress().then(res => {
        if (res.address) {
          app.globalData.currentAddress = res
          this.setData({
            currentAddress: app.globalData.currentAddress
          })
        } else {
          const $this = this;
          wx.getLocation({
            success: function (res) {
              var latitude = res.latitude;
              var longitude = res.longitude;
              console.log(latitude, longitude)
              $this.setData({
                currentAddress: {
                  address: '附近地址',
                  x: longitude,
                  y: latitude
                }
              })
            },
            fail(res) {
              $this.setData({
                currentAddress: {
                  address: '获取失败',
                  x: 0,
                  y: 0
                }
              })
              wx.showModal({
                title: '提示',
                content: '无法获取您当前的位置信息,请检查权限是否打开',
                showCancel: false
              })
            }
          })
        }
      })
    } else {
      this.setData({
        currentAddress: app.globalData.currentAddress
      })
    }
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