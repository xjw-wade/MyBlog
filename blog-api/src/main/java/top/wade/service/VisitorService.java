package top.wade.service;

import org.springframework.scheduling.annotation.Async;
import top.wade.entity.Visitor;
import top.wade.model.dto.VisitLogUuidTime;

import java.util.List;

/**
 * @Author xjw
 * @Date 2023/6/16 13:04
 * @Description:
 */
public interface VisitorService {
    boolean hasUUID(String uuid);

    @Async
    void saveVisitor(Visitor visitor);

    void updatePVAndLastTimeByUUID(VisitLogUuidTime dto);

    void deleteVisitor(Long id, String uuid);

    List<Visitor> getVisitorListByDate(String startDate, String endDate);

    List<String> getNewVisitorIpSourceByYesterday();
}
