package net.novaborn.takeaway.order.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import net.novaborn.takeaway.order.dto.OrderDto;
import net.novaborn.takeaway.order.entity.Order;
import net.novaborn.takeaway.order.entity.OrderItem;
import net.novaborn.takeaway.order.enums.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
     * @param orderId       订单Id
     * @param isShowDeleted 是否显示被删除的订单
     * @return
     */
    Optional<Order> getById(Long orderId, boolean isShowDeleted);

    /**
     * 获取指定用户的订单
     *
     * @param userId 用户ID
     * @return 用户的订单列表
     */
    List<Order> getOrderListByUserId(Long userId);

    /**
     * 获取指定用户的订单
     *
     * @param userId        用户ID
     * @param paymentWay    支付方式
     * @param orderState    订单状态
     * @param isShowDeleted 是否显示已被删除的账户
     * @return 筛选后的指定用户订单列表
     */
    List<Order> getOrderListByUserId(Long userId,
                                     PaymentWay paymentWay,
                                     OrderState orderState,
                                     boolean isShowDeleted);

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
    List<Order> getOrderList(Map args);

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
    IPage<Order> getOrderListByPage(Page page, Map args);

    /**
     * 获取等待接单的订单数量
     *
     * @param orderType 订单类型
     * @return 等待接单的订单数量
     */
    int getWaitingReceiveOrderCount(OrderType orderType);

    /**
     * 用户端分页获取订单列表
     *
     * @param page       分页实例
     * @param userId     用户ID
     * @param orderState 订单状态
     * @return 订单列表
     */
    IPage<Order> getOrderListByPageU(Page page, Long userId, OrderStateEx orderState);

    /**
     * 用户端获取指定订单类型的数量
     *
     * @param userId     用户ID
     * @param orderState 订单状态
     * @return 订单数量
     */
    int getOrderCountByStateU(Long userId, OrderStateEx orderState);

    /**
     * 获取订单数量
     *
     * @param day          指定的日期
     * @param orderType    订单类型
     * @return 订单数量
     */
    int getOrderCount(Date day, OrderType orderType);


    /**
     * 获取订单数量
     *
     * @param storeId       店铺Id
     * @param day           指定的日期
     * @param orderType     订单类型
     * @return 订单数量
     */
    int getOrderCount(Long storeId, Date day, OrderType orderType);

    /**
     * 用户端获取今日指定类型的订单
     *
     * @param userId     用户ID
     * @param orderState 订单状态
     * @return 订单数量
     */
    List<Order> getTodayOrderByStateU(Long userId, OrderStateEx orderState);

    /**
     * 用户端获取今日指定类型的数量
     *
     * @param userId     用户ID
     * @param orderState 订单状态
     * @return 订单数量
     */
    int getTodayOrderCountByStateU(Long userId, OrderStateEx orderState);

    /**
     * 设置优惠卷折扣
     *
     * @param order
     * @param orderItemList
     * @param couponId
     */
    void setDiscount(Order order, List<OrderItem> orderItemList, Long couponId);

    /**
     * 设置一般折扣
     *
     * @param order         预设置的订单
     * @param orderItemList 预设置的订单商品项
     * @param discount      折扣, 1-99 的折扣大小
     */
    void setDiscount(Order order, List<OrderItem> orderItemList, int discount);

    /**
     * 检查此订单是否可以下单
     *
     * @param order         预检查的订单
     * @param orderItemList 预检查的订单商品项
     */
    void checkOrder(Order order, List<OrderItem> orderItemList);
//
//    /**
//     * 检查完订单后的后续操作
//     *
//     * @param orderDto      预检查的订单
//     */
//    void postCheckOrder(OrderDto orderDto);

    /**
     * 生成订单
     * @param orderDto
     * @return 返回生成的订单
     */
    Order createOrder(OrderDto orderDto);

    /**
     * 对指定订单列表的商品进行排名 从高到低
     *
     * @param orderList
     * @return
     */
    List<Map.Entry<String, Integer>> getGoodsSales(List<Order> orderList);
}
