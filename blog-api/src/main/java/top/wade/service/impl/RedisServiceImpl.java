package top.wade.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import top.wade.model.vo.BlogInfo;
import top.wade.model.vo.PageResult;
import top.wade.service.RedisService;
import top.wade.util.JacksonUtils;

/**
 * @Author xjw 读写Redis相关操作
 * @Date 2023/6/9 20:28
 * @Description:
 */
@Service
public class RedisServiceImpl implements RedisService {
    @Autowired
    RedisTemplate jsonRedisTemplate;

    @Override
    public PageResult<BlogInfo> getBlogInfoPageResultByHash(String hash, Integer pageNum) {
        if (jsonRedisTemplate.opsForHash().hasKey(hash, pageNum)) {
            Object redisResult = jsonRedisTemplate.opsForHash().get(hash, pageNum);
            PageResult<BlogInfo> pageResult = JacksonUtils.convertValue(redisResult, PageResult.class);
            return pageResult;
        } else {
            return null;
        }
    }

    @Override
    public Object getValueByHashKey(String hash, Object key) {
        return jsonRedisTemplate.opsForHash().get(hash, key);
    }

    @Override
    public void saveKVToHash(String hash, Object key, Object value) {
        jsonRedisTemplate.opsForHash().put(hash, key, value);
    }
}
