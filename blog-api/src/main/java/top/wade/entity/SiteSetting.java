package top.wade.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author xjw
 * @Date 2023/6/17 17:06
 * @Description: 站点设置
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class SiteSetting {
    private Long id;
    private String nameEn;
    private String nameZh;
    private String value;
    private Integer type;
}
