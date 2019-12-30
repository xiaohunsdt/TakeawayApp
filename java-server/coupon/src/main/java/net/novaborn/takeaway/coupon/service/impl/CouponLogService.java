package net.novaborn.takeaway.coupon.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.novaborn.takeaway.coupon.dao.ICouponLogDao;
import net.novaborn.takeaway.coupon.entity.Coupon;
import net.novaborn.takeaway.coupon.entity.CouponLog;
import net.novaborn.takeaway.coupon.service.ICouponLogService;
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
    public List<Coupon> getCouponLogListByOrderId(String orderId) {
        return null;
    }

    @Override
    public IPage<Coupon> getCouponLogListByPage(Page page, Map args) {
        return null;
    }
}
