export function getCart () {
  const storeId = getApp().globalData.currentStoreId
  if(!getApp().globalData.cart[storeId]){
    clearCart()
  }
  return getApp().globalData.cart[storeId]
}

export function getCartList () {
  return getCart().cartList
}

export function getCartAllCount () {
  return getCart().cartAllCount
}

export function getCartAllPrice () {
  return getCart().cartAllPrice
}

export function find (goods) {
  return getCartList().find(item => item.goodsId === goods.id)
}

export function findIndex (goods) {
  return getCartList().findIndex(item => item.goodsId === goods.id)
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
