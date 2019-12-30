package net.novaborn.takeaway.coupon.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import net.novaborn.takeaway.coupon.entity.Coupon;
import net.novaborn.takeaway.coupon.entity.CouponTemplate;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xiaohun
 * @since 2019-09-20
 */
public interface ICouponService extends IService<Coupon> {
    /**
     * 根据 用户ID 获取优惠卷
     *
     * @param userId          用户ID
     * @param onlyShowUseAble 只显示可用优惠卷
     * @return 优惠卷列表
     */
    List<Coupon> getCouponListByUserId(String userId, boolean onlyShowUseAble);

    /**
     * 分页获取优惠卷列表
     *
     * @param page 分页实例
     * @param args userId 用户ID
     *             startDate endDate 范围时间
     * @return 优惠卷列表
     */
    IPage<Coupon> getCouponListByPage(Page page, Map args);

    /**
     * 生成优惠卷
     * @param template 优惠卷模板
     * @param userIds 用户ID列表
     */
    void makeCoupon(CouponTemplate template, List<String> userIds);

    /**
     * 生成优惠卷
     * @param template 优惠卷模板
     * @param userId 用户ID
     */
    void makeCoupon(CouponTemplate template, String userId);
}
