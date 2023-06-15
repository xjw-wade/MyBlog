package top.wade.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.wade.entity.Blog;
import top.wade.model.vo.BlogIdAndTitle;
import top.wade.model.vo.BlogInfo;
import top.wade.model.vo.NewBlog;
import top.wade.model.vo.SearchBlog;

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





}
