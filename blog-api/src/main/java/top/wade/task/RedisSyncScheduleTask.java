package top.wade.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.wade.constant.RedisKeyConstants;
import top.wade.service.BlogService;
import top.wade.service.RedisService;

import java.util.Map;
import java.util.Set;

/**
 * @Author xjw
 * @Date 2023/8/12 21:14
 * @Description: Redis相关定时任务
 */
@Component
public class RedisSyncScheduleTask {
    @Autowired
    RedisService redisService;
    @Autowired
    BlogService blogService;

    /**
     * 从Redis同步博客文章浏览量到数据库
     */
    public void syncBlogViewsToDatabase() {
        String redisKey = RedisKeyConstants.BLOG_VIEWS_MAP;
        Map blogViewMap = redisService.getMapByHash(redisKey);
        Set<Integer> keys = blogViewMap.keySet();
        for (Integer key: keys) {
            Integer views = (Integer) blogViewMap.get(key);
            blogService.updateViews(key.longValue(), views);
        }
    }
}
