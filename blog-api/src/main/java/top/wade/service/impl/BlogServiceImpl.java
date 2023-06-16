package top.wade.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.wade.constant.RedisKeyConstants;
import top.wade.entity.Blog;
import top.wade.mapper.BlogMapper;
import top.wade.model.vo.BlogInfo;
import top.wade.model.vo.PageResult;
import top.wade.service.BlogService;
import top.wade.service.RedisService;
import top.wade.service.TagService;
import top.wade.util.JacksonUtils;
import top.wade.util.markdown.MarkdownUtils;

import java.util.List;

/**
 * @Author xjw
 * @Date 2023/6/9 16:14
 * @Description: 博客文章业务层实现
 */
@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    BlogMapper blogMapper;
    @Autowired
    RedisService redisService;
    @Autowired
    TagService tagService;
    //随机博客显示5条
    private static final int randomBlogLimitNum = 5;
    //最新推荐博客显示3条
    private static final int newBlogPageSize = 3;
    //每页显示5条博客简介
    private static final int pageSize = 5;
    //博客简介列表排序方式
    private static final String orderBy = "is_top desc, create_time desc";
    //私密博客提示
    private static final String PRIVATE_BLOG_DESCRIPTION = "此文章受密码保护！";

    @Override
    public List<Blog> getListByTitleAndCategoryId(String title, Integer categoryId) {
        return null;
    }

    @Override
    public PageResult<BlogInfo> getBlogInfoListByIsPublished(Integer pageNum) {
        String redisKey = RedisKeyConstants.HOME_BLOG_INFO_LIST;
        System.out.println("xjw233");
        //redis已有当前页缓存
        PageResult<BlogInfo> pageResultFromRedis = redisService.getBlogInfoPageResultByHash(redisKey, pageNum);
        if (pageResultFromRedis != null) {
            setBlogViewsFromRedisToPageResult(pageResultFromRedis);
            return pageResultFromRedis;
        }
        System.out.println("xjw233");
        //redis没有缓存，从数据库查询，并添加缓存
        PageHelper.startPage(pageNum, pageSize, orderBy);
        List<BlogInfo> blogInfos = processBlogInfosPassword(blogMapper.getBlogInfoListByIsPublished());
        PageInfo<BlogInfo> pageInfo = new PageInfo<>(blogInfos);
        PageResult<BlogInfo> pageResult = new PageResult<>(pageInfo.getPages(), pageInfo.getList());
        setBlogViewsFromRedisToPageResult(pageResult);
        //添加首页缓存
        redisService.saveKVToHash(redisKey, pageNum, pageResult);
        return pageResult;
    }

    /**
     * 将pageResult中博客对象的浏览量设置为Redis中的最新值
     *
     * @param pageResult
     */
    private void setBlogViewsFromRedisToPageResult(PageResult<BlogInfo> pageResult) {
        String redisKey = RedisKeyConstants.BLOG_VIEWS_MAP;
        List<BlogInfo> blogInfos = pageResult.getList();
        for (int i = 0; i < blogInfos.size(); i++) {
            BlogInfo blogInfo = JacksonUtils.convertValue(blogInfos.get(i), BlogInfo.class);
            Long blogId = blogInfo.getId();
            /**
             * 这里如果出现异常，通常是手动修改过 MySQL 而没有通过后台管理，导致 Redis 和 MySQL 不同步
             * 从 Redis 中查出了 null，强转 int 时出现 NullPointerException
             * 直接抛出异常比带着 bug 继续跑要好得多
             *
             * 解决步骤：
             * 1.结束程序
             * 2.删除 Redis DB 中 blogViewsMap 这个 key（或者直接清空对应的整个 DB）
             * 3.重新启动程序
             *
             * 具体请查看: https://github.com/Naccl/NBlog/issues/58
             */
            int view = (int) redisService.getValueByHashKey(redisKey, blogId);
            blogInfo.setViews(view);
            blogInfos.set(i, blogInfo);
        }
    }

    private List<BlogInfo> processBlogInfosPassword(List<BlogInfo> blogInfos) {
        for (BlogInfo blogInfo : blogInfos) {
            if (!"".equals(blogInfo.getPassword())) {
                blogInfo.setPrivacy(true);
                blogInfo.setPassword("");
                blogInfo.setDescription(PRIVATE_BLOG_DESCRIPTION);
            } else {
                blogInfo.setPrivacy(false);
                blogInfo.setDescription(MarkdownUtils.markdownToHtmlExtensions(blogInfo.getDescription()));
            }
            blogInfo.setTags(tagService.getTagListByBlogId(blogInfo.getId()));
        }
        return blogInfos;
    }





}
