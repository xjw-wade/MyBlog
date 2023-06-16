package top.wade.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.wade.entity.Tag;

import java.util.List;

/**
 * @Author xjw
 * @Date 2023/6/13 13:21
 * @Description: 博客标签持久层接口
 */
@Mapper
@Repository
public interface TagMapper {
    List<Tag> getTagListByBlogId(Long blogId);
}
