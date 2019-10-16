import Vue from 'vue'
import Vuex from 'vuex'

import getters from './getters'
import cart from './modules/cart'

Vue.use(Vuex)

const store = new Vuex.Store({
  modules: {
    cart
  },
  getters
})

export default store
