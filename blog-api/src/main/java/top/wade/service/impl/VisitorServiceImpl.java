package top.wade.service.impl;

import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.wade.entity.Visitor;
import top.wade.mapper.VisitorMapper;
import top.wade.model.dto.UserAgentDTO;
import top.wade.service.VisitorService;
import top.wade.util.IpAddressUtils;
import top.wade.util.UserAgentUtils;

/**
 * @Author xjw
 * @Date 2023/6/16 13:05
 * @Description: 访客统计业务层实现
 */
@Service
public class VisitorServiceImpl implements VisitorService {
    @Autowired
    VisitorMapper visitorMapper;
    @Autowired
    UserAgentUtils userAgentUtils;

    @Override
    public boolean hasUUID(String uuid) {
        return visitorMapper.hasUUID(uuid) != 0;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveVisitor(Visitor visitor) {
        String ipSource = IpAddressUtils.getCityInfo(visitor.getIp());
        UserAgentDTO userAgentDTO = userAgentUtils.parseOsAndBrowser(visitor.getUserAgent());
        visitor.setIpSource(ipSource);
        visitor.setOs(userAgentDTO.getOs());
        visitor.setBrowser(userAgentDTO.getBrowser());
        if (visitorMapper.saveVisitor(visitor) != 1) {
            throw new PersistenceException("访客添加失败");
        }

    }
}
