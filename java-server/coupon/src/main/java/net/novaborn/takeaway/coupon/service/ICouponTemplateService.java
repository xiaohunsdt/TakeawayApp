package net.novaborn.takeaway.coupon.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import net.novaborn.takeaway.coupon.entity.Coupon;
import net.novaborn.takeaway.coupon.entity.CouponTemplate;

import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xiaohun
 * @since 2019-09-20
 */
public interface ICouponTemplateService extends IService<CouponTemplate> {
    /**
     * 分页获取优惠卷模板列表
     *
     * @param page 分页实例
     * @param args couponType 优惠卷类型
     * @return 优惠卷模板列表
     */
    IPage<Coupon> getCouponTemplateListByPage(Page page, Map args);
}
