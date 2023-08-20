package top.wade.util.comment.channel;

import top.wade.constant.CommentConstants;
import top.wade.util.common.SpringContextUtils;

/**
 * @Author xjw
 * @Date 2023/8/20 16:14
 * @Description: 评论提醒方式
 */
public class ChannelFactory {
    /**
     * 创建评论提醒方式
     *
     * @param channelName 方式名称
     * @return
     */
    public static CommentNotifyChannel getChannel(String channelName) {
        if (CommentConstants.TELEGRAM.equalsIgnoreCase(channelName)) {
            return SpringContextUtils.getBean("telegramChannel", CommentNotifyChannel.class);
        } else if (CommentConstants.MAIL.equalsIgnoreCase(channelName)) {
            return SpringContextUtils.getBean("mailChannel", CommentNotifyChannel.class);
        }
        throw new RuntimeException("Unsupported value in [application.properties]: [comment.notify.channel]");
    }
}
