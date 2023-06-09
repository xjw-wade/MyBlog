package top.wade.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author xjw
 * @Date 2023/6/7 18:20
 * @Description: 关键字搜索博客
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SearchBlog {
    private Long id;
    private String title;
    private String content;
}
