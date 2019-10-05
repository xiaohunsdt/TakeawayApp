package net.novaborn.takeaway.order.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.novaborn.takeaway.order.entity.Order;
import net.novaborn.takeaway.order.entity.OrderItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xiaohun
 * @since 2019-09-20
 */
public interface IOrderItemDao extends BaseMapper<OrderItem> {
    /**
     * 通过订单ID获取订单项
     * @param orderId 订单ID
     * @return 订单项列表
     */
    List<OrderItem> selectByOrderId(@Param("orderId") String orderId);
}
