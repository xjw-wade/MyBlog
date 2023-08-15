package top.wade.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.wade.entity.LoginLog;
import top.wade.exception.PersistenceException;
import top.wade.mapper.LoginLogMapper;
import top.wade.model.dto.UserAgentDTO;
import top.wade.service.LoginLogService;
import top.wade.util.IpAddressUtils;
import top.wade.util.UserAgentUtils;

import java.util.List;

/**
 * @Author xjw
 * @Date 2023/7/27 12:19
 * @Description: 登录日志业务层实现
 */
@Service
public class LoginLogServiceImpl implements LoginLogService {
    @Autowired
    LoginLogMapper loginLogMapper;
    @Autowired
    UserAgentUtils userAgentUtils;



    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveLoginLog(LoginLog log) {
        String ipSource = IpAddressUtils.getCityInfo(log.getIp());
        UserAgentDTO userAgentDTO = userAgentUtils.parseOsAndBrowser(log.getUserAgent());
        log.setIpSource(ipSource);
        log.setOs(userAgentDTO.getOs());
        log.setBrowser(userAgentDTO.getBrowser());
        if (loginLogMapper.saveLoginLog(log) != 1) {
            throw new PersistenceException("日志添加失败");
        }
    }

    @Override
    public List<LoginLog> getLoginLogListByDate(String startDate, String endDate) {
        return loginLogMapper.getLoginLogListByDate(startDate, endDate);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteLoginLogById(Long id) {
        if (loginLogMapper.deleteLoginLogById(id) != 1) {
            throw new PersistenceException("删除日志失败");
        }
    }
}
