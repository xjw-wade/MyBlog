package top.wade.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.wade.constant.RedisKeyConstants;
import top.wade.constant.SiteSettingConstants;
import top.wade.entity.SiteSetting;
import top.wade.mapper.SiteSettingMapper;
import top.wade.model.vo.Badge;
import top.wade.model.vo.Copyright;
import top.wade.model.vo.Favorite;
import top.wade.model.vo.Introduction;
import top.wade.service.RedisService;
import top.wade.service.SiteSettingService;
import top.wade.util.JacksonUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author xjw
 * @Date 2023/6/17 17:03
 * @Description: 站点设置业务层实现
 */
@Service
public class SiteSettingServiceImpl implements SiteSettingService {
    @Autowired
    SiteSettingMapper siteSettingMapper;
    @Autowired
    RedisService redisService;

    private static final Pattern PATTERN = Pattern.compile("\"(.*?)\""); //（.?）表示一个非贪婪模式的匹配组，它可以匹配长度任意的字符串，但是会尽量少地匹配

    @Override
    public Map<String, Object> getSiteInfo() {
        String redisKey = RedisKeyConstants.SITE_INFO_MAP;
        Map<String, Object> siteInfoMapFromRedis = redisService.getMapByValue(redisKey);
        if (siteInfoMapFromRedis != null) {
            return siteInfoMapFromRedis;
        }
        List<SiteSetting> siteSettings = siteSettingMapper.getList();
        Map<String, Object> siteInfo = new HashMap<>(2);
        List<Badge> badges = new ArrayList<>();
        Introduction introduction = new Introduction();
        List<Favorite> favorites = new ArrayList<>();
        List<String> rollTexts = new ArrayList<>();
        for (SiteSetting s : siteSettings) {
            switch (s.getType()) {
                case 1:
                    if (SiteSettingConstants.COPYRIGHT.equals(s.getNameEn())) {
                        Copyright copyright = JacksonUtils.readValue(s.getValue(), Copyright.class);
                        siteInfo.put(s.getNameEn(), copyright);
                    } else {
                        siteInfo.put(s.getNameEn(), s.getValue());
                    }
                    break;
                case 2:
                    switch (s.getNameEn()) {
                        case SiteSettingConstants.AVATAR:
                            introduction.setAvatar(s.getValue());
                            break;
                        case SiteSettingConstants.NAME:
                            introduction.setName(s.getValue());
                            break;
                        case SiteSettingConstants.GITHUB:
                            introduction.setGithub(s.getValue());
                            break;
                        case SiteSettingConstants.TELEGRAM:
                            introduction.setTelegram(s.getValue());
                            break;
                        case SiteSettingConstants.QQ:
                            introduction.setQq(s.getValue());
                            break;
                        case SiteSettingConstants.BILIBILI:
                            introduction.setBilibili(s.getValue());
                            break;
                        case SiteSettingConstants.NETEASE:
                            introduction.setNetease(s.getValue());
                            break;
                        case SiteSettingConstants.EMAIL:
                            introduction.setEmail(s.getValue());
                            break;
                        case SiteSettingConstants.FAVORITE:
                            Favorite favorite = JacksonUtils.readValue(s.getValue(), Favorite.class);
                            favorites.add(favorite);
                            break;
                        case SiteSettingConstants.ROLL_TEXT:
                            Matcher m = PATTERN.matcher(s.getValue());
                            while (m.find()) {
                                rollTexts.add(m.group(1));
                            }
                            break;
                        default:
                            break;
                    }
                    break;
                case 3:
                    Badge badge = JacksonUtils.readValue(s.getValue(), Badge.class);
                    badges.add(badge);
                    break;
                default:
                    break;
            }
        }
        introduction.setFavorites(favorites);
        introduction.setRollText(rollTexts);
        Map<String, Object> map = new HashMap<>(8);
        map.put("introduction", introduction);
        map.put("siteInfo", siteInfo);
        map.put("badges", badges);
        redisService.saveMapToValue(redisKey, map);
        return map;
    }
}
