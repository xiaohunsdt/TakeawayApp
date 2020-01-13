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
import java.util.Optional;

/**
 * @author xiaohun
 * @since 2019-09-20
 */
public interface IOrderDao extends BaseMapper<Order> {
    /**
     *
     * @param orderId 订单Id
     * @param isShowDeleted 是否显示被删除的订单
     * @return
     */
    Optional<Order> getById(@Param("orderId") String orderId, @Param("isShowDeleted") boolean isShowDeleted);

    /**
     * 获取指定用户的订单
     * @param userId 用户ID
     * @param paymentWay 支付方式
     * @param orderState 订单状态
     * @param isShowDeleted 是否显示已被删除的账户
     * @return 筛选后的指定用户订单列表
     */
    List<Order> getOrderListByUserId(@Param("userId") String userId,
                                     @Param("paymentWay") PaymentWay paymentWay,
                                     @Param("orderState") OrderState orderState,
                                     @Param("isShowDeleted") boolean isShowDeleted);

    /**
     * 分页获取订单列表
     * @param page 分页实例
     * @param args userIds 用户IDs <br />
     *             orderId 订单ID <br />
     *             number 编号 <br />
     *             orderState 订单状态 <br />
     *             paymentWay 支付方式 <br />
     *             startDate endDate 范围时间
     * @return 订单列表
     */
    IPage<Order> getOrderListByPage(@Param("page") Page page, @Param("args") Map args);

    /**
     * 获取等待接单的订单
     * @return 等待接单的订单列表
     */
    List<Order> getWaitingReceiveOrder();

    /**
     * 获取等待接单的订单数量
     * @return 等待接单的订单数量
     */
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
