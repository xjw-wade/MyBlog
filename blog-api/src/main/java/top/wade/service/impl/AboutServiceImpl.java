package top.wade.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.wade.constant.RedisKeyConstants;
import top.wade.entity.About;
import top.wade.mapper.AboutMapper;
import top.wade.service.AboutService;
import top.wade.service.RedisService;
import top.wade.util.markdown.MarkdownUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
}
