package net.novaborn.takeaway.order.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.novaborn.takeaway.order.entity.Delivery;
import net.novaborn.takeaway.order.entity.OrderItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author xiaohun
 * @since 2019-09-20
 */
public interface IDeliveryDao extends BaseMapper<Delivery> {
    /**
     * 根据订单id获取配送实体类
     *
     * @param orderId       订单Id
     * @return
     */
    Optional<Delivery> getByOrderId(@Param("orderId") Long orderId);

    /**
     * 分页获取配送记录表
     *
     * @param page 分页实例
     * @param args adminId 管理员ID
     *             startDate endDate 范围时间
     * @return 优惠卷列表
     */
    IPage<Delivery> getDeliveryListByPage(@Param("page") Page page, @Param("args") Map args);
}
