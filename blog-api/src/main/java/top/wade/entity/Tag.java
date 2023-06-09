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
public class Tag {
    private Long id;
    private String name;//标签名称
    private String color;//标签颜色(与Semantic UI提供的颜色对应，可选)
    private List<Blog> blogs = new ArrayList<>();//该标签下的博客文章
}
