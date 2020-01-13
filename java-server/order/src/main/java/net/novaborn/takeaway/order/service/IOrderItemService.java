package net.novaborn.takeaway.order.service;


import com.baomidou.mybatisplus.extension.service.IService;
import net.novaborn.takeaway.order.entity.Order;
import net.novaborn.takeaway.order.entity.OrderItem;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xiaohun
 * @since 2019-09-20
 */
public interface IOrderItemService extends IService<OrderItem> {
    /**
     * 通过订单ID获取订单项
     * @param orderId 订单ID
     * @return 订单项列表
     */
    List<OrderItem> selectByOrderId(String orderId);

    /**
     * 根据订单ID删除指定订单项
     * @param orderId 订单ID
     * @return 是否成功
     */
    boolean removeByOrderId(String orderId);

    /**
     * 检测商品项是否可以下单
     * 商品不足抛出 @GoodsExceptionEnum 异常
     * @param orderItemList 被检测的订单商品项
     */
    void checkOrderItems(List<OrderItem> orderItemList);
}
