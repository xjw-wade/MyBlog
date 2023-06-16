package top.wade.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
/**
 * @Author xjw
 * @Date 2023/6/16 15:23
 * @Description: 访问日志备注
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class VisitLogRemark {
    /**
     * 访问内容
     */
    private String content;

    /**
     * 备注
     */
    private String remark;
}
