package net.novaborn.takeaway.coupon.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.novaborn.takeaway.coupon.entity.Coupon;
import net.novaborn.takeaway.coupon.entity.CouponLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author xiaohun
 * @since 2019-09-20
 */
public interface ICouponLogDao extends BaseMapper<CouponLog> {
    /**
     * 根据优惠卷Id 获取优惠卷使用记录
     * @param couponId
     * @return
     */
    List<CouponLog> getLogListByCouponId(@Param("couponId") Long couponId);

    /**
     * 根据 订单ID 获取优惠卷记录
     *
     * @param orderId          订单ID
     * @return 优惠卷记录列表
     */
    List<CouponLog> getLogListByOrderId(@Param("orderId") Long orderId);

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
    IPage<CouponLog> getLogListByPage(@Param("page") Page page, @Param("args") Map args);
}
