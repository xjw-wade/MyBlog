package top.wade.service;

import org.springframework.scheduling.annotation.Async;
import top.wade.entity.ExceptionLog;

import java.util.List;

/**
 * @Author xjw
 * @Date 2023/8/15 19:57
 * @Description:
 */
public interface ExceptionLogService {
    List<ExceptionLog> getExceptionLogListByDate(String startDate, String endDate);

    @Async
    void saveExceptionLog(ExceptionLog log);

    void deleteExceptionLogById(Long id);
}
