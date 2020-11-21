package net.novaborn.takeaway.order.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.novaborn.takeaway.order.entity.Order;
import net.novaborn.takeaway.order.enums.*;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author xiaohun
 * @since 2019-09-20
 */
public interface IOrderDao extends BaseMapper<Order> {
    /**
     * @param orderId       订单Id
     * @param isShowDeleted 是否显示被删除的订单
     * @return
     */
    Optional<Order> getById(@Param("orderId") Long orderId, @Param("isShowDeleted") boolean isShowDeleted);

    /**
     * 获取指定用户的订单
     *
     * @param userId        用户ID
     * @param paymentWay    支付方式
     * @param orderState    订单状态
     * @param isShowDeleted 是否显示已被删除的账户
     * @return 筛选后的指定用户订单列表
     */
    List<Order> getOrderListByUserId(@Param("userId") Long userId,
                                     @Param("paymentWay") PaymentWay paymentWay,
                                     @Param("orderState") OrderState orderState,
                                     @Param("isShowDeleted") boolean isShowDeleted);


    /**
     * 获取订单列表
     *
     * @param args userIds 用户IDs <br />
     *             orderId 订单ID <br />
     *             number 编号 <br />
     *             orderState 订单状态 <br />
     *             paymentWay 支付方式 <br />
     *             deliveryType 配送方式 <br />
     *             startDate endDate 范围时间
     * @return 订单列表
     */
    List<Order> getOrderList(@Param("args") Map args);

    /**
     * 分页获取订单列表
     *
     * @param page 分页实例
     * @param args userIds 用户IDs <br />
     *             orderId 订单ID <br />
     *             number 编号 <br />
     *             orderState 订单状态 <br />
     *             paymentWay 支付方式 <br />
     *             deliveryType 配送方式 <br />
     *             startDate endDate 范围时间
     * @return 订单列表
     */
    IPage<Order> getOrderListByPage(@Param("page") Page page, @Param("args") Map args);

    /**
     * 获取等待接单的订单
     *
     * @param orderType 订单类型
     * @return 等待接单的订单列表
     */
    List<Order> getWaitingReceiveOrder(@Param("orderType") OrderType orderType);

    /**
     * 获取等待接单的订单数量
     *
     * @param orderType 订单类型
     * @return 等待接单的订单数量
     */
    int getWaitingReceiveOrderCount(@Param("orderType") OrderType orderType);

    /**
     * 获取订单数量
     *
     * @param storeId      店铺ID
     * @param day          指定的日期
     * @param orderType    订单类型
     * @return 订单数量
     */
    int getOrderCount(@Param("day") Long storeId,@Param("day") Date day, @Param("orderType") OrderType orderType);

    /**
     * 用户端分页获取订单列表
     *
     * @param page       分页实例
     * @param userId     用户ID
     * @param orderState 订单状态
     * @return 订单列表
     */
    IPage<Order> getOrderListByPageU(@Param("page") Page page, @Param("userId") Long userId, @Param("orderState") OrderStateEx orderState);

    /**
     * 用户端获取指定订单类型的数量
     *
     * @param userId     用户ID
     * @param orderState 订单状态
     * @return 订单数量
     */
    int getOrderCountByStateU(@Param("userId") Long userId, @Param("orderState") OrderStateEx orderState);

    /**
     * 用户端获取今日指定类型的订单
     *
     * @param userId     用户ID
     * @param orderState 订单状态
     * @return 订单数量
     */
    List<Order> getTodayOrderByStateU(@Param("userId") Long userId, @Param("orderState") OrderStateEx orderState);

    /**
     * 用户端获取今日指定类型的订单数量
     *
     * @param userId     用户ID
     * @param orderState 订单状态
     * @return 订单数量
     */
    int getTodayOrderCountByStateU(@Param("userId") Long userId, @Param("orderState") OrderStateEx orderState);
}
