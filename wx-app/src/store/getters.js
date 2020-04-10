const getters = {
  // cart
  cartGoodsList: state => state.cart.cartList,

  cartAllCount: state => state.cart.cartAllCount,

  cartAllPrice: state => state.cart.cartAllPrice,

  // cartAllCount: state => {
  //   let count = 0
  //   state.cart.cartList.forEach(item => {
  //     count += item.count
  //   })
  //   return count
  // },
  //
  // cartAllPrice: state => {
  //   let price = 0
  //   state.cart.cartList.forEach(item => {
  //     price += item.count * item.goods.price
  //   })
  //   return price
  // },

  cartCountByGoodsId: (state) => (goodsId) => {
    const existData = state.cart.cartList.find(item => item.goodsId === goodsId)
    return existData !== undefined ? existData.count : 0
  },

  // address
  currentAddress: state => state.address.currentAddress,

  currentCoupon: state => state.coupon.currentCoupon,

  from: state => state.from.from
}
export default getters
