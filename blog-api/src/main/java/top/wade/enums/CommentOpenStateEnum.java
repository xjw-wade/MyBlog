package top.wade.enums;

/**
 * @Author xjw
 * @Date 2023/7/15 22:14
 * @Description: 评论开放状态枚举类
 */
public enum CommentOpenStateEnum {
    /**
     * 博客不存在，或博客未公开
     */
    NOT_FOUND,
    /**
     * 评论正常开放
     */
    OPEN,
    /**
     * 评论已关闭
     */
    CLOSE,
    /**
     * 评论所在页面需要密码
     */
    PASSWORD,
}
