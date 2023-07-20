package top.wade.service;

import top.wade.model.vo.Friend;
import top.wade.model.vo.FriendInfo;

import java.util.List;

/**
 * @Author xjw
 * @Date 2023/7/15 16:34
 * @Description:
 */
public interface FriendService {
    List<Friend> getFriendVOList();

    FriendInfo getFriendInfo(boolean cache, boolean md);

    void updateViewsByNickName(String nickname);
}
