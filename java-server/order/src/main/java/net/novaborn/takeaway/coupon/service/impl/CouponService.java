package net.novaborn.takeaway.coupon.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.novaborn.takeaway.common.exception.SysException;
import net.novaborn.takeaway.coupon.dao.ICouponDao;
import net.novaborn.takeaway.coupon.entity.Coupon;
import net.novaborn.takeaway.coupon.entity.CouponTemplate;
import net.novaborn.takeaway.coupon.enums.CouponState;
import net.novaborn.takeaway.coupon.exception.CouponExceptionEnum;
import net.novaborn.takeaway.coupon.service.ICouponService;
import net.novaborn.takeaway.coupon.util.CouponUtil;
import net.novaborn.takeaway.goods.entity.Goods;
import net.novaborn.takeaway.goods.service.impl.GoodsService;
import net.novaborn.takeaway.order.entity.Order;
import net.novaborn.takeaway.order.entity.OrderItem;
import net.novaborn.takeaway.order.enums.PaymentWay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    @Autowired
    GoodsService goodsService;

    @Override
    public List<Coupon> getCouponListByUserId(String userId, boolean onlyShowUseAble) {
        return this.baseMapper.getCouponListByUserId(userId, onlyShowUseAble);
    }

    @Override
    public IPage<Coupon> getCouponListByPage(Page page, Map args) {
        return this.baseMapper.getCouponListByPage(page, args);
    }

    @Override
    public void generateCoupon(CouponTemplate template, List<String> userIds) {
        this.generateCoupon(template, userIds, 1);
    }

    @Override
    public void generateCoupon(CouponTemplate template, List<String> userIds, Integer count) {
        userIds.parallelStream().forEach(userId -> this.generateCoupon(template, userId, count));
    }

    @Override
    public void generateCoupon(CouponTemplate template, String userId) {
        this.generateCoupon(template, userId, 1);
    }

    @Override
    public void generateCoupon(CouponTemplate template, String userId, Integer count) {
        for (int i = 0; i < count; i++) {
            Coupon target = new Coupon();
            BeanUtil.copyProperties(template, target, CopyOptions.create().setIgnoreNullValue(true));

            target.setId(null);
            target.setCreateDate(null);
            target.setDeleted(null);
            target.setUserId(userId);
            if (template.getExpireDays() > 0) {
                target.setExpireDate(DateUtil.date().offset(DateField.DAY_OF_MONTH, template.getExpireDays()));
            }
            target.insert();
        }
    }

    @Override
    public Order getDiscountMoney(Order order, List<OrderItem> orderItems, String couponId) {
        // 刷卡除外
        if (order.getPaymentWay() == PaymentWay.CREDIT_CARD) {
            SysException sysException = new SysException(CouponExceptionEnum.UNSUPPORT_PAYMENT_WAY);
            sysException.setMessage("此优惠卷不支持刷卡支付");
            throw sysException;
        }

        Optional<Coupon> coupon = Optional.ofNullable(this.getById(couponId));
        // 没有这个优惠卷
        coupon.orElseThrow(() -> new SysException(CouponExceptionEnum.HAVE_NO_COUPON));

        // 此优惠卷不可用
        if (coupon.get().getState() != CouponState.UN_USE) {
            throw new SysException(CouponExceptionEnum.COUPON_CAN_NOT_BE_USED);
        }

        Map<String, List<String>> couponRules = CouponUtil.getCouponRule(coupon.get());
        int allPrice = orderItems.parallelStream()
                .map(orderItem -> orderItem.getGoodsPrice() * orderItem.getGoodsCount())
                .reduce(0, (x, y) -> x + y);

        int needDisCountPrice = orderItems.parallelStream()
                .filter(orderItem -> {
                    Goods goods = goodsService.getById(orderItem.getGoodsId());
                    return CouponUtil.isDiscount(goods, couponRules);
                })
                .map(orderItem -> orderItem.getGoodsPrice() * orderItem.getGoodsCount())
                .reduce(0, (x, y) -> x + y);

        // 设置折扣
        int discount = 0;
        int discountedMoney = 0;
        if (needDisCountPrice > 0) {
            switch (coupon.get().getCouponType()) {
                case MONEY:
                    if (coupon.get().getMinimumMoney() > 0) {
                        if (coupon.get().getMinimumMoney() > needDisCountPrice) {
                            throw new SysException(CouponExceptionEnum.DO_NOT_MEET_MINI_AMOUNT_REQUIREMENTS);
                        }
                    }
                    discountedMoney = coupon.get().getCouponMoney();
                    break;
                case DISCOUNT:
                    if (coupon.get().getMinimumMoney() > needDisCountPrice) {
                        throw new SysException(CouponExceptionEnum.DO_NOT_MEET_MINI_AMOUNT_REQUIREMENTS);
                    }
                    discount = coupon.get().getCouponDiscount();
                    discountedMoney = needDisCountPrice * (100 - discount) / 100;
                    break;
                default:
                    break;
            }
        }

        // 设置折扣信息
        if (discount > 0) {
            order.setDiscount((short) discount);
        }
        order.setDiscountedPrices(discountedMoney);
        order.setRealPrice(allPrice - discountedMoney);
        return order;
    }
}
