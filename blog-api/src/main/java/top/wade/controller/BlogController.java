package top.wade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.wade.annotation.VisitLogger;
import top.wade.constant.JwtConstants;
import top.wade.entity.User;
import top.wade.enums.VisitBehavior;
import top.wade.model.vo.*;
import top.wade.service.BlogService;
import top.wade.service.impl.UserServiceImpl;
import top.wade.util.JwtUtils;
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
    @Autowired
    UserServiceImpl userService;

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

    /**
     * 按id获取公开博客详情
     *
     * @param id  博客id
     * @param jwt 密码保护文章的访问Token
     * @return
     */
    @VisitLogger(VisitBehavior.BLOG)
    @GetMapping("/blog")
    public Result getBlog(@RequestParam Long id,
                          @RequestHeader(value = "Authorization", defaultValue = "") String jwt) {
        BlogDetail blog = blogService.getBlogByIdAndIsPublished(id);
        //对密码保护的文章校验token
        if (!"".equals(blog.getPassword())) {
            if (JwtUtils.judgeTokenIsExist(jwt)) {
                try {
                    String subject = JwtUtils.getTokenBody(jwt).getSubject();
                    if (subject.startsWith(JwtConstants.ADMIN_PREFIX)) {
                        //博主身份token
                        String username = subject.replace(JwtConstants.ADMIN_PREFIX, "");
                        User admin = (User) userService.loadUserByUsername(username);
                        if (admin == null) {
                            return Result.create(403, "博主身份Token已失效，请重新登录！");
                        }
                    } else {
                        //经密码验证后的Token
                        Long tokenBlogId = Long.parseLong(subject);
                        //博客id不匹配，验证不通过，可能博客id改变或客户端传递了其它密码保护文章的Token
                        if (!tokenBlogId.equals(id)) {
                            return Result.create(403, "Token不匹配，请重新验证密码！");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return Result.create(403, "Token已失效，请重新验证密码！");
                }
            } else {
                return Result.create(403, "此文章受密码保护，请验证密码！");
            }
            blog.setPassword("");
        }
        blogService.updateViewsToRedis(id);
        return Result.ok("获取成功", blog);
    }

}
