package top.wade.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.wade.entity.Tag;
import top.wade.model.vo.TagBlogCount;

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

    List<Tag> getTagListNotId();

    List<TagBlogCount> getTagBlogCount();

    List<Tag> getTagList();

    Tag getTagById(Long id);

    Tag getTagByName(String name);

    int saveTag(Tag tag);

    int deleteTagById(Long id);

    int updateTag(Tag tag);
}
