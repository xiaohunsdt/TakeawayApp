package net.novaborn.takeaway.coupon.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import net.novaborn.takeaway.coupon.entity.Coupon;
import net.novaborn.takeaway.coupon.entity.CouponLog;
import net.novaborn.takeaway.order.entity.Order;

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
public interface ICouponLogService extends IService<CouponLog> {

    /**
     * 创建一个优惠卷记录
     * @param order
     * @param coupon
     * @return
     */
    boolean makeNewCouponLog(Order order, Coupon coupon);

    /**
     * 根据优惠卷Id 获取优惠卷使用记录
     *
     * @param couponId
     * @return
     */
    List<CouponLog> getLogListByCouponId(String couponId);

    /**
     * 根据 订单ID 获取优惠卷记录
     *
     * @param orderId 订单ID
     * @return 优惠卷记录列表
     */
    List<CouponLog> getLogListByOrderId(String orderId);

    /**
     * 分页获取优惠卷使用列表
     *
     * @param page 分页实例
     * @param args userId 用户ID
     *             couponId 优惠卷ID
     *             orderId 订单ID
     *             startDate endDate 范围时间
     * @return 优惠卷列表
     */
    IPage<CouponLog> getLogListByPage(Page page, Map args);
}
