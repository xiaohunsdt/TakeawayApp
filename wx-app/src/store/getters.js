const getters = {
  // cart
  cartGoodsList: state => state.cart.cartList,

  cartAllCount: state => state.cart.cartAllCount,

  cartAllPrice: state => state.cart.cartAllPrice,

  cartCountByGoodsId: (state) => (goodsId) => {
    const index = state.cart.cartList.findIndex(item => item.goodsId === goodsId)
    return index > -1 ? state.cart.cartList[index].count : 0
  },

  // address
  currentAddress: state => state.address.currentAddress,

  currentCoupon: state => state.coupon.currentCoupon
}
export default getters
