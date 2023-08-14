package top.wade.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.wade.entity.User;
import top.wade.model.vo.Result;
import top.wade.service.UserService;

/**
 * @Author xjw
 * @Date 2023/8/10 16:23
 * @Description: 账号后台管理
 */
@RestController
@RequestMapping("/admin")
public class AccountAdminController {
    @Autowired
    UserService userService;

    /**
     * 账号密码修改
     */
    @PostMapping("/account")
    public Result account(@RequestBody User user, @RequestHeader(value = "Authorization", defaultValue = "") String jwt) {
        boolean res = userService.changeAccount(user, jwt);
        return res ? Result.ok("修改成功") : Result.error("修改失败");
    }

}
