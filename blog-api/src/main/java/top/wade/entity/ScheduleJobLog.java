package top.wade.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @Author xjw
 * @Date 2023/8/10 19:01
 * @Description: 定时任务日志
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ScheduleJobLog {
    private Long logId;//日志id
    private Long jobId;//任务id
    private String beanName;//spring bean名称
    private String methodName;//方法名
    private String params;//参数
    private Boolean status;//任务执行结果
    private String error;//异常信息
    private Integer times;//耗时(单位：毫秒)
    private Date createTime;//创建时间
}
