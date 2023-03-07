import Vue from 'vue'
import App from './App.vue'
import router from './router'
import Axios from 'axios'
import ElementUI from 'element-ui'
import Moment from 'moment';
import 'element-ui/lib/theme-chalk/index.css'
import '@/assets/css/bootstrap.css'
import '@/assets/css/fonts.css'
import '@/assets/css/style.css'
import '@/assets/css/icon.css'

Vue.config.productionTip = false
Vue.prototype.$http = Axios
Vue.prototype.$moment = Moment
Vue.prototype.$host = 'http://127.0.0.1:80/'
Vue.use(ElementUI)

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
