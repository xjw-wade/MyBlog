package top.wade.service.impl;

import com.github.pagehelper.PageHelper;
import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.wade.entity.Moment;
import top.wade.mapper.MomentMapper;
import top.wade.service.MomentService;
import top.wade.util.markdown.MarkdownUtils;

import java.util.List;

/**
 * @Author xjw
 * @Date 2023/7/7 21:04
 * @Description: 博客动态业务层实现
 */
@Service
public class MomentServiceImpl implements MomentService {
    @Autowired
    MomentMapper momentMapper;
    //每页显示5条动态
    private static final int pageSize = 5;
    //动态列表排序方式
    private static final String orderBy = "create_time desc";
    //动态私密提示
    private static final String PRIVATE_MOMENT_CONTENT = "<p>此条为私密动态，仅发布者可见！</p>";


    @Override
    public List<Moment> getMomentVOList(Integer pageNum, boolean adminIdentity) {
        PageHelper.startPage(pageNum, pageSize, orderBy);
        List<Moment> moments = momentMapper.getMomentList();
        for (Moment moment: moments) {
            if (adminIdentity || moment.getPublished()) {
                moment.setContent(MarkdownUtils.markdownToHtmlExtensions(moment.getContent()));
            } else {
                moment.setContent(PRIVATE_MOMENT_CONTENT);
            }
        }
        return moments;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addLikeByMomentId(Long momentId) {
        if (momentMapper.addLikeByMomentId(momentId) != 1) {
            throw new PersistenceException("操作失败");
        }
    }
}
