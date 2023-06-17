package top.wade.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author xjw
 * @Date 2023/6/17 17:16
 * @Description: 自定义爱好
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Favorite {
    private String title;
    private String content;
}
