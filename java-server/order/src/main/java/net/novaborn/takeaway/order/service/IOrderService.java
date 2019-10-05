package net.novaborn.takeaway.order.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import net.novaborn.takeaway.order.entity.Order;
import net.novaborn.takeaway.order.enums.OrderState;
import net.novaborn.takeaway.order.enums.PaymentWay;

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
public interface IOrderService extends IService<Order> {
    /**
     * 获取指定用户的订单
     * @param userId 用户ID
     * @return 用户的订单列表
     */
    List<Order> selectByUserId(String userId);

    /**
     * 获取指定用户的订单
     * @param userId 用户ID
     * @param paymentWay 支付方式
     * @param orderState 订单状态
     * @return 筛选后的指定用户订单列表
     */
    List<Order> selectByUserId(String userId, PaymentWay paymentWay, OrderState orderState);

    /**
     * 分页获取订单列表
     * @param page   分页实例
     * @param args   用户名称
     * @return 用户列表
     */
    IPage<Order> getOrderListByPage(Page page, Map args);
}
