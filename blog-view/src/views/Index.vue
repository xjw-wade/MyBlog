<template>
	<div class="site">
		<!--顶部导航-->
		<Nav :blogName="siteInfo.blogName" :categoryList="categoryList"/>
		<!--首页大图 只在首页且pc端时显示-->
		<div class="m-mobile-hide">
			<Header v-if="$route.name==='home'"/>
		</div>
        
		<div class="main">
			<div class="m-padded-tb-big">
				<div class="ui container">
					<div class="ui stackable grid">
						<!--左侧-->
						<div class="three wide column m-mobile-hide">
							<Introduction :class="{'m-display-none':focusMode}"/>
						</div>
						<!--中间-->
						<div class="ten wide column">
							<keep-alive include="Home">
								<router-view/>
							</keep-alive>
						</div>
						<!--右侧-->
						<div class="three wide column m-mobile-hide">
							<RandomBlog :randomBlogList="randomBlogList" :class="{'m-display-none':focusMode}"/>
							<Tags :tagList="tagList" :class="{'m-display-none':focusMode}"/>
							<!--只在文章页面显示目录-->
							<Tocbot v-if="$route.name==='blog'"/>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!--私密文章密码对话框-->
		<BlogPasswordDialog/>

		<!--APlayer-->
		<div class="m-mobile-hide">
			<meting-js :server="siteInfo.playlistServer" :id="siteInfo.playlistId" type="playlist" fixed="true" theme="#25CCF7" v-if="siteInfo.playlistServer && siteInfo.playlistId"></meting-js>
		</div>
		<!--回到顶部-->
		<el-backtop style="box-shadow: none;background: none;z-index: 9999;">
			<img src="/img/paper-plane.png" style="width: 40px;height: 40px;">
		</el-backtop>
		<!--底部footer-->
		<Footer :siteInfo="siteInfo" :badges="badges" :newBlogList="newBlogList" :hitokoto="hitokoto"/>
	</div>
</template>

<script>
	import {getHitokoto, getSite} from '@/api/index'
	import Nav from "@/components/index/Nav";
	import Header from "@/components/index/Header";
	import Footer from "@/components/index/Footer";
	import Introduction from "@/components/sidebar/Introduction";
	import Tags from "@/components/sidebar/Tags";
	import RandomBlog from "@/components/sidebar/RandomBlog";
	import Tocbot from "@/components/sidebar/Tocbot";
	import BlogPasswordDialog from "@/components/index/BlogPasswordDialog";
	import {mapState} from 'vuex'
	import {SAVE_CLIENT_SIZE, SAVE_INTRODUCTION, SAVE_SITE_INFO, RESTORE_COMMENT_FORM} from "@/store/mutations-types";

	export default {
		name: "Index",
		// components: {Header, BlogPasswordDialog, Tocbot, RandomBlog, Tags, Nav, Footer, Introduction},
		components: {Nav, Header, Footer, Introduction, Tags, RandomBlog, Tocbot, BlogPasswordDialog},
		data() {
			return {
				siteInfo: {
					blogName: '',
					webTitleSuffix: '',
					playlistServer: '',
					playlistId: ''
				},
				categoryList: [],
				tagList: [],
				randomBlogList: [],
				badges: [],
				newBlogList: [],
				hitokoto: {},
			}
		},
		computed: {
			//将 Vuex store 的 state 对象中所对应的 module 的 focusMode 属性，映射为当前 Vue 实例中的 computed 对象的 focusMode 属性
			...mapState(['focusMode'])
		},
		watch: {
			//路由改变时，页面滚动至顶部
			'$route.path'() {
				this.scrollToTop()
			}
		},
		created() {
			this.getSite()
			this.getHitokoto()
			//从localStorage恢复之前的评论信息
			//当这个 mutation 被提交时，store 对象中定义的相应方法会被执行，store 中的状态会相应地改变，从而达到更新页面显示的效果。具体的 mutation 实现需要在 Vuex store 的 mutation 部分中进行定义，以实现特定的业务逻辑。
			this.$store.commit(RESTORE_COMMENT_FORM)
		},
		mounted() {
			//通过在 mounted() 函数中监听浏览器窗口大小变化，避免了复杂的自行设计隐藏的 resize 事件监听器，从而实现了客户端窗口大小随窗口大小变化而更改的目的。这是一个常见的前端技术应用场景。
			//保存可视窗口大小
			this.$store.commit(SAVE_CLIENT_SIZE, {clientHeight: document.body.clientHeight, clientWidth: document.body.clientWidth})
			window.onresize = () => {
				this.$store.commit(SAVE_CLIENT_SIZE, {clientHeight: document.body.clientHeight, clientWidth: document.body.clientWidth})
			}
		},
		methods: {
			getSite() {
				getSite().then(res => {
					if (res.code === 200) {
						this.siteInfo = res.data.siteInfo
						this.badges = res.data.badges
						this.newBlogList = res.data.newBlogList
						this.categoryList = res.data.categoryList
						this.tagList = res.data.tagList
						this.randomBlogList = res.data.randomBlogList
						this.$store.commit(SAVE_SITE_INFO, this.siteInfo)
						this.$store.commit(SAVE_INTRODUCTION, res.data.introduction)
						document.title = this.$route.meta.title + this.siteInfo.webTitleSuffix
					}
				})
			},
			//获取一言
			getHitokoto() {
				getHitokoto().then(res => {
					this.hitokoto = res
				})
			}
		}
	}
</script>

<style scoped>
	.site {
		display: flex;
		min-height: 100vh; /* 没有元素时，也把页面撑开至100% */
		flex-direction: column;
	}

	.main {
		margin-top: 40px;
		flex: 1;
	}

	.main .ui.container {
		width: 1400px !important;
		margin-left: auto !important;
		margin-right: auto !important;
	}

	.ui.grid .three.column {
		padding: 0;
	}

	.ui.grid .ten.column {
		padding-top: 0;
	}

	.m-display-none {
		display: none !important;
	}
</style>