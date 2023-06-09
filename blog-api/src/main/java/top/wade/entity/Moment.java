package top.wade.entity;

import java.util.Date;

/**
 * @Author xjw
 * @Date 2023/6/7 17:04
 * @Description: 博客动态
 */
public class Moment {
    private Long id;
    private String content;//动态内容
    private Date createTime;//创建时间
    private Integer likes;//点赞数量
    private Boolean published;//是否公开
}
