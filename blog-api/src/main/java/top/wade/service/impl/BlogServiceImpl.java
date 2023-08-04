package top.wade.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.wade.constant.RedisKeyConstants;
import top.wade.entity.Blog;
import top.wade.exception.NotFoundException;
import top.wade.mapper.BlogMapper;
import top.wade.model.vo.*;
import top.wade.service.BlogService;
import top.wade.service.RedisService;
import top.wade.service.TagService;
import top.wade.util.JacksonUtils;
import top.wade.util.markdown.MarkdownUtils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
        //redis已有当前页缓存
        PageResult<BlogInfo> pageResultFromRedis = redisService.getBlogInfoPageResultByHash(redisKey, pageNum);
        if (pageResultFromRedis != null) {
            setBlogViewsFromRedisToPageResult(pageResultFromRedis);
            return pageResultFromRedis;
        }
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

    @Override
    public List<NewBlog> getNewBlogListByIsPublished() {
        String redisKey = RedisKeyConstants.NEW_BLOG_LIST;
        List<NewBlog> newBlogListFromRedis = redisService.getListByValue(redisKey);
        if (newBlogListFromRedis != null) {
            return newBlogListFromRedis;
        }
        PageHelper.startPage(1, newBlogPageSize);
        List<NewBlog> newBlogList = blogMapper.getNewBlogListByIsPublished();
        for (NewBlog newBlog : newBlogList) {
            if (!"".equals(newBlog.getPassword())) {
                newBlog.setPrivacy(true);
                newBlog.setPassword("");
            } else {
                newBlog.setPrivacy(false);
            }
        }
        redisService.saveListToValue(redisKey, newBlogList);
        return newBlogList;
    }

    @Override
    public List<RandomBlog> getRandomBlogListByLimitNumAndIsPublishedAndIsRecommend() {
        List<RandomBlog> randomBlogs = blogMapper.getRandomBlogListByLimitNumAndIsPublishedAndIsRecommend(randomBlogLimitNum);
        for (RandomBlog randomBlog : randomBlogs) {
            if (!"".equals(randomBlog.getPassword())) {
                randomBlog.setPrivacy(true);
                randomBlog.setPassword("");
            } else {
                randomBlog.setPrivacy(false);
            }
        }
        return randomBlogs;
    }

    @Override
    public PageResult<BlogInfo> getBlogInfoListByCategoryNameAndIsPublished(String categoryName, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize, orderBy);
        List<BlogInfo> blogInfos = processBlogInfosPassword(blogMapper.getBlogInfoListByCategoryNameAndIsPublished(categoryName));
        PageInfo<BlogInfo> pageInfo = new PageInfo<>(blogInfos);
        PageResult<BlogInfo> pageResult = new PageResult<>(pageInfo.getPages(), pageInfo.getList());
        setBlogViewsFromRedisToPageResult(pageResult);
        return pageResult;
    }

    @Override
    public Map<String, Object> getArchiveBlogAndCountByIsPublished() {
        String redisKey = RedisKeyConstants.ARCHIVE_BLOG_MAP;
        Map<String, Object> mapFromRedis = redisService.getMapByValue(redisKey);
        if (mapFromRedis != null) {
            return mapFromRedis;
        }
        List<String> groupYearMonth = blogMapper.getGroupYearMonthByIsPublished();
        Map<String, List<ArchiveBlog>> archiveBlogMap = new LinkedHashMap<>();
        for (String s: groupYearMonth) {
            List<ArchiveBlog> archiveBlogs = blogMapper.getArchiveBlogListByYearMonthAndIsPublished(s);
            for (ArchiveBlog archiveBlog: archiveBlogs) {
                if (!"".equals(archiveBlog.getPassword())) {
                    archiveBlog.setPrivacy(true);
                    archiveBlog.setPassword("");
                } else {
                    archiveBlog.setPrivacy(false);
                }
            }
            archiveBlogMap.put(s, archiveBlogs);
        }
        Integer count = countBlogByIsPublished();
        Map<String, Object> map = new HashMap<>(4);
        map.put("blogMap", archiveBlogMap);
        map.put("count", count);
        redisService.saveMapToValue(redisKey, map);
        return map;
    }

    @Override
    public int countBlogByIsPublished() {
        return blogMapper.countBlogByIsPublished();
    }

    @Override
    public Boolean getCommentEnabledByBlogId(Long blogId) {
        return blogMapper.getCommentEnabledByBlogId(blogId);
    }

    @Override
    public Boolean getPublishedByBlogId(Long blogId) {
        return blogMapper.getPublishedByBlogId(blogId);
    }

    @Override
    public String getBlogPassword(Long blogId) {
        return blogMapper.getBlogPassword(blogId);
    }

    @Override
    public String getTitleByBlogId(Long blogId) {
        return blogMapper.getTitleByBlogId(blogId);
    }

    @Override
    public List<SearchBlog> getSearchBlogListByQueryAndIsPublished(String query) {
        List<SearchBlog> searchBlogs = blogMapper.getSearchBlogListByQueryAndIsPublished(query);
        for (SearchBlog searchBlog : searchBlogs) {
            String content = searchBlog.getContent();
            int contentLength = content.length();
            int index = content.indexOf(query) - 10;
            index = index < 0 ? 0 : index;
            int end = index + 21;//以关键字字符串为中心返回21个字
            end = end > contentLength ? contentLength : end;
            searchBlog.setContent(content.substring(index, end));
        }
        return searchBlogs;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveBlog(top.wade.model.dto.Blog blog) {
        if (blogMapper.saveBlog(blog) != 1) {
            throw new PersistenceException("添加博客失败");
        }
        redisService.saveKVToHash(RedisKeyConstants.BLOG_VIEWS_MAP, blog.getId(), 0);
        deleteBlogRedisCache();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveBlogTag(Long blogId, Long tagId) {
        if (blogMapper.saveBlogTag(blogId, tagId) != 1) {
            throw new PersistenceException("维护博客标签关联表失败");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateBlog(top.wade.model.dto.Blog blog) {
        if (blogMapper.updateBlog(blog) != 1) {
            throw new PersistenceException("更新博客失败");
        }
        deleteBlogRedisCache();
        redisService.saveKVToHash(RedisKeyConstants.BLOG_VIEWS_MAP, blog.getId(), blog.getViews());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteBlogTagByBlogId(Long blogId) {
        if (blogMapper.deleteBlogTagByBlogId(blogId) == 0) {
            throw new PersistenceException("维护博客标签关联表失败");
        }
    }

    @Override
    public Blog getBlogById(Long id) {
        Blog blog = blogMapper.getBlogById(id);
        if (blog == null) {
            throw new NotFoundException("博客不存在");
        }

        int view = (int) redisService.getValueByHashKey(RedisKeyConstants.BLOG_VIEWS_MAP, blog.getId());
        blog.setViews(view);
        return blog;
    }

    /**
     * 删除首页缓存、最新推荐缓存、归档页面缓存、博客浏览量缓存
     */
    private void deleteBlogRedisCache() {
        redisService.deleteCacheByKey(RedisKeyConstants.HOME_BLOG_INFO_LIST);
        redisService.deleteCacheByKey(RedisKeyConstants.NEW_BLOG_LIST);
        redisService.deleteCacheByKey(RedisKeyConstants.ARCHIVE_BLOG_MAP);
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
        for (BlogInfo blogInfo: blogInfos) {
            if (! "".equals(blogInfo.getPassword())) {
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
