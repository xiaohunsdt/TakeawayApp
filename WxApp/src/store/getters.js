const getters = {
  // cart
  cartGoodsList: state => state.cart.cartList,
  cartAllCount: state => {
    let count = 0
    state.cart.cartList.forEach(item => {
      count += item.count
    })
    return count
  },
  cartCountByGoodsId: (state) => (goodsId) => {
    const existData = state.cart.cartList.find(item => item.goodsId === goodsId)
    return existData ? existData.count : 0
  },
  cartAllPrice: state => {
    let price = 0
    state.cart.cartList.forEach(item => {
      price += item.goods.price * item.count
    })
    return price
  },
  cartPriceByGoodsId: (state) => (goodsId) => {
    const existData = state.cart.cartList.find(item => item.goodsId === goodsId)
    return existData ? existData.goods.price * existData.count : 0
  }
}
export default getters
