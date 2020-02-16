package net.novaborn.takeaway.coupon.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.novaborn.takeaway.coupon.entity.CouponLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
    List<CouponLog> getLogListByCouponId(@Param("couponId") String couponId);

    /**
     * 根据 订单ID 获取优惠卷记录
     *
     * @param orderId          订单ID
     * @return 优惠卷记录列表
     */
    List<CouponLog> getLogListByOrderId(@Param("orderId") String orderId);
}
