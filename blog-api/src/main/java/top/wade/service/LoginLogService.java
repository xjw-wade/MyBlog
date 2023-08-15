package top.wade.service;

import org.springframework.scheduling.annotation.Async;
import top.wade.entity.LoginLog;

import java.util.List;

/**
 * @Author xjw
 * @Date 2023/7/26 20:58
 * @Description:
 */
public interface LoginLogService {
    @Async
    void saveLoginLog(LoginLog log);

    List<LoginLog> getLoginLogListByDate(String startDate, String endDate);

    void deleteLoginLogById(Long id);
}
