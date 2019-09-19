package net.novaborn.takeaway.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.novaborn.takeaway.order.dao.IOrderDao;
import net.novaborn.takeaway.order.entity.Order;
import net.novaborn.takeaway.order.service.IOrderService;
import org.springframework.stereotype.Service;

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

}
