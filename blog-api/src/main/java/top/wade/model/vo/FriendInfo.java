package top.wade.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author xjw
 * @Date 2023/7/15 16:38
 * @Description: 友链页面信息
 */

@NoArgsConstructor
@Getter
@Setter
@ToString
public class FriendInfo {
    private String content;
    private Boolean commentEnabled;
}
