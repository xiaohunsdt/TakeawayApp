import cartService from '../../../services/cart'

Page({
  data: {
    cartList: []
  },
  onLoad: function (options) {
    this.initCartData()
  },
  initCartData() {
    this.setData({
      cartList: cartService.getCartList(),
      cartAllCount: cartService.getCartAllCount(),
      cartAllPrice: cartService.getCartAllPrice()
    })
  },
})