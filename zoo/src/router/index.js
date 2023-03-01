import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/Home'
import Search from '@/components/search'
import Photo from '@/components/photo'
Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
    }, 
    {
      path: '/search',
      name: 'search',
      component: Search
    }, 
    {
      path: '/photo',
      name: 'photo',
      component: Photo
    }
  ]
})