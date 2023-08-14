package top.wade.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.wade.entity.CityVisitor;
import top.wade.mapper.CityVisitorMapper;
import top.wade.service.CityVisitorService;

/**
 * @Author xjw
 * @Date 2023/8/14 15:25
 * @Description: 城市访客数量统计业务层实现
 */
@Service
public class CityVisitorServiceImpl implements CityVisitorService {
    @Autowired
    CityVisitorMapper cityVisitorMapper;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveCityVisitor(CityVisitor cityVisitor) {
        cityVisitorMapper.saveCityVisitor(cityVisitor);
    }
}
