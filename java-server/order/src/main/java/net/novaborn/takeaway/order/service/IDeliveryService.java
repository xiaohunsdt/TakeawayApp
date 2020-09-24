package net.novaborn.takeaway.order.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import net.novaborn.takeaway.order.entity.Delivery;

import java.util.Map;
import java.util.Optional;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xiaohun
 * @since 2019-09-20
 */
public interface IDeliveryService extends IService<Delivery> {
    /**
     * 根据订单id获取配送实体类
     *
     * @param orderId 订单Id
     * @return
     */
    Optional<Delivery> getByOrderId(Long orderId);

    /**
     * 分页获取配送记录表
     *
     * @param page 分页实例
     * @param args adminId 管理员ID
     *             startDate endDate 范围时间
     * @return 优惠卷列表
     */
    IPage<Delivery> getDeliveryListByPage(Page page, Map args);
}
