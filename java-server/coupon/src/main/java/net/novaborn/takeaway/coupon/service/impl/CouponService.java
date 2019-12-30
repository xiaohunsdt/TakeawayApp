package net.novaborn.takeaway.coupon.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.novaborn.takeaway.coupon.dao.ICouponDao;
import net.novaborn.takeaway.coupon.entity.Coupon;
import net.novaborn.takeaway.coupon.entity.CouponTemplate;
import net.novaborn.takeaway.coupon.service.ICouponService;
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
public class CouponService extends ServiceImpl<ICouponDao, Coupon> implements ICouponService {
    @Override
    public List<Coupon> getCouponListByUserId(String userId, boolean onlyShowUseAble) {
        return null;
    }

    @Override
    public IPage<Coupon> getCouponListByPage(Page page, Map args) {
        return null;
    }

    @Override
    public void makeCoupon(CouponTemplate template, List<String> userIds) {

    }

    @Override
    public void makeCoupon(CouponTemplate template, String userId) {

    }
}
