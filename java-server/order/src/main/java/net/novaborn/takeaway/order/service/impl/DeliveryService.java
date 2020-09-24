package net.novaborn.takeaway.order.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.novaborn.takeaway.order.dao.ICommentDao;
import net.novaborn.takeaway.order.dao.IDeliveryDao;
import net.novaborn.takeaway.order.entity.Comment;
import net.novaborn.takeaway.order.entity.Delivery;
import net.novaborn.takeaway.order.service.ICommentService;
import net.novaborn.takeaway.order.service.IDeliveryService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaohun
 * @since 2019-09-20
 */
@Service
public class DeliveryService extends ServiceImpl<IDeliveryDao, Delivery> implements IDeliveryService {
    @Override
    public Optional<Delivery> getByOrderId(Long orderId) {
        return this.baseMapper.getByOrderId(orderId);
    }

    @Override
    public IPage<Delivery> getDeliveryListByPage(Page page, Map args) {
        return this.baseMapper.getDeliveryListByPage(page,args);
    }
}
