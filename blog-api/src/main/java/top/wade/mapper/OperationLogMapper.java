package top.wade.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.wade.entity.OperationLog;

/**
 * @Author xjw
 * @Date 2023/8/4 21:01
 * @Description: 操作日志持久层接口
 */
@Mapper
@Repository
public interface OperationLogMapper {
    int saveOperationLog(OperationLog log);

}
