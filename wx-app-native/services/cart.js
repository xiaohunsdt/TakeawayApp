export function getCart () {
  const storeId = getApp().globalData.currentStoreId
  if(!getApp().globalData.cart[storeId]){
    clearCart()
  }
  return getApp().globalData.cart[storeId]
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

export function clearCart () {
  const storeId = getApp().globalData.currentStoreId
  getApp().globalData.cart[storeId] = {
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