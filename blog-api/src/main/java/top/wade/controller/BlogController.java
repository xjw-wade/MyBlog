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
import top.wade.model.vo.SearchBlog;
import top.wade.service.BlogService;
import top.wade.util.StringUtils;

import java.util.List;

/**
 * @Author xjw
 * @Date 2023/6/9 16:12
 * @Description: 博客相关
 */
@RestController
public class BlogController {
    @Autowired
    BlogService blogService;

    /**
     * 按置顶、创建时间排序 分页查询博客简要信息列表
     *
     * @param pageNum 页码
     * @return
     */
    @VisitLogger(VisitBehavior.INDEX)
    @GetMapping("/blogs")
    public Result blogs(@RequestParam(defaultValue = "1") Integer pageNum) {
        PageResult<BlogInfo> pageResult = blogService.getBlogInfoListByIsPublished(pageNum);
        return Result.ok("请求成功", pageResult);
    }

    /**
     * 按关键字根据文章内容搜索公开且无密码保护的博客文章
     *
     * @param query 关键字字符串
     * @return
     */
    @VisitLogger(VisitBehavior.SEARCH)
    @GetMapping("/searchBlog")
    public Result searchBlig(@RequestParam String query) {
        //校验关键字符串合法性
        if (StringUtils.isEmpty(query) || StringUtils.hasSpecialChar(query) || query.trim().length() > 20) {
            return Result.error("参数错误");
        }
        List<SearchBlog> searchBlogs = blogService.getSearchBlogListByQueryAndIsPublished(query.trim());
        return Result.ok("获取成功", searchBlogs);
    }

}
