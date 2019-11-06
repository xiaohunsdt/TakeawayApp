const state = {
  cartList: []
}

const mutations = {
  ADD_GOODS: (state, goods) => {
    const existData = state.cartList.find(item => item.goodsId === goods.id)
    if (existData !== undefined) {
      existData.count++
      return
    }

    const cartData = {
      goodsId: goods.id,
      goods,
      count: 1
    }
    state.cartList.push(cartData)
  },
  REDUCE_GOODS: (state, goods) => {
    const existData = state.cartList.find(item => item.goodsId === goods.id)
    if (existData !== undefined && existData.count > 0) {
      existData.count--
      if (existData.count === 0) {
        const index = state.cartList.findIndex(item => item.goodsId === goods.id)
        state.cartList.splice(index, 1)
      }
    }
  },
  DELETE_GOODS: (state, goodsId) => {
    let existDataIndex = -1

    state.cartList.find((item, index) => {
      if (item.goodsId === goodsId) {
        existDataIndex = index
        return true
      }
      return false
    })
    state.cartList.slice(existDataIndex, 1)
  },
  CLEAR_CART: (state) => {
    state.cartList.splice(0, state.cartList.length)
  }
}

const actions = {}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
