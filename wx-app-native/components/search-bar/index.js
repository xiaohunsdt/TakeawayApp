Component({
  properties: {
    defaultValue: {
      type: String,
      value: ''
    },
    placeholder: {
      type: String,
      value: '请输入搜索关键词'
    },
    background: {
      type: String,
      value: 'transparent'
    }
  },
  observers:{
    'defaultValue':function(newVal){
      if (newVal !== '') {
        this.setData({
          searchValue: newVal
        })
      }
    }
  },
  data: {
    searchValue: ''
  },

  /**
   * 组件的方法列表
   */
  methods: {
    searchChanged(event) {
      this.setData({
        searchValue: event.detail
      })
    },
    onSearch() {
      this.triggerEvent('search', this.data.searchValue)
    }
  }
})