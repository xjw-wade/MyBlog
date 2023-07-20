package top.wade.enums;

/**
 * @Author xjw
 * @Date 2023/7/17 23:51
 * @Description: 评论页面枚举类
 */
public enum CommentPageEnum {
    UNKNOWN("UNKNOWN", "UNKNOWN"),

    BLOG("", ""),
    ABOUT("关于我", "/about"),
    FRIEND("友人帐", "/friends"),
    ;



    private String title;
    private String path;

    CommentPageEnum(String title, String path) {
        this.title = title;
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}
