const state = {
  orderId: null
}

const mutations = {
  SET_ORDER_ID: (state, orderId) => {
    state.orderId = orderId
  },
  REMOVE_ORDER_ID: (state) => {
    state.orderId = null
  }
}

const actions = {}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
