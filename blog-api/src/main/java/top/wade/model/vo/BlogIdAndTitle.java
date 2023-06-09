package top.wade.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author xjw
 * @Date 2023/6/9 15:56
 * @Description: 评论管理页面按博客title查询评论
 */

@NoArgsConstructor
@Getter
@Setter
@ToString
public class BlogIdAndTitle {
    private Long id;
    private String title;
}
