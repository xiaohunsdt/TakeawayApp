package net.novaborn.takeaway.coupon.services;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import net.novaborn.takeaway.admin.AdminApplication;
import net.novaborn.takeaway.coupon.entity.Coupon;
import net.novaborn.takeaway.coupon.enums.CouponState;
import net.novaborn.takeaway.coupon.services.impl.CouponService;
import net.novaborn.takeaway.user.entity.Address;
import net.novaborn.takeaway.user.service.impl.AddressService;
import net.novaborn.takeaway.user.service.impl.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AdminApplication.class})
public class CouponServiceTest {
    @Autowired
    CouponService couponService;

    @Autowired
    UserService userService;

    @Autowired
    AddressService addressService;

    @Test
    public void getWillExpireUserTest() {
        // 获取所有状态为未使用的优惠卷
        QueryWrapper queryWrapper = new QueryWrapper<Coupon>().eq("state", 0).eq("deleted", 0);
        List<Coupon> couponList = couponService.list(queryWrapper);
        Date current = new Date();
        String phone = couponList.parallelStream()
                .filter(coupon -> {
                    // 把已经过期的全部过滤出来
                    if (coupon.getExpireDate() != null && DateUtil.compare(coupon.getExpireDate(), current) <= 0) {
                        coupon.setState(CouponState.EXPIRED);
                        couponService.updateById(coupon);
                        return false;
                    }
                    return true;
                })
                .filter(coupon -> {
                    long remindSecond = 0;
                    if (coupon.getExpireDate() != null) {
                        remindSecond = DateUtil.between(new Date(), coupon.getExpireDate(), DateUnit.SECOND, false);
                    }

                    // 24之内即将过期的话就丢入队列等待过期
                    return remindSecond > 0 && remindSecond < 24 * 60 * 60;
                })
                .map(coupon -> {
                    Optional<Address> address = addressService.selectDefaultAddressByUserId(coupon.getUserId());
                    if (address.isPresent()) {
                        return address.get().getPhone();
                    } else {
                        return null;
                    }
                }).distinct().collect(Collectors.joining(","));
        System.out.println(phone);
    }
}

