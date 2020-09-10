// components/store-card/index.js
Component({
  /**
   * 组件的属性列表
   */
  properties: {
    store: Object
  },

  /**
   * 组件的初始数据
   */
  data: {

  },

  /**
   * 组件的方法列表
   */
  methods: {
    goToStore(event) {
      wx.navigateTo({
        url: `/pages/goods/index?storeId=${this.data.store.id}`
      })
    },
    showServiceStopNotice() {
      wx.showModal({
        content: this.data.store.serviceNotice,
        showCancel: false
      })
    }
  }
})