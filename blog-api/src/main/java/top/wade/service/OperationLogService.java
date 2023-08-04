package top.wade.service;

import top.wade.entity.OperationLog;

/**
 * @Author xjw
 * @Date 2023/8/4 20:44
 * @Description:
 */
public interface OperationLogService {
    void saveOperationLog(OperationLog log);
}
