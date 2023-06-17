package top.wade.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.wade.entity.SiteSetting;

import java.util.List;

/**
 * @Author xjw
 * @Date 2023/6/17 17:05
 * @Description: 站点设置持久层接口
 */
@Mapper
@Repository
public interface SiteSettingMapper {
    List<SiteSetting> getList();
}
