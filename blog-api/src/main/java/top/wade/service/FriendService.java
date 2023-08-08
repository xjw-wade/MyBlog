package top.wade.service;


import top.wade.entity.Friend;
import top.wade.model.vo.FriendInfo;

import java.util.List;

/**
 * @Author xjw
 * @Date 2023/7/15 16:34
 * @Description:
 */
public interface FriendService {
    List<top.wade.model.vo.Friend> getFriendVOList();

    FriendInfo getFriendInfo(boolean cache, boolean md);

    void updateViewsByNickName(String nickname);

    List<Friend> getFriendList();

    void saveFriend(Friend friend);

    void deleteFriend(Long id);

    void updateFriend(top.wade.model.dto.Friend friend);

    void updateFriendInfoContent(String content);

    void updateFriendInfoCommentEnabled(Boolean commentEnabled);

    void updateFriendPublishedById(Long friendId, Boolean published);

}
