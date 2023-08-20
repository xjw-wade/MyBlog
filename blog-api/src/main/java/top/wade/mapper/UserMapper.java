package top.wade.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.wade.entity.User;

/**
 * @Author xjw
 * @Date 2023/7/8 22:21
 * @Description:
 */
@Mapper
@Repository
public interface UserMapper {
    User findByUsername(String username);

    int updateUserByUsername(String username, User user);

    User findById(Long id);
}
