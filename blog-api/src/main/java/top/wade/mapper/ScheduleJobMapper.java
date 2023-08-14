package top.wade.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.wade.entity.ScheduleJob;

import java.util.List;

/**
 * @Author xjw
 * @Date 2023/8/10 19:02
 * @Description: 定时任务持久层接口
 */
@Mapper
@Repository
public interface ScheduleJobMapper {
    List<ScheduleJob> getJobList();

    ScheduleJob getJobById(Long jobId);

    int saveJob(ScheduleJob scheduleJob);

    int updateJob(ScheduleJob scheduleJob);

    int deleteJobById(Long jobId);

    int updateJobStatusById(Long jobId, Boolean status);

}
