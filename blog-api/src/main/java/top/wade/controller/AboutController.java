package top.wade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.wade.annotation.VisitLogger;
import top.wade.enums.VisitBehavior;
import top.wade.model.vo.Result;
import top.wade.service.AboutService;

/**
 * @Author xjw
 * @Date 2023/7/20 18:52
 * @Description: 关于我页面
 */
@RestController
public class AboutController {
    @Autowired
    AboutService aboutService;

    /**
     * 获取关于我页面信息
     *
     * @return
     */
    @VisitLogger(VisitBehavior.ABOUT)
    @GetMapping("/about")
    public Result about() {
        return Result.ok("获取成功", aboutService.getAboutInfo());
    }

}
