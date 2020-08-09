import addressService from '../../../services/address'

Page({
  data: {
    addressList: []
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    this.init()
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {
    const selectedAddress = this.data.addressList.find(item => item.isDefault)
    if (selectedAddress) {
      getApp().globalData.currentAddress = selectedAddress
    }
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
    this.init()
    wx.stopPullDownRefresh()
  },
  init() {
    addressService.getMyAddressList()
      .then(res => {
        this.setData({
          addressList : res
        })
      })
  },
  addNewAddress() {
    wx.navigateTo({
      url: './edit/index'
    })
  },
  editAddress (event) {
    let addressId = event.currentTarget.dataset.addressId
    wx.navigateTo({
      url: `./edit/index?addressId=${addressId}`
    })
  },
  onSetDefault(event) {
    let addressId = event.currentTarget.dataset.addressId
    addressService.setDefault(addressId)
      .then(res => {
        // 将当前的地址设置成订单地址
        const address = this.data.addressList.find(item => item.id === addressId)
        getApp().globalData.currentAddress = address
        this.init()
      })
  },
  onDeleteAddress(event) {
    const $this = this
    let addressId = event.currentTarget.dataset.addressId

    wx.showModal({
      title: '提示',
      content: '您确定要删除这个地址吗?',
      success(res) {
        if (res.confirm) {
          addressService.deleteAddress(addressId)
            .then(res => {
              $this.init()
            })
        }
      }
    })
  }
})