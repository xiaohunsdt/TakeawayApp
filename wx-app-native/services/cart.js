export function getCart() {
  return getApp().globalData.cart
}

export function getCartList() {
  return getCart().cartList
}

export function getCartAllCount() {
  return getCart().cartAllCount
}

export function getCartAllPrice() {
  return getCart().cartAllPrice
}

export function find(goods) {
  return goods ? getCartList().find(item => item.produce.selectedGoods.id === goods.id) : undefined
}

export function findIndex(goods) {
  return goods ? getCartList().findIndex(item => item.produce.selectedGoods.id === goods.id) : -1
}

export function clearCart() {
  getApp().globalData.cart = {
    cartList: [],
    cartAllCount: 0,
    cartAllPrice: 0
  }
}
export default {
  getCart,
  getCartList,
  getCartAllCount,
  getCartAllPrice,
  find,
  findIndex,
  clearCart
}