package net.novaborn.takeaway.order.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import net.novaborn.takeaway.order.entity.Comment;
import net.novaborn.takeaway.order.entity.OrderItem;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xiaohun
 * @since 2019-09-20
 */
public interface ICommentService extends IService<Comment> {
    /**
     * 分页获取订单评论列表
     *
     * @param page 分页实例
     * @param args userId 用户ID
     * @return 用户列表
     */
    IPage<Comment> getCommentListByPage(Page page, Map args);
}
