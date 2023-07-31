package top.wade.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.wade.constant.RedisKeyConstants;
import top.wade.entity.CityVisitor;
import top.wade.model.vo.Result;
import top.wade.service.DashboardService;
import top.wade.service.RedisService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author xjw
 * @Date 2023/7/31 15:45
 * @Description: 后台管理仪表盘
 */
@RestController
@RequestMapping("/admin")
public class DashboardAdminController {
    @Autowired
    DashboardService dashboardService;
    @Autowired
    RedisService redisService;

    @GetMapping("/dashboard")
    public Result dashboard() {
        int todayPV = dashboardService.countVisitLogByToday();
        int todayUV = redisService.countBySet(RedisKeyConstants.IDENTIFICATION_SET);
        int blogCount = dashboardService.getBlogCount();
        int commentCount = dashboardService.getCommentCount();
        Map<String, List> categoryBlogCountMap = dashboardService.getCategoryBlogCountMap();
        Map<String, List> tagBlogCountMap = dashboardService.getTagBlogCountMap();
        Map<String, List> visitRecordMap = dashboardService.getVisitRecordMap();
        List<CityVisitor> cityVisitorList = dashboardService.getCityVisitorList();

        Map<String, Object> map = new HashMap<>(16);
        map.put("pv", todayPV);
        map.put("uv", todayUV);
        map.put("blogCount", blogCount);
        map.put("commentCount", commentCount);
        map.put("category", categoryBlogCountMap);
        map.put("tag", tagBlogCountMap);
        map.put("visitRecord", visitRecordMap);
        map.put("cityVisitor", cityVisitorList);
        return Result.ok("获取成功", map);
    }

}
