package net.novaborn.takeaway.order.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.novaborn.takeaway.order.entity.Order;
import net.novaborn.takeaway.order.enums.OrderState;
import net.novaborn.takeaway.order.enums.PaymentWay;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author xiaohun
 * @since 2019-09-20
 */
public interface IOrderDao extends BaseMapper<Order> {
    /**
     * 获取指定用户的订单
     * @param userId 用户ID
     * @param paymentWay 支付方式
     * @param orderState 订单状态
     * @return 筛选后的指定用户订单列表
     */
    List<Order> getOrderListByUserId(@Param("userId") String userId, @Param("paymentWay") PaymentWay paymentWay, @Param("orderState") OrderState orderState);

    /**
     * 分页获取订单列表
     * @param page   分页实例
     * @param args   userId 用户ID <br />
     *               paymentWay 支付方式 <br />
     *               orderState 订单状态
     * @return 订单列表
     */
    IPage<Order> getOrderListByPage(@Param("page") Page page, @Param("args") Map args);


    List<Order> getWaitingReceiveOrder();

    int getWaitingReceiveOrderCount();

    /**
     * 用户端分页获取订单列表
     * @param page   分页实例
     * @param args   userId 用户ID <br />
     *               orderState 订单状态
     *                  - waitPay 等待支付
     *                  - waitEat 等待就餐
     *                  - waitComment 等待评价
     *                  - refund 退款
     * @return 订单列表
     */
    IPage<Order> getOrderListByPageU(@Param("page") Page page, @Param("args") Map args);

    /**
     * 用户端获取指定订单类型的数量
     * @param args   userId 用户ID <br />
     *               orderState 订单状态
     *                  - waitPay 等待支付
     *                  - waitEat 等待就餐
     *                  - waitComment 等待评价
     *                  - refund 退款
     * @return 订单数量
     */
    int getOrderCountByStateU(@Param("args") Map args);

    /**
     * 获取当天的订单数量
     * @return 当天的订单数量
     */
    int getOrderCountToday();
}
