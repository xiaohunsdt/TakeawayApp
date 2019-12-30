package net.novaborn.takeaway.coupon.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.novaborn.takeaway.coupon.dao.ICouponTemplateDao;
import net.novaborn.takeaway.coupon.entity.Coupon;
import net.novaborn.takeaway.coupon.entity.CouponTemplate;
import net.novaborn.takeaway.coupon.service.ICouponTemplateService;
import org.springframework.stereotype.Service;

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
public class CouponTemplateService extends ServiceImpl<ICouponTemplateDao, CouponTemplate> implements ICouponTemplateService {

    @Override
    public IPage<Coupon> getCouponTemplateListByPage(Page page, Map args) {
        return this.baseMapper.getCouponTemplateListByPage(page,args);
    }
}
