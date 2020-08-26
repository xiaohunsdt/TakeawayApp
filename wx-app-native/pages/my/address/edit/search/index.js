import addressService from '../../../../../services/address'
Page({
  data: {
    editLoading: false,
    addressList: [],
    selectedAddress: null,
    addressStaticMap: null,
    showConfirmDialog: false
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

    addressService.getAddressStaticMap(address)
      .then(res => {
        this.setData({
          selectedAddress: address,
          addressStaticMap: "data:image/png;base64," + res.message,
          showConfirmDialog: true
        })
      })
  },
  onConfirm() {
    let eventChannel = this.getOpenerEventChannel()
    eventChannel.emit('setSelectedAddress', this.data.selectedAddress)
    wx.navigateBack()
  }
})