package top.wade.util.comment.channel;

import top.wade.model.dto.Comment;

/**
 * @Author xjw
 * @Date 2023/7/20 16:12
 * @Description: 评论提醒方式
 */
public interface CommentNotifyChannel {
    /**
     * 通过指定方式通知自己
     *
     * @param comment 当前收到的评论
     */
    void notifyMyself(Comment comment);
}
