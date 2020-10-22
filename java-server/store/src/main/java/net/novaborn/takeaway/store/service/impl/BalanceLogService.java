package net.novaborn.takeaway.store.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.store.dao.IBalanceLogDao;
import net.novaborn.takeaway.store.entity.BalanceLog;
import net.novaborn.takeaway.store.service.IBalanceLogService;
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
@Service
@Setter(onMethod_ = {@Autowired})
public class BalanceLogService extends ServiceImpl<IBalanceLogDao, BalanceLog> implements IBalanceLogService {
}
