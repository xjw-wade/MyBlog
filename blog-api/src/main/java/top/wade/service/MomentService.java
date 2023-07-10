package top.wade.service;

import top.wade.entity.Moment;

import java.util.List;

/**
 * @Author xjw
 * @Date 2023/7/7 21:04
 * @Description:
 */
public interface MomentService {
    List<Moment> getMomentVOList(Integer pageNum, boolean adminIdentity);

    void addLikeByMomentId(Long momentId);
}
