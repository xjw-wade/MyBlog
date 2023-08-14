package top.wade.util.quartz;

import nl.basjes.shaded.org.springframework.util.ReflectionUtils;
import nl.basjes.shaded.org.springframework.util.StringUtils;
import top.wade.util.common.SpringContextUtils;

import java.lang.reflect.Method;

/**
 * @Author xjw
 * @Date 2023/8/11 16:57
 * @Description: 执行定时任务
 */
public class ScheduleRunnable implements Runnable {
    private Object target;
    private Method method;
    private String params;

    public ScheduleRunnable(String beanName, String methodName, String params) throws NoSuchMethodException, SecurityException {
        this.target = SpringContextUtils.getBean(beanName);
        this.params = params;
        if (StringUtils.hasText(params)) {
            this.method = target.getClass().getDeclaredMethod(methodName, String.class);
        } else {
            this.method = target.getClass().getDeclaredMethod(methodName);
        }
    }

    @Override
    public void run() {
        try {
            ReflectionUtils.makeAccessible(method);
            if (StringUtils.hasText(params)) {
                method.invoke(target, params);
            } else {
                method.invoke(target);
            }

        } catch (Exception e) {
            throw new RuntimeException("执行定时任务失败", e);
        }
    }
}
