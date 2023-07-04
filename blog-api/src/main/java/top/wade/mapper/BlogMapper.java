package top.wade.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.wade.entity.Blog;
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





}
