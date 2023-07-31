package top.wade.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.wade.entity.CityVisitor;

import java.util.List;

/**
 * @Author xjw
 * @Date 2023/7/31 18:03
 * @Description: 城市访客数量统计持久层接口
 */
@Mapper
@Repository
public interface CityVisitorMapper {
    List<CityVisitor> getCityVisitorList();

}
