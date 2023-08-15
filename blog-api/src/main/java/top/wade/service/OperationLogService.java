package top.wade.service;

import top.wade.entity.OperationLog;

import java.util.List;

/**
 * @Author xjw
 * @Date 2023/8/4 20:44
 * @Description:
 */
public interface OperationLogService {
    void saveOperationLog(OperationLog log);

    List<OperationLog> getOperationLogListByDate(String startDate, String endDate);

    void deleteOperationLogById(Long id);
}
