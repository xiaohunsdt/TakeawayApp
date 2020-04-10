const state = {
  currentAddress: null
}

const mutations = {
  SET_ADDRESS: (state, address) => {
    // state.currentAddress = Object.assign({}, address)
    state.currentAddress = address
  }
}

const actions = {}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
