package top.wade.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author xjw
 * @Date 2023/6/16 15:01
 * @Description: UserAgent解析DTO
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserAgentDTO {
    private String os;
    private String browser;
}
