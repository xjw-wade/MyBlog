package top.wade.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.wade.entity.VisitRecord;

import java.util.List;

/**
 * @Author xjw
 * @Date 2023/7/31 17:53
 * @Description: 访问记录持久层接口
 */
@Mapper
@Repository
public interface VisitRecordMapper {
    List<VisitRecord> getVisitRecordListByLimit(Integer limit);

    int saveVisitRecord(VisitRecord visitRecord);
}
