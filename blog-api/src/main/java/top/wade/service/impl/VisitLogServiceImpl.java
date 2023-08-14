package top.wade.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.wade.entity.VisitLog;
import top.wade.exception.PersistenceException;
import top.wade.mapper.VisitLogMapper;
import top.wade.model.dto.UserAgentDTO;
import top.wade.model.dto.VisitLogUuidTime;
import top.wade.service.VisitLogService;
import top.wade.util.IpAddressUtils;
import top.wade.util.UserAgentUtils;

import java.util.List;

/**
 * @Author xjw
 * @Date 2023/6/15 12:40
 * @Description: 访问日志业务层实现
 */
@Service
public class VisitLogServiceImpl implements VisitLogService {
    @Autowired
    VisitLogMapper visitLogMapper;
    @Autowired
    UserAgentUtils userAgentUtils;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveVisitLog(VisitLog log) {
        String ipSource = IpAddressUtils.getCityInfo(log.getIp());
        UserAgentDTO userAgentDTO = userAgentUtils.parseOsAndBrowser(log.getUserAgent());
        log.setIpSource(ipSource);
        log.setOs(userAgentDTO.getOs());
        log.setBrowser(userAgentDTO.getBrowser());
        if (visitLogMapper.saveVisitLog(log) != 1) {
            throw new PersistenceException("日志添加失败");
        }
    }

    @Override
    public List<VisitLogUuidTime> getUUIDAndCreateTimeByYesterday() {
        return visitLogMapper.getUUIDAndCreateTimeByYesterday();
    }

    @Override
    public List<VisitLog> getVisitLogListByUUIDAndDate(String uuid, String startDate, String endDate) {
        return visitLogMapper.getVisitLogListByUUIDAndDate(uuid, startDate, endDate);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteVisitLogById(Long id) {
        if (visitLogMapper.deleteVisitLogById(id) != 1) {
            throw new PersistenceException("删除日志失败");
        }
    }
}
