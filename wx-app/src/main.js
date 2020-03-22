import Vue from 'vue'
import App from './App'
import store from '@/store/store'

Vue.config.productionTip = false
Vue.config._mpTrace = true

App.mpType = 'app'

Vue.prototype.$store = store

const app = new Vue(App)
app.$mount()
