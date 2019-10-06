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
    List<Order> selectByUserId(@Param("userId") String userId, @Param("paymentWay") PaymentWay paymentWay, @Param("orderState") OrderState orderState);

    /**
     * 分页获取订单列表
     * @param page   分页实例
     * @param args   userId 用户ID <br />
     *               paymentWay 支付方式 <br />
     *               orderState 订单状态
     * @return 用户列表
     */
    IPage<Order> getOrderListByPage(@Param("page") Page page, @Param("args") Map args);

    /**
     * 获取当天的订单数量
     * @return 当天的订单数量
     */
    int getOrderCountToday();
}
