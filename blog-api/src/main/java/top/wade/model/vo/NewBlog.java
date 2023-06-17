package top.wade.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author xjw
 * @Date 2023/6/15 11:49
 * @Description: 最新推荐博客
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class NewBlog {
    private Long id;
    private String title;
    private String password;
    private Boolean privacy;
}
