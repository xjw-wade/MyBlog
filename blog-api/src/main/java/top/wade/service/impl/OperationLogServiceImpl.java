package top.wade.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import top.wade.entity.OperationLog;
import top.wade.exception.PersistenceException;
import top.wade.mapper.OperationLogMapper;
import top.wade.model.dto.UserAgentDTO;
import top.wade.service.OperationLogService;
import top.wade.util.IpAddressUtils;
import top.wade.util.UserAgentUtils;

/**
 * @Author xjw
 * @Date 2023/8/4 21:08
 * @Description:
 */
public class OperationLogServiceImpl implements OperationLogService {
    @Autowired
    OperationLogMapper operationLogMapper;
    @Autowired
    UserAgentUtils userAgentUtils;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveOperationLog(OperationLog log) {
        String ipSource = IpAddressUtils.getCityInfo(log.getIp());
        UserAgentDTO userAgentDTO = userAgentUtils.parseOsAndBrowser(log.getUserAgent());
        log.setIpSource(ipSource);
        log.setOs(userAgentDTO.getOs());
        log.setBrowser(userAgentDTO.getBrowser());
        if (operationLogMapper.saveOperationLog(log) != 1) {
            throw new PersistenceException("日志添加失败");
        }
    }
}
