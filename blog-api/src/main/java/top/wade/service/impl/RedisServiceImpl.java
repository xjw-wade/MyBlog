package top.wade.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import top.wade.model.vo.BlogInfo;
import top.wade.model.vo.PageResult;
import top.wade.service.RedisService;
import top.wade.util.JacksonUtils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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

    @Override
    public boolean hasValueInSet(String key, Object value) {
        return jsonRedisTemplate.opsForSet().isMember(key, value);
    }

    @Override
    public void saveValueToSet(String key, Object value) {
        jsonRedisTemplate.opsForSet().add(key, value);
    }

    @Override
    public <T> T getObjectByValue(String key, Class t) {
        Object redisResult = jsonRedisTemplate.opsForValue().get(key);
        T object = (T) JacksonUtils.convertValue(redisResult, t);
        return object;
    }

    @Override
    public void incrementByKey(String key, int increment) {
        if (increment < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }
        jsonRedisTemplate.opsForValue().increment(key, increment);
    }

    @Override
    public void expire(String key, long time) {
        jsonRedisTemplate.expire(key, time, TimeUnit.SECONDS);
    }

    @Override
    public <T> Map<String, T> getMapByValue(String key) {
        Map<String, T> redisResult = (Map<String, T>) jsonRedisTemplate.opsForValue().get(key);
        return redisResult;
    }

    @Override
    public <T> void saveMapToValue(String key, Map<String, T> map) {
        jsonRedisTemplate.opsForValue().set(key, map);
    }

    @Override
    public <T> List<T> getListByValue(String key) {
        List<T> redisResult = (List<T>) jsonRedisTemplate.opsForValue().get(key);
        return  redisResult;
    }

    @Override
    public <T> void saveListToValue(String key, List<T> list) {
        jsonRedisTemplate.opsForValue().set(key, list);
    }
}
