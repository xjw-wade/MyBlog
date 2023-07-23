package top.wade.service;

import top.wade.entity.Blog;
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

}
