package top.wade.service;

import org.springframework.scheduling.annotation.Async;
import top.wade.entity.Visitor;

/**
 * @Author xjw
 * @Date 2023/6/16 13:04
 * @Description:
 */
public interface VisitorService {
    boolean hasUUID(String uuid);

    @Async
    void saveVisitor(Visitor visitor);
}
