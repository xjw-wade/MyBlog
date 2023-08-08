package top.wade.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.wade.entity.Moment;

import java.util.List;

/**
 * @Author xjw
 * @Date 2023/7/8 22:33
 * @Description: 博客动态持久层接口
 */
@Mapper
@Repository
public interface MomentMapper {
    List<Moment> getMomentList();

    int addLikeByMomentId(Long momentId);

    int updateMomentPublishedById(Long momentId, Boolean published);

    Moment getMomentById(Long id);

    int deleteMomentById(Long id);

    int saveMoment(Moment moment);

    int updateMoment(Moment moment);

}
