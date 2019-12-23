package net.novaborn.takeaway.order.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.novaborn.takeaway.order.dao.ICommentDao;
import net.novaborn.takeaway.order.entity.Comment;
import net.novaborn.takeaway.order.service.ICommentService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaohun
 * @since 2019-09-20
 */
@Service
public class CommentService extends ServiceImpl<ICommentDao, Comment> implements ICommentService {
    @Override
    public IPage<Comment> getCommentListByPage(Page page, Map args) {
        return this.baseMapper.getCommentListByPage(page, args);
    }
}
