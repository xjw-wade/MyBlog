package top.wade.service;

import java.util.Map;

/**
 * @Author xjw
 * @Date 2023/7/20 18:53
 * @Description:
 */
public interface AboutService {
    Map<String, String> getAboutInfo();

    boolean getAboutCommentEnabled();

    Map<String, String> getAboutSetting();

    void updateAbout(Map<String, String> map);
}
