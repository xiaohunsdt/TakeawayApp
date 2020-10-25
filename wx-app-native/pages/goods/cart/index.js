import cartService from '../../../services/cart'

Page({
  data: {
    store: null,
    cartList: []
  },
  onLoad: function (options) {
    this.initCartData()
  },
  initCartData() {
    this.setData({
      store: getApp().globalData.currentStore,
      cartList: cartService.getCartList(),
      cartAllCount: cartService.getCartAllCount(),
      cartAllPrice: cartService.getCartAllPrice()
    })
  },
})