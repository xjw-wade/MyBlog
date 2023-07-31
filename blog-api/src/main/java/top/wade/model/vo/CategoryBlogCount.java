package top.wade.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author xjw
 * @Date 2023/7/31 17:05
 * @Description: 分类和博客数量
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CategoryBlogCount {
    private Long id;
    private String name; //分类名
    private Integer value; //分类下博客数量
}
