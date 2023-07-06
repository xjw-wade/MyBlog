package top.wade.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author xjw
 * @Date 2023/7/6 18:34
 * @Description: 归档页面博客简要信息
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ArchiveBlog {
    private Long id;
    private String title;
    private String day;
    private String password;
    private Boolean privacy;
}
