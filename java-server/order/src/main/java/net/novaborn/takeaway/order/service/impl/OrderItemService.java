package net.novaborn.takeaway.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.novaborn.takeaway.order.dao.IOrderDao;
import net.novaborn.takeaway.order.dao.IOrderItemDao;
import net.novaborn.takeaway.order.entity.Order;
import net.novaborn.takeaway.order.entity.OrderItem;
import net.novaborn.takeaway.order.service.IOrderItemService;
import net.novaborn.takeaway.order.service.IOrderService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaohun
 * @since 2019-09-20
 */
@Service
public class OrderItemService extends ServiceImpl<IOrderItemDao, OrderItem> implements IOrderItemService {
    @Override
    public List<OrderItem> selectByOrderId(String orderId) {
        return this.baseMapper.selectByOrderId(orderId);
    }

    @Override
    public boolean removeByOrderId(String orderId) {
        return this.baseMapper.removeByOrderId(orderId);
    }
}
