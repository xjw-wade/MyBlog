package top.wade.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author xjw
 * @Date 2023/7/22 21:58
 * @Description: 登录账号密码
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class LoginInfo {
    private String username;
    private String password;
}
