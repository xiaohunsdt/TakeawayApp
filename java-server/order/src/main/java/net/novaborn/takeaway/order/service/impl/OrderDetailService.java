package net.novaborn.takeaway.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.order.dao.IOrderDetailDao;
import net.novaborn.takeaway.order.entity.OrderDetail;
import net.novaborn.takeaway.order.service.IOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaohun
 * @since 2019-09-20
 */
@Slf4j
@Setter(onMethod_ = {@Autowired})
@Service
public class OrderDetailService extends ServiceImpl<IOrderDetailDao, OrderDetail> implements IOrderDetailService {

}
