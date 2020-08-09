import addressService from '../../../../../services/address'
Page({
  data: {
    editLoading: false,
    addressList: []
  },

  onLoad: function (options) {
    this.init()
  },

  init() {},
  onSearch(event) {
    addressService.searchAddress(event.detail)
      .then(res => {
        this.setData({
          addressList: res
        })
      })
      .catch(() => {
        this.setData({
          addressList: []
        })
      })
  },
  onSelect(event) {
    let address = event.currentTarget.dataset.address;
    let eventChannel = this.getOpenerEventChannel()
    eventChannel.emit('setSelectedAddress', address)
    wx.navigateBack()
  }
})