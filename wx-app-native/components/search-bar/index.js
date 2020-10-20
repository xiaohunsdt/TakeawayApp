// components/search-bar/index.js
Component({
  /**
   * 组件的属性列表
   */
  properties: {
    placeholder:{
      type: String,
      value: '请输入搜索关键词'
    },
    background:{
      type: String,
      value: 'transparent'
    }
  },

  /**
   * 组件的初始数据
   */
  data: {
    searchValue: ''
  },

  /**
   * 组件的方法列表
   */
  methods: {
    searchChanged (event) {
      this.setData({
        searchValue: event.detail
      })
    },
    onSearch () {
      this.triggerEvent('search', this.data.searchValue)
    }
  }
})
