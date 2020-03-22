const state = {
  cartList: [],
  cartAllCount: 0,
  cartAllPrice: 0
}

const mutations = {
  ADD_GOODS: (state, goods) => {
    const existData = state.cartList.find(item => item.goodsId === goods.id)
    if (existData !== undefined) {
      existData.count++
    } else {
      const cartData = {
        goodsId: goods.id,
        goods,
        count: 1
      }
      state.cartList.push(cartData)
    }
    state.cartAllCount++
    state.cartAllPrice += goods.price
  },
  REDUCE_GOODS: (state, goods) => {
    const index = state.cartList.findIndex(item => item.goodsId === goods.id)
    if (index < 0) {
      return
    }
    const existData = state.cartList[index]
    if (existData !== undefined && existData.count > 0) {
      existData.count--
      if (existData.count === 0) {
        state.cartList.splice(index, 1)
      }

      state.cartAllCount--
      state.cartAllPrice -= goods.price
    }
  },
  DELETE_GOODS: (state, goodsId) => {
    const index = state.cartList.findIndex(item => item.goodsId === goodsId)
    if (index < 0) {
      return
    }
    state.cartAllCount -= state.cartList[index].count
    state.cartAllPrice -= state.cartList[index].count * state.cartList[index].goods.price
    state.cartList.slice(index, 1)
  },
  CLEAR_CART: (state) => {
    // state.cartList.splice(0, state.cartList.length)
    state.cartList = []
    state.cartAllCount = 0
    state.cartAllPrice = 0
  }
}

const actions = {}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
