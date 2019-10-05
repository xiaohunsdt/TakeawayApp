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
    public List<Order> selectByUserId(String userId) {
        return this.selectByUserId(userId, null, null);
    }

    @Override
    public List<Order> selectByUserId(String userId, PaymentWay paymentWay, OrderState orderState) {
        return this.baseMapper.selectByUserId(userId, paymentWay, orderState);
    }

    @Override
    public IPage<Order> getOrderListByPage(Page page, Map args) {
        return this.baseMapper.getOrderListByPage(page,args);
    }
}
