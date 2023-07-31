package top.wade.service;

import top.wade.entity.CityVisitor;

import java.util.List;
import java.util.Map;

/**
 * @Author xjw
 * @Date 2023/7/31 15:46
 * @Description:
 */
public interface DashboardService {
    int countVisitLogByToday();

    int getBlogCount();

    int getCommentCount();

    Map<String, List> getCategoryBlogCountMap();

    Map<String, List> getTagBlogCountMap();

    Map<String, List> getVisitRecordMap();

    List<CityVisitor> getCityVisitorList();

}
