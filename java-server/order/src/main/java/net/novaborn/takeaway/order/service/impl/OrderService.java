package net.novaborn.takeaway.order.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.novaborn.takeaway.order.dao.IOrderDao;
import net.novaborn.takeaway.order.entity.Order;
import net.novaborn.takeaway.order.enums.OrderState;
import net.novaborn.takeaway.order.enums.PaymentWay;
import net.novaborn.takeaway.order.service.IOrderService;
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
public class OrderService extends ServiceImpl<IOrderDao, Order> implements IOrderService {
    @Override
    public List<Order> getOrderListByUserId(String userId) {
        return this.getOrderListByUserId(userId, null, null, false);
    }

    @Override
    public List<Order> getOrderListByUserId(String userId, PaymentWay paymentWay, OrderState orderState, boolean isShowDeleted) {
        return this.baseMapper.getOrderListByUserId(userId, paymentWay, orderState, isShowDeleted);
    }

    @Override
    public IPage<Order> getOrderListByPage(Page page, Map args) {
        return this.baseMapper.getOrderListByPage(page, args);
    }


    @Override
    public int getWaitingReceiveOrderCount() {
        return this.baseMapper.getWaitingReceiveOrderCount();
    }

    @Override
    public IPage<Order> getOrderListByPageU(Page page, Map args) {
        return this.baseMapper.getOrderListByPageU(page, args);
    }

    @Override
    public int getOrderCountByStateU(Map args) {
        return this.baseMapper.getOrderCountByStateU(args);
    }

    @Override
    public int getOrderCountToday() {
        return this.baseMapper.getOrderCountToday();
    }
}
