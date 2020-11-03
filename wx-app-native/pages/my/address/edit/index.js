import addressService from '../../../../services/address'

Page({
  data: {
    editLoading: false,
    addressId: '',
    address: {
      address: '',
      detail: '',
      phone: '',
      x: 0,
      y: 0
    }
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    if (options.addressId) {
      this.setData({
        addressId: options.addressId
      })
      addressService.getAddressById(this.data.addressId).then(res => {
        this.setData({
          address: res
        })
      })
    }
  },
  clickRightIcon() {
    wx.showToast({
      icon: 'none',
      title: '请输入街道名,小区名,道路名\r\n例如: 서울 마포구 신촌로 150'
    })
  },
  regionInput(value) {
    this.setData({
      "address.address":value.detail
    })
  },
  detailInput(value) {
    this.setData({
      "address.detail":value.detail
    })
  },
  phoneInput(value) {
    this.setData({
      "address.phone":value.detail
    })
  },
  onSearchAddress(event) {
    var $this = this
    wx.navigateTo({
      url: './search/index',
      events: {
        setSelectedAddress(data) {
          $this.setData({
            "address.address": data.address,
            "address.x": data.x,
            "address.y": data.y
          })
        }
      }
    })
  },
  editBtnClick() {
    if (!this.data.addressId) {
      this.create()
    } else {
      this.edit()
    }
  },
  create() {
    this.setData({
      editLoading: true
    })
    addressService.createNewAddress(this.data.address)
      .then(res => {
        wx.showToast({
          title: res.message,
          icon: 'success',
          duration: 2000
        })
        this.setData({
          editLoading: false
        })
        wx.navigateBack()
      })
      .catch(() => {
        this.setData({
          editLoading: false
        })
      })
  },
  edit() {
    const {
      address,
      phone
    } = this.data.address
    if (!address || !phone) {
      wx.showToast({
        title: '请填写完整信息',
        icon: 'none',
        duration: 2000
      })
      return
    }

    this.setData({
      editLoading: true
    })
    addressService.updateAddress(this.data.address)
      .then(res => {
        wx.showToast({
          title: res.message,
          icon: 'success',
          duration: 2000
        })
        this.setData({
          editLoading: false
        })
        wx.navigateBack()
      })
      .catch(() => {
        this.setData({
          editLoading: false
        })
      })
  }
})