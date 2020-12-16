package net.novaborn.takeaway.order.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import net.novaborn.takeaway.order.entity.RefundLog;

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
public interface IRefundLogService extends IService<RefundLog> {
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
    IPage<RefundLog> getListByPage(Page page, Map args);

    /**
     * 根据订单Id获取退款记录
     * @param orderId
     * @return
     */
    List<RefundLog> getRefundLogsByOrderId(Long orderId);

    /**
     * 根据订单Id获取退款记录的总金额
     * @param orderId
     * @return
     */
    int getAllRefundMoneyByOrderId(Long orderId);

    /**
     * 根据订单Id获取退款记录的总数量
     * @param orderId
     * @return
     */
    int getRefundLogCountByOrderId(Long orderId);
}
