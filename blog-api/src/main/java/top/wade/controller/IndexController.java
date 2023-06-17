package top.wade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.wade.entity.Category;
import top.wade.entity.Tag;
import top.wade.model.vo.NewBlog;
import top.wade.model.vo.RandomBlog;
import top.wade.model.vo.Result;
import top.wade.service.BlogService;
import top.wade.service.CategoryService;
import top.wade.service.SiteSettingService;
import top.wade.service.TagService;

import java.util.List;
import java.util.Map;

/**
 * @Author xjw
 * @Date 2023/6/17 16:24
 * @Description: 站点相关
 */
@RestController
public class IndexController {
    @Autowired
    SiteSettingService siteSettingService;
    @Autowired
    BlogService blogService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    TagService tagService;

    /**
     * 获取站点配置信息、最新推荐博客、分类列表、标签云、随机博客
     *
     * @return
     */
    @GetMapping("/site")
    public Result site() {
        Map<String, Object> map = siteSettingService.getSiteInfo();
        List<NewBlog> newBlogList = blogService.getNewBlogListByIsPublished();
        List<Category> categoryList = categoryService.getCategoryNameList();
        List<Tag> tagList = tagService.getTagListNotId();
        List<RandomBlog> randomBlogList = blogService.getRandomBlogListByLimitNumAndIsPublishedAndIsRecommend();
        map.put("newBlogList", newBlogList);
        map.put("categoryList", categoryList);
        map.put("tagList", tagList);
        map.put("randomBlogList", randomBlogList);
        return Result.ok("请求成功", map);
    }
}
