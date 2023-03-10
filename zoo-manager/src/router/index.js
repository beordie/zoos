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
import Login from '@/components/Login'
import Home from '@/components/Home'
Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      redirect: '/home'
    },
    {
      path: '/login',
      components: {
        Main: Login
      }
    },
      {
        path: '/home',
        components: {
          Main: Home
        },
        children: [
          {
            path: '/user',
            components: {
              info: User
            }
          },
          {
              path: '/kinds',
              components: {
                info: ClassSpecies
              }
          },
          {
            path: '/subclass',
            components: {
              info: Subclass
            }
          },
          {
            path: '/orders',
            components: {
              info: Orders
            }
          },
          {
            path: '/family',
            components: {
              info: Family
            }
          },
          {
            path: '/genus',
            components: {
              info: Genus
            }
          },
          {
            path: '/picture',
            components: {
              info: Picture
            }
          },
          {
            path: '/photo',
            components: {
              info: Photo
            }
          },
          {
            path: '/zoo',
            components: {
              info: Zoo
            }
          },
          {
            path: '/event',
            components: {
              info: Event
            }
          },
        ]
    }
  ]
})