package top.wade.service.impl;

import com.github.pagehelper.Page;
import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.wade.entity.Comment;
import top.wade.mapper.CommentMapper;
import top.wade.model.vo.PageComment;
import top.wade.service.CommentService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @Author xjw
 * @Date 2023/7/15 22:06
 * @Description: 评论业务层实现
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentMapper commentMapper;

    @Override
    public int countByPageAndIsPublished(Integer page, Long blogId, Boolean isPublished) {
        return commentMapper.countByPageAndIsPublished(page, blogId, isPublished);
    }

    @Override
    public List<Comment> getListByPageAndParentCommentId(Integer page, Long blogId, Long parentCommentId) {
        List<Comment> comments = commentMapper.getListByPageAndParentCommentId(page, blogId, parentCommentId);
        for (Comment c: comments) {
            //递归查询子评论极其子评论
            List<Comment> replyComments = getListByPageAndParentCommentId(page, blogId, c.getId());
            c.setReplyComments(replyComments);
        }
        return comments;
    }

    @Override
    public List<PageComment> getPageCommentList(Integer page, Long blogId, Long parentCommentId) {
        List<PageComment> comments = getPageCommentListByPageAndParentCommentId(page, blogId, parentCommentId);
        for (PageComment c: comments) {
            List<PageComment> tmpComments = new ArrayList<>();
            getReplyComments(tmpComments, c.getReplyComments());
            //对于两列评论来说，按时间顺序排列应该比树形更合理些
            //排序一下
            Comparator<PageComment> comparator = Comparator.comparing(PageComment::getCreateTime);
            tmpComments.sort(comparator);
            c.setReplyComments(tmpComments);
        }
        return comments;
    }

    @Override
    public Comment getCommentById(Long id) {
        Comment comment = commentMapper.getCommentById(id);
        if (comment == null) {
            throw new PersistenceException("评论不存在");
        }
        return comment;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveComment(top.wade.model.dto.Comment comment) {
        if (commentMapper.saveComment(comment) != 1) {
            throw new PersistenceException("评论失败");
        }

    }

    private List<PageComment> getPageCommentListByPageAndParentCommentId(Integer page, Long blogId, Long parentCommentId) {
        List<PageComment> comments = commentMapper.getPageCommentListByPageAndParentCommentId(page, blogId, parentCommentId);
        for (PageComment c: comments) {
            List<PageComment> replyComments = getPageCommentListByPageAndParentCommentId(page, blogId, c.getId());
            c.setReplyComments(replyComments);
        }
        return comments;
    }

    /**
     * 将所有子评论递归取出到一个List中
     *
     * @param comments
     */
    private void getReplyComments(List<PageComment> tmpComments, List<PageComment> comments) {
        for (PageComment c: comments) {
            tmpComments.add(c);
            getReplyComments(tmpComments, c.getReplyComments());
        }
    }

}
