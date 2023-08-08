package top.wade.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.wade.entity.Category;

import java.util.List;

/**
 * @Author xjw
 * @Date 2023/6/17 18:38
 * @Description: 博客分类持久层接口
 */
@Mapper
@Repository
public interface CategoryMapper {
    List<Category> getCategoryNameList();

    List<Category> getCategoryList();

    Category getCategoryById(Long id);

    Category getCategoryByName(String name);

    int saveCategory(Category category);

    int deleteCategoryById(Long id);

    int updateCategory(Category category);
}
