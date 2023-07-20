package top.wade.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.wade.entity.Comment;
import top.wade.model.vo.PageComment;

import java.util.List;

/**
 * @Author xjw
 * @Date 2023/7/15 23:13
 * @Description: 博客评论持久层接口
 */
@Mapper
@Repository
public interface CommentMapper {
    int countByPageAndIsPublished(Integer page, Long blogId, Boolean isPublished);

    List<Comment> getListByPageAndParentCommentId(Integer page, Long blogId, Long parentCommentId);

    List<PageComment> getPageCommentListByPageAndParentCommentId(Integer page, Long blogId, Long parentCommentId);

    Comment getCommentById(Long id);

    int saveComment(top.wade.model.dto.Comment comment);
}
