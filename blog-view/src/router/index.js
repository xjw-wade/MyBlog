import Vue from 'vue'
import VueRouter from 'vue-router'
import getPageTitle from '@/util/get-page-title'

Vue.use(VueRouter)

const routes = [
  {
		path: '/login',
		component: () => import('@/views/Login'),
		meta: {title: '登录'}
	},
  {
    path: '/',
    component: () => import('@/views/Index'),
	redirect: '/home',
    children: [
        {
			path: '/home',
			name: 'home',
			component: () => import('@/views/home/Home'),
			meta: {title: '首页'}
		},
		{
			path: '/category/:name',
			name: 'category',
			component: () => import('@/views/category/Category'),
			meta: {title: '分类'}
		},
		{
			path: '/archives',
			name: 'archives',
			component: () => import('@/views/archives/Archives'),
			meta: {title: '归档'}
		},
		{
			path: '/moments',
			name: 'moments',
			component: () => import('@/views/moments/Moments'),
			meta: {title: '动态'}
		},
		{
			path: '/friends',
			name: 'friends',
			component: () => import('@/views/friends/Friends'),
			meta: {title: '友人帐'}
		},
    ]
  },
 
]

const router = new VueRouter({
	mode: 'history',
	base: process.env.BASE_URL,
	routes
})

//挂载路由守卫
router.beforeEach((to, from, next) => {
	document.title = getPageTitle(to.meta.title)
	next()
})

export default router
