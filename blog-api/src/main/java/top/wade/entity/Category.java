package top.wade.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author xjw
 * @Date 2023/6/7 16:50
 * @Version 0.0.1
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Category {
    private Long id;
    private String name;//分类名称
    private List<Blog> blogs = new ArrayList<>();//该分类下的博客文章
}
