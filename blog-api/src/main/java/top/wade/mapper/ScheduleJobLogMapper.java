package top.wade.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.wade.entity.ScheduleJobLog;

import java.util.List;

/**
 * @Author xjw
 * @Date 2023/8/10 19:03
 * @Description: 定时任务日志持久层接口
 */
@Mapper
@Repository
public interface ScheduleJobLogMapper {
    List<ScheduleJobLog> getJobLogListByDate(String startDate, String endDate);

    int saveJobLog(ScheduleJobLog jobLog);

    int deleteJobLogByLogId(Long logId);
}
