package top.wade.service;

import org.springframework.scheduling.annotation.Async;
import top.wade.entity.VisitLog;
import top.wade.model.dto.VisitLogUuidTime;

import java.util.List;

/**
 * @Author xjw
 * @Date 2023/6/15 12:35
 * @Description:
 */
public interface VisitLogService {
    @Async
    void saveVisitLog(VisitLog log);

    List<VisitLogUuidTime> getUUIDAndCreateTimeByYesterday();

    List<VisitLog> getVisitLogListByUUIDAndDate(String uuid, String startDate, String endDate);

    void deleteVisitLogById(Long id);
}
