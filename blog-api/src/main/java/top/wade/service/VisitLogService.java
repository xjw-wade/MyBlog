package top.wade.service;

import org.springframework.scheduling.annotation.Async;
import top.wade.entity.VisitLog;

/**
 * @Author xjw
 * @Date 2023/6/15 12:35
 * @Description:
 */
public interface VisitLogService {
    @Async
    void saveVisitLog(VisitLog log);
}
