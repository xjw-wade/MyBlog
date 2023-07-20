package top.wade.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.wade.entity.About;

import java.util.List;

/**
 * @Author xjw
 * @Date 2023/7/20 19:10
 * @Description:
 */
@Repository
@Mapper
public interface AboutMapper {
    List<About> getList();

}
