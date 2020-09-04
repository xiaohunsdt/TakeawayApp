package net.novaborn.takeaway.user.web.wrapper;

import net.novaborn.takeaway.common.BaseControllerWrapper;
import net.novaborn.takeaway.common.SpringContextHolder;
import net.novaborn.takeaway.coupon.services.impl.CouponService;

import java.util.Map;

/**
 * 分类包装类
 *
 * @author xiaohun
 */
public class CouponLogWrapper extends BaseControllerWrapper {

    public CouponLogWrapper(Object element) {
        super(element);
    }

    @Override
    protected void warpTheMap(Map<String, Object> map) {
        CouponService couponService = SpringContextHolder.getBean(CouponService.class);

        map.put("couponName", couponService.getById((Long) map.get("couponId")).getCouponName());
    }
}
