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
		{
			path: 'list',
			name: 'BlogList',
			component: () => import('@/views/blog/blog/BlogList'),
			meta: {title: '文章管理', icon: 'el-icon-s-order'}
		},
		{
			path: 'edit/:id',
			name: 'EditBlog',
			component: () => import('@/views/blog/blog/WriteBlog'),
			meta: {title: '编辑文章', icon: 'el-icon-edit'},
			hidden: true
		},
		{
			path: 'moment/write',
			name: 'WriteMoment',
			component: () => import('@/views/blog/moment/WriteMoment'),
			meta: {title: '写动态', icon: 'el-icon-edit'}
		},
		{
			path: 'moment/list',
			name: 'MomentList',
			component: () => import('@/views/blog/moment/MomentList'),
			meta: {title: '动态管理', icon: 'el-icon-chat-dot-round'}
		},
		{
			path: 'moment/edit/:id',
			name: 'EditMoment',
			component: () => import('@/views/blog/moment/WriteMoment'),
			meta: {title: '编辑动态', icon: 'el-icon-edit'},
			hidden: true
		},
		{
			path: 'category/list',
			name: 'CategoryList',
			component: () => import('@/views/blog/category/CategoryList'),
			meta: {title: '分类管理', icon: 'el-icon-s-opportunity'}
		},
		{
			path: 'tag/list',
			name: 'TagList',
			component: () => import('@/views/blog/tag/TagList'),
			meta: {title: '标签管理', icon: 'biaoqian'}
		},
		{
			path: 'comment/list',
			name: 'CommentList',
			component: () => import('@/views/blog/comment/CommentList'),
			meta: {title: '评论管理', icon: 'el-icon-s-comment'}
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
