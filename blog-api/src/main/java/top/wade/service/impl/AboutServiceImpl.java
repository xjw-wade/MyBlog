package top.wade.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.wade.constant.RedisKeyConstants;
import top.wade.entity.About;
import top.wade.exception.PersistenceException;
import top.wade.mapper.AboutMapper;
import top.wade.service.AboutService;
import top.wade.service.RedisService;
import top.wade.util.markdown.MarkdownUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author xjw
 * @Date 2023/7/20 19:07
 * @Description: 关于我页面业务层实现
 */
@Service
public class AboutServiceImpl implements AboutService {
    @Autowired
    RedisService redisService;
    @Autowired
    AboutMapper aboutMapper;


    @Override
    public Map<String, String> getAboutInfo() {
        String redisKey = RedisKeyConstants.ABOUT_INFO_MAP;
        Map<String, String> aboutInfoMapFromRedis = redisService.getMapByValue(redisKey);
        if (aboutInfoMapFromRedis != null) {
            return aboutInfoMapFromRedis;
        }
        List<About> abouts = aboutMapper.getList();
        Map<String, String> aboutInfoMap = new HashMap<>(16);
        for (About about: abouts) {
            if ("content".equals(about.getNameEn())) {
                about.setValue(MarkdownUtils.markdownToHtmlExtensions(about.getValue()));
            }
            aboutInfoMap.put(about.getNameEn(), about.getValue());
        }
        redisService.saveMapToValue(redisKey, aboutInfoMap);
        return aboutInfoMap;
    }

    @Override
    public boolean getAboutCommentEnabled() {
        String commentEnabledString = aboutMapper.getAboutCommentEnabled();
        return Boolean.parseBoolean(commentEnabledString);
    }

    @Override
    public Map<String, String> getAboutSetting() {
        List<About> abouts = aboutMapper.getList();
        Map<String, String> map = new HashMap<>(16);
        for (About about : abouts) {
            map.put(about.getNameEn(), about.getValue());
        }
        return map;
    }

    @Override
    public void updateAbout(Map<String, String> map) {
        Set<String> keySet = map.keySet();
        for (String key: keySet) {
            updateOneAbout(key, map.get(key));
        }
        deleteAboutRedisCache();
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateOneAbout(String nameEn, String value) {
        if (aboutMapper.updateAbout(nameEn, value) != 1) {
            throw new PersistenceException("修改失败");
        }
    }

    /**
     * 删除关于我页面缓存
     */
    private void deleteAboutRedisCache() {
        redisService.deleteCacheByKey(RedisKeyConstants.ABOUT_INFO_MAP);
    }
}
