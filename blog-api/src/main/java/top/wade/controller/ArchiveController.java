package top.wade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.wade.annotation.VisitLogger;
import top.wade.enums.VisitBehavior;
import top.wade.model.vo.Result;
import top.wade.service.BlogService;

import java.util.Map;

/**
 * @Author xjw
 * @Date 2023/7/4 17:12
 * @Description: 归档页面
 */
@RestController
public class ArchiveController {
    @Autowired
    BlogService blogService;

    /**
     * 按年月分组归档公开博客 统计公开博客总数
     *
     * @return
     */
    @VisitLogger(VisitBehavior.ARCHIVE)
    @GetMapping("/archives")
    public Result archives() {
        Map<String, Object> archiveBlogMap = blogService.getArchiveBlogAndCountByIsPublished();
        return Result.ok("请求成功", archiveBlogMap);
    }
}
