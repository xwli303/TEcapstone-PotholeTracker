import Vue from 'vue'
import App from './App.vue'
import router from './router/index'
import store from './store/index'
import axios from 'axios'
// import * as GmapVue from 'gmap-vue'

// Vue.use(GmapVue, {
//   load: {
//     key: 'AIzaSyDRcIpT0bxsU80JISszJUMH2uyxYpZ2I-w'
//   },
//   installComponents: true
// });

Vue.config.productionTip = false

axios.defaults.baseURL = process.env.VUE_APP_REMOTE_API;

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
