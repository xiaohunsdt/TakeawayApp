const state = {
  currentCoupon: null
}

const mutations = {
  SET_COUPON: (state, coupon) => {
    state.currentCoupon = Object.assign({}, coupon)
  },
  CLEAR_COUPON: (state) => {
    state.currentCoupon = null
  }
}

const actions = {}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
