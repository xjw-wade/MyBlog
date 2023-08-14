package top.wade.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.wade.entity.VisitRecord;
import top.wade.mapper.VisitRecordMapper;
import top.wade.service.VisitRecordService;

/**
 * @Author xjw
 * @Date 2023/8/13 17:22
 * @Description:
 */
@Service
public class VisitRecordServiceImpl implements VisitRecordService {
    @Autowired
    VisitRecordMapper visitRecordMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveVisitRecord(VisitRecord visitRecord) {
        visitRecordMapper.saveVisitRecord(visitRecord);
    }
}
