package top.wade.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.wade.entity.Tag;
import top.wade.mapper.TagMapper;
import top.wade.service.TagService;

import java.util.List;

/**
 * @Author xjw
 * @Date 2023/6/13 13:20
 * @Description:
 */
@Service
public class TagServiceImpl implements TagService {
    @Autowired
    TagMapper tagMapper;

    @Override
    public List<Tag> getTagListByBlogId(Long blogId) {
        return tagMapper.getTagListByBlogId(blogId);
    }
}
