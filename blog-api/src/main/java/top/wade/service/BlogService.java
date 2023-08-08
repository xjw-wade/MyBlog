package top.wade.service;

import top.wade.entity.Blog;
import top.wade.model.dto.BlogVisibility;
import top.wade.model.vo.*;

import java.util.List;
import java.util.Map;

/**
 * @Author xjw
 * @Date 2023/6/9 16:13
 * @Description:
 */
public interface BlogService {
    List<Blog> getListByTitleAndCategoryId(String title, Integer categoryId);

    PageResult<BlogInfo> getBlogInfoListByIsPublished(Integer pageNum);

    List<NewBlog> getNewBlogListByIsPublished();

    List<RandomBlog> getRandomBlogListByLimitNumAndIsPublishedAndIsRecommend();

    PageResult<BlogInfo> getBlogInfoListByCategoryNameAndIsPublished(String categoryName, Integer pageNum);

    Map<String, Object> getArchiveBlogAndCountByIsPublished();

    int countBlogByIsPublished();

    Boolean getCommentEnabledByBlogId(Long blogId);

    Boolean getPublishedByBlogId(Long blogId);

    String getBlogPassword(Long blogId);

    String getTitleByBlogId(Long blogId);

    List<SearchBlog> getSearchBlogListByQueryAndIsPublished(String query);

    void saveBlog(top.wade.model.dto.Blog blog);

    void saveBlogTag(Long blogId, Long tagId);

    void updateBlog(top.wade.model.dto.Blog blog);

    void deleteBlogTagByBlogId(Long blogId);

    Blog getBlogById(Long id);

    void deleteBlogById(Long id);

    void updateBlogRecommendById(Long blogId, Boolean recommend);

    void updateBlogVisibilityById(Long blogId, BlogVisibility blogVisibility);

    void updateBlogTopById(Long blogId, Boolean top);

    int countBlogByCategoryId(Long categoryId);

    int countBlogByTagId(Long tagId);

}
