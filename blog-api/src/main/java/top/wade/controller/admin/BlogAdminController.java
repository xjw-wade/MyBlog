package top.wade.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.wade.annotation.OperationLogger;
import top.wade.entity.Blog;
import top.wade.entity.Category;
import top.wade.entity.Tag;
import top.wade.entity.User;
import top.wade.model.vo.Result;
import top.wade.service.BlogService;
import top.wade.service.CategoryService;
import top.wade.service.TagService;
import top.wade.util.StringUtils;

import java.util.*;

/**
 * @Author xjw
 * @Date 2023/8/1 15:46
 * @Description: 博客文章后台管理
 */
@RestController
@RequestMapping("/admin")
public class BlogAdminController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    TagService tagService;
    @Autowired
    BlogService blogService;
    /**
     * 获取分类列表和标签列表
     *
     * @return
     */
    @GetMapping("/categoryAndTag")
    public Result categoryAndTag() {
        List<Category> categoryList = categoryService.getCategoryList();
        List<Tag> tagList = tagService.getTagList();
        Map<String, Object> map = new HashMap<>(4);
        map.put("categories", categoryList);
        map.put("tags", tagList);
        return Result.ok("请求成功", map);
    }

    /**
     * 保存草稿或发布新文章
     *
     * @param blog 博客文章DTO
     * @return
     */
    @OperationLogger("发布博客")
    @PostMapping("/blog")
    public Result saveBlog(@RequestBody top.wade.model.dto.Blog blog) {
        return getResult(blog, "save");
    }

    /**
     * 按id获取博客详情
     *
     * @param id 博客id
     * @return
     */
    @GetMapping("/blog")
    public Result getBlog(@RequestParam Long id) {
        Blog blog = blogService.getBlogById(id);
        return Result.ok("获取成功", blog);
    }

    /**
     * 更新博客
     *
     * @param blog 博客文章DTO
     * @return
     */
    @OperationLogger("更新博客")
    @PutMapping("/blog")
    public Result updateBlog(@RequestBody top.wade.model.dto.Blog blog) {
        return getResult(blog, "update");
    }



    /**
     * 执行博客添加或更新操作：校验参数是否合法，添加分类、标签，维护博客标签关联表
     *
     * @param blog 博客文章DTO
     * @param type 添加或更新
     * @return
     */
    private Result getResult(top.wade.model.dto.Blog blog, String type) {
        //验证普通字段
        if (StringUtils.isEmpty(blog.getTitle(), blog.getFirstPicture(), blog.getContent(), blog.getDescription())
                       || blog.getWords() == null || blog.getWords() < 0) {
            return Result.error("参数有误");
        }

        //处理分类
        Object cate = blog.getCate();
        if (cate == null) {
            return Result.error("分类不能为空");
        }
        if (cate instanceof Integer) { //选择了已存在的分类
            Category c = categoryService.getCategoryById(((Integer)cate).longValue());
            blog.setCategory(c);
        } else if (cate instanceof String) {//添加新分类
            //查询分类是否存在
            Category category = categoryService.getCategoryByName((String) cate);
            if (category != null) {
                return Result.error("分类已存在");
            }
            Category c = new Category();
            c.setName((String) cate);
            categoryService.saveCategory(c);
            blog.setCategory(c);
        } else {
            return Result.error("分类不正确");
        }

        //处理标签
        List<Object> tagList = blog.getTagList();
        List<Tag> tags = new ArrayList<>();
        for (Object t: tagList) {
            if (t instanceof Integer) { //选择了已存在的标签
                Tag tag = tagService.getTagById(((Integer) t).longValue());
                tags.add(tag);
            }else if (t instanceof String) {//添加新标签
                //查询标签是否已存在
                Tag tag1 = tagService.getTagByName((String) t);
                if (tag1 != null) {
                    return Result.error("不可添加已存在的标签");
                }
                Tag tag = new Tag();
                tag.setName((String) t);
                tagService.saveTag(tag);
                tags.add(tag);
            } else {
                return Result.error("标签不正确");
            }

        }

        Date date = new Date();
        if (blog.getReadTime() == null || blog.getReadTime() < 0) {
            blog.setReadTime((int) Math.round(blog.getWords() / 200)); //粗略计算阅读时长
        }
        if (blog.getViews() == null || blog.getViews() < 0) {
            blog.setViews(0);
        }
        if ("save".equals(type)) {
            blog.setCreateTime(date);
            blog.setUpdateTime(date);
            User user = new User();
            user.setId(1L);//个人博客默认只有一个作者
            blog.setUser(user);

            blogService.saveBlog(blog);
            //关联博客和标签 (维护blog_tag表)
            for (Tag t : tags) {
                blogService.saveBlogTag(blog.getId(), t.getId());
            }
            return Result.ok("添加成功");
        } else {
            blog.setUpdateTime(date);
            blogService.updateBlog(blog);
            //关联博客和标签 (维护blog_tag表)
            blogService.deleteBlogTagByBlogId(blog.getId());
            for (Tag t : tags) {
                blogService.saveBlogTag(blog.getId(), t.getId());
            }
            return Result.ok("更新成功");
        }
    }
}
