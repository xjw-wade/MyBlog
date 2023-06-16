package top.wade.annotation;

import top.wade.enums.VisitBehavior;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author xjw
 * @Date 2023/6/13 11:50
 * @Description: 用于需要记录访客访问日志的方法
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface VisitLogger {
    /**
     * 访问行为枚举
     */
    VisitBehavior value() default VisitBehavior.UNKNOWN;
}
