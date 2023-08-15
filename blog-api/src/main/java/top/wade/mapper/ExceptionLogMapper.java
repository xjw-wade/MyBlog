package top.wade.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.wade.entity.ExceptionLog;

import java.util.List;

/**
 * @Author xjw
 * @Date 2023/8/15 19:59
 * @Description: 异常日志持久层接口
 */
@Mapper
@Repository
public interface ExceptionLogMapper {
    List<ExceptionLog> getExceptionLogListByDate(String startDate, String endDate);

    int saveExceptionLog(ExceptionLog log);

    int deleteExceptionLogById(Long id);
}
