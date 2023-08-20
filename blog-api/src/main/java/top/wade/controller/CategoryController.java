package top.wade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.wade.annotation.VisitLogger;
import top.wade.enums.VisitBehavior;
import top.wade.model.vo.BlogInfo;
import top.wade.model.vo.PageResult;
import top.wade.model.vo.Result;
import top.wade.service.BlogService;

/**
 * @Author xjw
 * @Date 2023/7/3 21:32
 * @Description: 分类
 */
@RestController
public class CategoryController {
    @Autowired
    BlogService blogService;

    /**
     * 根据分类name分页查询公开博客列表
     *
     * @param categoryName 分类name
     * @param pageNum      页码
     * @return
     */
    @VisitLogger(VisitBehavior.CATEGORY)
    @GetMapping("/category")
    public Result category(@RequestParam String categoryName, @RequestParam(defaultValue = "1") Integer pageNum) {
        PageResult<BlogInfo> pageResult = blogService.getBlogInfoListByCategoryNameAndIsPublished(categoryName, pageNum);
        return Result.ok("请求成功", pageResult);
    }
}
