package net.novaborn.takeaway.coupon.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.novaborn.takeaway.coupon.entity.Coupon;
import net.novaborn.takeaway.coupon.entity.CouponTemplate;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @author xiaohun
 * @since 2019-09-20
 */
public interface ICouponTemplateDao extends BaseMapper<CouponTemplate> {
    /**
     * 分页获取优惠卷模板列表
     *
     * @param page 分页实例
     * @param args couponType 优惠卷类型
     * @return 优惠卷模板列表
     */
    IPage<Coupon> getCouponTemplateListByPage(@Param("page") Page page, @Param("args") Map args);
}
