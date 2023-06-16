package top.wade.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @Author xjw
 * @Date 2023/6/9 20:21
 * @Description: 分页结果
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PageResult<T> {
    private Integer totalPage;//总页数
    private List<T> list;//数据

    public PageResult(Integer totalPage, List<T> list) {
        this.totalPage = totalPage;
        this.list = list;
    }

}
