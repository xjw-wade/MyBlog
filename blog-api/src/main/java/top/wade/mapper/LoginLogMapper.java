package top.wade.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.wade.entity.LoginLog;

import java.util.List;

/**
 * @Author xjw
 * @Date 2023/7/27 12:20
 * @Description: 登录日志持久层接口
 */
@Mapper
@Repository
public interface LoginLogMapper {
    int saveLoginLog(LoginLog log);

    List<LoginLog> getLoginLogListByDate(String startDate, String endDate);

    int deleteLoginLogById(Long id);
}
