package top.wade.service;

import top.wade.entity.Blog;
import top.wade.model.vo.BlogInfo;
import top.wade.model.vo.PageResult;

import java.util.List;

/**
 * @Author xjw
 * @Date 2023/6/9 16:13
 * @Description:
 */
public interface BlogService {
    List<Blog> getListByTitleAndCategoryId(String title, Integer categoryId);

    PageResult<BlogInfo> getBlogInfoListByIsPublished(Integer pageNum);
}
