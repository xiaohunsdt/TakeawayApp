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
     *
     * @param userId 用户ID
     * @return 用户的订单列表
     */
    List<Order> getOrderListByUserId(String userId);

    /**
     * 获取指定用户的订单
     *
     * @param userId        用户ID
     * @param paymentWay    支付方式
     * @param orderState    订单状态
     * @param isShowDeleted 是否显示已被删除的账户
     * @return 筛选后的指定用户订单列表
     */
    List<Order> getOrderListByUserId(String userId,
                                     PaymentWay paymentWay,
                                     OrderState orderState,
                                     boolean isShowDeleted);

    /**
     * 分页获取订单列表
     *
     * @param page 分页实例
     * @param args userIds 用户IDs <br />
     *             orderId 订单ID <br />
     *             number 编号 <br />
     *             orderState 订单状态 <br />
     *             paymentWay 支付方式 <br />
     *             startDate endDate 范围时间
     * @return 订单列表
     */
    IPage<Order> getOrderListByPage(Page page, Map args);

    /**
     * 获取当前待接单的数量
     *
     * @return
     */
    int getWaitingReceiveOrderCount();

    /**
     * 用户端分页获取订单列表
     *
     * @param page 分页实例
     * @param args userId 用户ID <br />
     *             orderState 订单状态
     *             - waitPay 等待支付
     *             - waitEat 等待就餐
     *             - waitComment 等待评价
     *             - refund 退款
     * @return 订单列表
     */
    IPage<Order> getOrderListByPageU(Page page, Map args);

    /**
     * 用户端获取指定订单类型的数量
     *
     * @param args userId 用户ID <br />
     *             orderState 订单状态
     *             - waitPay 等待支付
     *             - waitEat 等待就餐
     *             - waitComment 等待评价
     *             - refund 退款
     * @return 订单数量
     */
    int getOrderCountByStateU(Map args);

    /**
     * 获取当天的订单数量
     *
     * @return 当天的订单数量
     */
    int getOrderCountToday();
}
