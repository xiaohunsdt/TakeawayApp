package net.novaborn.takeaway.coupon.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.novaborn.takeaway.coupon.dao.ICouponDao;
import net.novaborn.takeaway.coupon.entity.Coupon;
import net.novaborn.takeaway.coupon.service.ICouponService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaohun
 * @since 2018-07-12
 */
@Service
public class CouponService extends ServiceImpl<ICouponDao, Coupon> implements ICouponService {

}
