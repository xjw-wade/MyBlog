package top.wade.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
/**
 * @Author xjw
 * @Date 2023/7/31 16:59
 * @Description: 城市访客数量
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CityVisitor {
    private String city; //城市名称
    private Integer uv;  //独立访客数量
}
