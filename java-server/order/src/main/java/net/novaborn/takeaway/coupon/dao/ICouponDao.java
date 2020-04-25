package net.novaborn.takeaway.coupon.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.novaborn.takeaway.coupon.entity.Coupon;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author xiaohun
 * @since 2019-09-20
 */
public interface ICouponDao extends BaseMapper<Coupon> {
    /**
     * 根据 用户ID 获取优惠卷
     *
     * @param userId          用户ID
     * @return 优惠卷列表
     */
    List<Coupon> getCouponListByUserId(@Param("userId") String userId, @Param("onlyShowUseAble") boolean onlyShowUseAble);

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
}
