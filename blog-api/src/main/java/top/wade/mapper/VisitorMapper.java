package top.wade.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.wade.entity.Visitor;
import top.wade.model.dto.VisitLogUuidTime;

import java.util.List;

/**
 * @Author xjw
 * @Date 2023/6/16 13:07
 * @Description: 访客统计持久层接口
 */
@Mapper
@Repository
public interface VisitorMapper {
    int hasUUID(String uuid);

    int saveVisitor(Visitor visitor);

    int updatePVAndLastTimeByUUID(VisitLogUuidTime dto);

    List<Visitor> getVisitorListByDate(String startDate, String endDate);

    List<String> getNewVisitorIpSourceByYesterday();

    int deleteVisitorById(Long id);
}
