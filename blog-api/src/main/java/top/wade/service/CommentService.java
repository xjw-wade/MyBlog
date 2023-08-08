package top.wade.service;

import top.wade.entity.Comment;
import top.wade.model.vo.PageComment;

import java.util.List;

/**
 * @Author xjw
 * @Date 2023/7/15 18:09
 * @Description:
 */
public interface CommentService {
    int countByPageAndIsPublished(Integer page, Long blogId, Boolean isPublished);

    List<Comment> getListByPageAndParentCommentId(Integer page, Long blogId, Long parentCommentId);

    List<PageComment> getPageCommentList(Integer page, Long blogId, Long parentCommentId);

    Comment getCommentById(Long id);

    void saveComment(top.wade.model.dto.Comment comment);

    void deleteCommentById(Long commentId);

    void deleteCommentsByBlogId(Long blogId);
}
