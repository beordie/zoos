import Vue from 'vue'
import Router from 'vue-router'
import User from '@/components/User'
import ClassSpecies from '@/components/classify/ClassSpecies'
import Subclass from '@/components/classify/Subclass'
import Orders from '@/components/classify/Orders'
import Family from '@/components/classify/Family'
import Genus from '@/components/classify/Genus'
import Picture from '@/components/Picture'
import Photo from '@/components/Photo'
import Zoo from '@/components/Zoo'
import Event from '@/components/Event'
Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
      {
          path: '/user',
          name: 'user',
          component: User
      },
      {
          path: '/kinds',
          name: 'kinds',
          component: ClassSpecies
      },
      {
        path: '/subclass',
        name: 'subclass',
        component: Subclass
      },
      {
        path: '/orders',
        name: 'orders',
        component: Orders
      },
      {
        path: '/family',
        name: 'family',
        component: Family
      },
      {
        path: '/genus',
        name: 'genus',
        component: Genus
      },
      {
        path: '/picture',
        name: 'picture',
        component: Picture
      },
      {
        path: '/photo',
        name: 'photo',
        component: Photo
      },
      {
        path: '/zoo',
        name: 'zoo',
        component: Zoo
      },
      {
        path: '/event',
        name: 'event',
        component: Event
      }
  ]
})