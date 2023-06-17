package top.wade.service;

import top.wade.model.vo.BlogInfo;
import top.wade.model.vo.PageResult;

/**
 * @Author xjw
 * @Date 2023/6/9 20:27
 * @Description:
 */
public interface RedisService {
    PageResult<BlogInfo> getBlogInfoPageResultByHash(String hash, Integer pageNum);

    Object getValueByHashKey(String hash, Object key);

    void saveKVToHash(String hash, Object key, Object value);

    boolean hasValueInSet(String key, Object value);

    void saveValueToSet(String key, Object value);

    <T> T getObjectByValue(String key, Class t);

    void incrementByKey(String key, int increment);

    void expire(String key, long time);

}
