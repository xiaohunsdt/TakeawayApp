const state = {
  from: null
}

const mutations = {
  SET_FROM: (state, from) => {
    state.from = from
  },
  CLEAR_FROM: (state) => {
    state.from = null
  }
}

const actions = {}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
