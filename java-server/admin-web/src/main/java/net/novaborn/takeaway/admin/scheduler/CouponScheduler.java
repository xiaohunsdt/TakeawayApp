package net.novaborn.takeaway.admin.scheduler;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.coupon.entity.Coupon;
import net.novaborn.takeaway.coupon.enums.CouponState;
import net.novaborn.takeaway.coupon.services.impl.CouponService;
import net.novaborn.takeaway.mq.sender.CouponExpiredSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Slf4j
@Component
@Setter(onMethod_ = {@Autowired})
public class CouponScheduler {
    private CouponService couponService;

    private CouponExpiredSender couponExpiredSender;

    /**
     * 优惠卷过期周期 每日0:01分时计算
     */
    @Scheduled(cron = "0 1 0 * * ?")
    public void couponExpired() {
        // 获取所有状态为未使用的优惠卷
        QueryWrapper queryWrapper = new QueryWrapper<Coupon>().eq("state", 0).eq("deleted",0);
        List<Coupon> couponList = couponService.list(queryWrapper);
        Date current = new Date();
        couponList.parallelStream()
                .filter(coupon -> {
                    // 把已经过期的全部过滤出来
                    if (coupon.getExpireDate() != null && DateUtil.compare(coupon.getExpireDate(), current) <= 0) {
                        coupon.setState(CouponState.EXPIRED);
                        couponService.updateById(coupon);
                        return false;
                    }
                    return true;
                })
                .forEach(coupon -> {
                    long remindSecond = 0;
                    if (coupon.getExpireDate() != null) {
                        remindSecond = DateUtil.between(new Date(), coupon.getExpireDate(), DateUnit.SECOND, false);
                    }

                    // 24之内即将过期的话就丢入队列等待过期
                    if (remindSecond > 0 && remindSecond < 24 * 60 * 60) {
                        couponExpiredSender.send(coupon, (int) remindSecond);
                    }

                    // 消息到期通知
                    // ...
                });
    }
}
