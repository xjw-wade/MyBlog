package top.wade.service;

import top.wade.entity.SiteSetting;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author xjw
 * @Date 2023/6/17 16:26
 * @Description:
 */
public interface SiteSettingService {
    Map<String, Object> getSiteInfo();

    Map<String, List<SiteSetting>> getList();

    String getWebTitleSuffix();

    void updateSiteSetting(List<LinkedHashMap> siteSettings, List<Integer> deleteIds);
}
