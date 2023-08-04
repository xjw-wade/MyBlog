import Vue from 'vue'
import VueRouter from 'vue-router'
import getPageTitle from '@/util/get-page-title'
import Layout from '@/layout'

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
		component: Layout,
		redirect: '/dashboard',
		children: [
			{
				path: 'dashboard',
				name: 'Dashboard',
				component: () => import('@/views/dashboard'),
				meta: {title: 'Dashboard', icon: 'dashboard'}
			}
		]
	},
  {
    path: '/blog',
	name: 'Blog',
	redirect: '/blog/write',
	component: Layout,
	meta: {title: '博客管理', icon: 'el-icon-menu'},
	children: [
		{
			path: 'write',
			name: 'WriteBlog',
			component: () => import('@/views/blog/blog/WriteBlog'),
			meta: {title: '写文章', icon: 'el-icon-edit'}
		},
	]


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
