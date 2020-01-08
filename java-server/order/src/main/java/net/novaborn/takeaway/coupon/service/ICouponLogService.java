package net.novaborn.takeaway.coupon.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import net.novaborn.takeaway.coupon.entity.Coupon;
import net.novaborn.takeaway.coupon.entity.CouponLog;

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
     * 根据 订单ID 获取优惠卷记录
     *
     * @param orderId          订单ID
     * @return 优惠卷记录列表
     */
    List<Coupon> getCouponLogListByOrderId(String orderId);

    /**
     * 分页获取优惠卷记录列表
     *
     * @param page 分页实例
     * @param args userId 用户ID
     *             startDate endDate 范围时间
     * @return 优惠卷记录列表
     */
    IPage<Coupon> getCouponLogListByPage(Page page, Map args);
}
