package top.wade.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.wade.entity.Blog;
import top.wade.mapper.BlogMapper;
import top.wade.service.BlogService;

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

    @Override
    public List<Blog> getListByTitleAndCategoryId(String title, Integer categoryId) {
        return null;
    }
}
