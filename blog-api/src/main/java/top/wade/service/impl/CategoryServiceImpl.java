package top.wade.service.impl;

import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.wade.constant.RedisKeyConstants;
import top.wade.entity.Category;
import top.wade.exception.NotFoundException;
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

    @Override
    public List<Category> getCategoryList() {
        return categoryMapper.getCategoryList();
    }

    @Override
    public Category getCategoryById(Long id) {
        Category category = categoryMapper.getCategoryById(id);
        if (category == null) {
            throw new NotFoundException("分类不存在");
        }
        return category;
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryMapper.getCategoryByName(name);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveCategory(Category category) {
        if (categoryMapper.saveCategory(category) != 1) {
            throw new PersistenceException("分类添加失败");
        }
        redisService.deleteCacheByKey(RedisKeyConstants.CATEGORY_NAME_LIST);
    }
}
