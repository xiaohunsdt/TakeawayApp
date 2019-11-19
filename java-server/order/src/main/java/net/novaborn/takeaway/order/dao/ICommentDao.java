package net.novaborn.takeaway.order.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.novaborn.takeaway.order.entity.Comment;
import net.novaborn.takeaway.order.entity.OrderItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xiaohun
 * @since 2019-09-20
 */
public interface ICommentDao extends BaseMapper<Comment> { }
