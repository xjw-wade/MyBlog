package top.wade.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.wade.service.VisitLogService;

/**
 * @Author xjw
 * @Date 2023/6/15 12:29
 * @Description: AOP记录访问日志
 */
@Component
@Aspect
public class VisitLogAspect {
    @Autowired
    VisitLogService visitLogService;
}
