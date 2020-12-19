package net.novaborn.takeaway.order.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.novaborn.takeaway.order.dao.IRefundLogDao;
import net.novaborn.takeaway.order.entity.RefundLog;
import net.novaborn.takeaway.order.service.IRefundLogService;
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
public class RefundLogService extends ServiceImpl<IRefundLogDao, RefundLog> implements IRefundLogService {
    @Override
    public IPage<RefundLog> getListByPage(Page page, Map args) {
        return this.baseMapper.getListByPage(page, args);
    }

    @Override
    public List<RefundLog> getRefundLogsByOrderId(Long orderId) {
        return this.baseMapper.getRefundLogsByOrderId(orderId);
    }

    @Override
    public int getAllRefundMoneyByOrderId(Long orderId) {
        return this.baseMapper.getAllRefundMoneyByOrderId(orderId);
    }

    @Override
    public int getRefundLogCountByOrderId(Long orderId) {
        return this.baseMapper.getRefundLogCountByOrderId(orderId);
    }
}
