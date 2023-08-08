package top.wade.service;

import top.wade.entity.Tag;

import java.util.List;

/**
 * @Author xjw
 * @Date 2023/6/13 13:19
 * @Description:
 */
public interface TagService {
    List<Tag> getTagListByBlogId(Long blogId);

    List<Tag> getTagListNotId();

    List<Tag> getTagList();

    Tag getTagById(Long id);

    Tag getTagByName(String name);

    void saveTag(Tag tag);

    void deleteTagById(Long id);

    void updateTag(Tag tag);

}
