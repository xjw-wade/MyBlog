package top.wade.service;

import top.wade.entity.ScheduleJob;
import top.wade.entity.ScheduleJobLog;

import java.util.List;

/**
 * @Author xjw
 * @Date 2023/8/10 19:46
 * @Description:
 */
public interface ScheduleJobService {
    List<ScheduleJob> getJobList();

    void saveJob(ScheduleJob scheduleJob);

    void updateJob(ScheduleJob scheduleJob);

    void deleteJobById(Long jobId);

    void runJobById(Long jobId);

    void updateJobStatusById(Long jobId, Boolean status);

    List<ScheduleJobLog> getJobLogListByDate(String startDate, String endDate);

    void saveJobLog(ScheduleJobLog log);

    void deleteJobLogByLogId(Long logId);
}
