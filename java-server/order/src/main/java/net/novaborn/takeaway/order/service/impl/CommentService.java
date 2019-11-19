package net.novaborn.takeaway.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.novaborn.takeaway.order.dao.ICommentDao;
import net.novaborn.takeaway.order.dao.IOrderItemDao;
import net.novaborn.takeaway.order.entity.Comment;
import net.novaborn.takeaway.order.entity.OrderItem;
import net.novaborn.takeaway.order.service.ICommentService;
import net.novaborn.takeaway.order.service.IOrderItemService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaohun
 * @since 2019-09-20
 */
@Service
public class CommentService extends ServiceImpl<ICommentDao, Comment> implements ICommentService { }
