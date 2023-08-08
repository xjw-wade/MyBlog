package top.wade.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.wade.entity.Blog;
import top.wade.model.dto.BlogVisibility;
import top.wade.model.vo.*;

import java.util.List;

/**
 * @Author xjw
 * @Date 2023/6/7 17:05
 * @Description: 博客文章持久层接口
 */
@Mapper
@Repository
public interface BlogMapper {
    List<Blog> getListByTitleAndCategoryId(String title, Integer categoryId);

    List<SearchBlog> getSearchBlogListByQueryAndIsPublished(String query);

    List<BlogIdAndTitle> getIdAndTitleList();

    List<BlogInfo> getBlogInfoListByIsPublished();

    List<NewBlog> getNewBlogListByIsPublished();

    List<RandomBlog> getRandomBlogListByLimitNumAndIsPublishedAndIsRecommend(Integer limitNum);

    List<BlogInfo> getBlogInfoListByCategoryNameAndIsPublished(String categoryName);

    List<String> getGroupYearMonthByIsPublished();

    List<ArchiveBlog> getArchiveBlogListByYearMonthAndIsPublished(String yearMonth);

    int countBlogByIsPublished();

    Boolean getCommentEnabledByBlogId(Long blogId);

    Boolean getPublishedByBlogId(Long blogId);

    String getBlogPassword(Long blogId);

    String getTitleByBlogId(Long blogId);

    int countBlog();

    List<CategoryBlogCount> getCategoryBlogCountList();

    int saveBlog(top.wade.model.dto.Blog blog);

    int saveBlogTag(Long blogId, Long tagId);

    int updateBlog(top.wade.model.dto.Blog blog);

    int deleteBlogTagByBlogId(Long blogId);

    Blog getBlogById(Long id);

    int deleteBlogById(Long id);

    int updateBlogRecommendById(Long blogId, Boolean recommend);

    int updateBlogVisibilityById(Long blogId, BlogVisibility bv);

    int updateBlogTopById(Long blogId, Boolean top);

    int countBlogByCategoryId(Long categoryId);

    int countBlogByTagId(Long tagId);


}
