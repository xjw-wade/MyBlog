package top.wade.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.wade.annotation.OperationLogger;
import top.wade.model.vo.Result;
import top.wade.service.AboutService;

import java.util.Map;

/**
 * @Author xjw
 * @Date 2023/8/8 21:31
 * @Description: 关于我页面后台管理
 */
@RestController
@RequestMapping("/admin")
public class AboutAdminController {
    @Autowired
    AboutService aboutService;

    /**
     * 获取关于我页面配置
     *
     * @return
     */
    @GetMapping("/about")
    public Result about() {
        return Result.ok("请求成功", aboutService.getAboutSetting());
    }

    /**
     * 修改关于我页面
     *
     * @param map
     * @return
     */
    @OperationLogger("修改关于我页面")
    @PutMapping("/about")
    public Result updateAbout(@RequestBody Map<String, String> map) {
        aboutService.updateAbout(map);
        return Result.ok("修改成功");
    }
}
