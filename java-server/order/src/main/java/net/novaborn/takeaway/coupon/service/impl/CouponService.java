package net.novaborn.takeaway.coupon.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
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
    public int getDiscountMoney(Coupon coupon, int price) {
        return 0;
    }
}
