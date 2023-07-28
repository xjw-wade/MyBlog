import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import getPageTitle from '@/util/get-page-title'

Vue.use(VueRouter)

const routes = [
  {
		path: '/404',
		component: () => import('@/views/404'),
		meta: {title: '404 NOT FOUND'},
		hidden: true
	},
  {
		path: '/login',
		component: () => import('@/views/login'),
		meta: {title: '后台管理登录'},
		hidden: true
	},
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

//挂载路由守卫
router.beforeEach((to, from, next) => {
	if (to.path !== '/login') {
		//获取token
		const tokenStr = window.localStorage.getItem('token')
		if (!tokenStr) return next("/login")
	}
	document.title = getPageTitle(to.meta.title)
	next()
})

export default router
