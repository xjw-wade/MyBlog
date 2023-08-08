package top.wade.service;

import org.springframework.scheduling.annotation.Async;
import top.wade.entity.LoginLog;

/**
 * @Author xjw
 * @Date 2023/7/26 20:58
 * @Description:
 */
public interface LoginLogService {
    @Async
    void saveLoginLog(LoginLog log);
}
