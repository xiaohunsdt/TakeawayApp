import searchApi from '../../../services/search'

Page({
  data: {
    keyword: null,
    produceList: []
  },
  onLoad (option) {
    if (option.keyword) {
      this.onSearch(option.keyword)
    }
  },
  onSearchEvent(event){
    this.onSearch(event.detail)
  },
  onSearch (val) {
    if(!val || val===''){
      wx.showToast({
        icon: 'none',
        title: '请输入关键字'
      })

      return
    }
    this.setData({
      keyword: val
    })
    searchApi.searchGoods(this.data.keyword).then(res => {
      this.setData({
        produceList: res
      })
    })
  }
})