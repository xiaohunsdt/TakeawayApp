package net.novaborn.takeaway.order.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.novaborn.takeaway.order.entity.Order;
import net.novaborn.takeaway.order.entity.OrderItem;
import net.novaborn.takeaway.order.entity.RefundLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author xiaohun
 * @since 2019-09-20
 */
public interface IRefundLogDao extends BaseMapper<RefundLog> {
    /**
     * 分页获取列表
     *
     * @param page 分页实例
     * @param args userIds 用户IDs <br />
     *             orderId 订单ID <br />
     *             state 订单状态 <br />
     *             paymentWay 支付方式 <br />
     *             startDate endDate 范围时间
     * @return 分页列表
     */
    IPage<RefundLog> getListByPage(@Param("page") Page page, @Param("args") Map args);

    /**
     * 根据订单Id获取退款记录
     * @param orderId
     * @return
     */
    List<RefundLog> getRefundLogsByOrderId(@Param("orderId") Long orderId);

    /**
     * 根据订单Id获取退款记录的总金额
     * @param orderId
     * @return
     */
    int getAllRefundMoneyByOrderId(@Param("orderId") Long orderId);

    /**
     * 根据订单Id获取退款记录的总数量
     * @param orderId
     * @return
     */
    int getRefundLogCountByOrderId(@Param("orderId") Long orderId);
}
