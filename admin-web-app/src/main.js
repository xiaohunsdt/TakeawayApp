import Vue from 'vue'

import 'normalize.css/normalize.css' // A modern alternative to CSS resets

import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import locale from 'element-ui/lib/locale/lang/en' // lang i18n

import '@/styles/index.scss' // global css

import App from './App'
import store from './store'
import router from './router'

import '@/icons' // icon
import '@/permission' // permission control

/**
 * If you don't want to use mock-server
 * you want to use MockJs for mock api
 * you can execute: mockXHR()
 *
 * Currently MockJs will be used in the production environment,
 * please remove it before going online! ! !
 */
import {mockXHR} from '../mock'

if (process.env.NODE_ENV === 'production') {
  mockXHR()
}

// set ElementUI lang to EN
Vue.use(ElementUI, {locale})

import naver from 'vue-naver-maps'
Vue.use(naver, {
  clientID: 'jwsa1q8wp1',
  useGovAPI: false, // 공공 클라우드 API 사용 (선택)
  subModules: '' // 서브모듈 (선택)
})

import VueClipboard from 'vue-clipboard2'
VueClipboard.config.autoSetContainer = true
Vue.use(VueClipboard)

Vue.config.productionTip = false
Vue.prototype.$VUE_APP_BASE_API = process.env.VUE_APP_BASE_API

new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
})


