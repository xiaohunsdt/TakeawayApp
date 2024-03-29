package net.novaborn.takeaway.coupon.services;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import net.novaborn.takeaway.coupon.entity.Coupon;
import net.novaborn.takeaway.coupon.entity.CouponTemplate;
import net.novaborn.takeaway.order.entity.Order;
import net.novaborn.takeaway.order.entity.OrderItem;
import net.novaborn.takeaway.user.entity.User;

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
    List<Coupon> getCouponListByUserId(Long userId, boolean onlyShowUseAble);

    /**
     * 分页获取优惠卷列表
     *
     * @param page 分页实例
     * @param args userId 用户ID
     *             couponType 优惠卷类型
     *             couponState 优惠卷状态
     *             bindState 是否已被用户绑定   0 未绑定    1 已绑定
     *             startDate endDate 范围时间
     * @return 优惠卷列表
     */
    IPage<Coupon> getCouponListByPage(Page page, Map args);

    /**
     * 生成优惠卷
     *
     * @param template 优惠卷模板
     * @param userIds  用户ID列表
     */
    void generateCoupon(CouponTemplate template, List<Long> userIds);

    void generateCoupon(CouponTemplate template, List<Long> userIds, Integer count);

    void generateCoupon(CouponTemplate template, Integer expireDays, Integer count);

    void generateCoupon(CouponTemplate template, List<Long> userIds, Integer expireDays, Integer count);

    /**
     * 生成优惠卷
     *
     * @param template 优惠卷模板
     * @param userId   用户ID
     */
    void generateCoupon(CouponTemplate template, Long userId);

    void generateCoupon(CouponTemplate template, Long userId, Integer count);

    void generateCoupon(CouponTemplate template, Long userId, Integer expireDays, Integer count);

    /**
     * 绑定优惠卷
     * @param userId
     * @param couponId
     * @return
     */
    boolean bindCoupon(Long userId, Long couponId);

    /**
     * 获取优惠金额
     *
     * @param order      订单中必须有优惠卷，支付方式，不然抛出异常
     * @param orderItems 订单项
     * @param couponId   优惠卷ID
     * @return order 一个Order实例,里面包含折扣信息
     */
    Order getDiscountMoney(Order order, List<OrderItem> orderItems, Long couponId);
}
