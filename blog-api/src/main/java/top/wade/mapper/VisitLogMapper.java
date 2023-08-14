package top.wade.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.wade.entity.VisitLog;
import top.wade.model.dto.VisitLogUuidTime;

import java.util.List;

/**
 * @Author xjw
 * @Date 2023/6/16 16:15
 * @Description: 访问日志持久层接口
 */
@Mapper
@Repository
public interface VisitLogMapper {
    int saveVisitLog(VisitLog log);

    int countVisitLogByToday();

    List<VisitLogUuidTime> getUUIDAndCreateTimeByYesterday();

    List<VisitLog> getVisitLogListByUUIDAndDate(String uuid, String startDate, String endDate);

    int deleteVisitLogById(Long id);

}
