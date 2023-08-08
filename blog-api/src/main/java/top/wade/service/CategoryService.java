package top.wade.service;

import top.wade.entity.Category;

import java.util.List;

/**
 * @Author xjw
 * @Date 2023/6/17 16:26
 * @Description:
 */
public interface CategoryService {
    List<Category> getCategoryNameList();

    List<Category> getCategoryList();

    Category getCategoryById(Long id);

    Category getCategoryByName(String name);

    void saveCategory(Category category);

    void deleteCategoryById(Long id);

    void updateCategory(Category category);
}
