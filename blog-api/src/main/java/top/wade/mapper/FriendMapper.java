package top.wade.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.wade.entity.Friend;

import java.util.List;

/**
 * @Author xjw
 * @Date 2023/7/15 16:44
 * @Description: 友链持久层接口
 */
@Mapper
@Repository
public interface FriendMapper {
    List<Friend> getFriendList();

    List<top.wade.model.vo.Friend> getFriendVOList();

    int updateFriendPublishedById(Long id, Boolean published);

    int saveFriend(Friend friend);

    int updateFriend(top.wade.model.dto.Friend friend);

    int deleteFriend(Long id);

    int updateViewsByNickname(String nickname);


}
