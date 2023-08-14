package top.wade.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @Author xjw
 * @Date 2023/8/12 21:42
 * @Description: 访客更新DTO
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class VisitLogUuidTime {
    private String uuid;
    private Date time;
    private Integer pv;
}
