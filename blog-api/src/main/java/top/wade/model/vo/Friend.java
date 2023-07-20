package top.wade.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author xjw
 * @Date 2023/7/15 16:37
 * @Description: 友链VO
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Friend {
    private String nickname;//昵称
    private String description;//描述
    private String website;//站点
    private String avatar;//头像
}
