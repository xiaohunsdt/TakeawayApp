package net.novaborn.takeaway.coupon.services.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.novaborn.takeaway.coupon.dao.ICouponLogDao;
import net.novaborn.takeaway.coupon.entity.Coupon;
import net.novaborn.takeaway.coupon.entity.CouponLog;
import net.novaborn.takeaway.coupon.services.ICouponLogService;
import net.novaborn.takeaway.order.entity.Order;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaohun
 * @since 2019-09-20
 */
@Service
public class CouponLogService extends ServiceImpl<ICouponLogDao, CouponLog> implements ICouponLogService {
    @Override
    public boolean makeNewCouponLog(Order order, Coupon coupon) {
        CouponLog couponLog = new CouponLog();
        couponLog.setUserId(order.getUserId());
        couponLog.setCouponId(coupon.getId());
        couponLog.setOrderId(order.getId());
        couponLog.setCouponAmount(order.getDiscountedPrices());
        couponLog.setOrderOriginalAmount(order.getAllPrice());
        couponLog.setOrderFinalAmount(order.getRealPrice());
        return couponLog.insert();
    }

    @Override
    public List<CouponLog> getLogListByCouponId(String couponId) {
        return this.baseMapper.getLogListByCouponId(couponId);
    }

    @Override
    public List<CouponLog> getLogListByOrderId(String orderId) {
        return this.baseMapper.getLogListByOrderId(orderId);
    }

    @Override
    public IPage<CouponLog> getLogListByPage(Page page, Map args) {
        return this.baseMapper.getLogListByPage(page, args);
    }
}
