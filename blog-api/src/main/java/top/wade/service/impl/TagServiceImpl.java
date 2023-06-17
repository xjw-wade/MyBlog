package top.wade.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.wade.constant.RedisKeyConstants;
import top.wade.entity.Tag;
import top.wade.mapper.TagMapper;
import top.wade.service.RedisService;
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
    @Autowired
    RedisService redisService;

    @Override
    public List<Tag> getTagListByBlogId(Long blogId) {
        return tagMapper.getTagListByBlogId(blogId);
    }

    @Override
    public List<Tag> getTagListNotId() {
        String redisKey = RedisKeyConstants.TAG_CLOUD_LIST;
        List<Tag> tagListFromRedis = redisService.getListByValue(redisKey);
        if (tagListFromRedis != null) {
            return tagListFromRedis;
        }
        List<Tag> tagList = tagMapper.getTagListNotId();
        redisService.saveListToValue(redisKey, tagList);
        return tagList;
    }
}
