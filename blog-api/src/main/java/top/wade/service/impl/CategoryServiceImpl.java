package top.wade.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.wade.constant.RedisKeyConstants;
import top.wade.entity.Category;
import top.wade.mapper.CategoryMapper;
import top.wade.service.CategoryService;
import top.wade.service.RedisService;
import top.wade.service.TagService;

import java.util.List;

/**
 * @Author xjw
 * @Date 2023/6/17 18:36
 * @Description: 博客分类业务层实现
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    TagService tagService;
    @Autowired
    RedisService redisService;

    @Override
    public List<Category> getCategoryNameList() {
        String redisKey = RedisKeyConstants.CATEGORY_NAME_LIST;
        List<Category> categoryListFromRedis = redisService.getListByValue(redisKey);
        if (categoryListFromRedis != null) {
            return categoryListFromRedis;
        }
        List<Category> categoryList = categoryMapper.getCategoryNameList();
        redisService.saveListToValue(redisKey, categoryList);
        return categoryList;
    }
}
