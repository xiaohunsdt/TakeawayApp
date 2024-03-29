import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from 'vuex-persistedstate'
import createLogger from 'vuex/dist/logger'

import getters from './getters'
import cart from './modules/cart'
import address from './modules/address'
import coupon from './modules/coupon'
import from from './modules/from'

Vue.use(Vuex)

// const debug = process.env.NODE_ENV !== 'production'
const debug = false

const plugins = [
  createPersistedState({
    storage: {
      getItem: key => wx.getStorageSync(key),
      setItem: (key, value) => wx.setStorageSync(key, value),
      removeItem: key => {}
    }
  })
]

if (debug) {
  plugins.push(createLogger())
}

const store = new Vuex.Store({
  modules: {
    cart,
    address,
    coupon,
    from
  },
  getters,
  strict: debug,
  plugins
})

export default store
