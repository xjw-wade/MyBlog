package top.wade.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * @Author xjw
 * @Date 2023/6/17 17:16
 * @Description:  GitHub徽标
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Badge {
    private String title;
    private String url;
    private String subject;
    private String value;
    private String color;

}
