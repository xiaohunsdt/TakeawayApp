package net.novaborn.takeaway.order.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.novaborn.takeaway.order.entity.Comment;
import net.novaborn.takeaway.order.entity.OrderItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author xiaohun
 * @since 2019-09-20
 */
public interface ICommentDao extends BaseMapper<Comment> {
    /**
     * 分页获取订单评论列表
     *
     * @param page   分页实例
     * @param args   userId 用户ID
     * @return 用户列表
     */
    IPage<Comment> getCommentListByPage(@Param("page") Page page, @Param("args") Map args);
}
