package net.novaborn.takeaway.store.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.store.dao.IBalanceLogDao;
import net.novaborn.takeaway.store.entity.BalanceLog;
import net.novaborn.takeaway.store.service.IBalanceLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


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
    @Override
    public IPage<BalanceLog> getListByPage(Page page, Map args) {
        return this.baseMapper.getListByPage(page, args);
    }

    @Override
    public boolean setMoneyLog(Long storeId, Long money, Long afterMoney, int eventType, Object... args) {
        BalanceLog balanceLog = new BalanceLog();
        balanceLog.setStoreId(storeId);
        balanceLog.setMoney(money);
        balanceLog.setEventType(eventType);
        balanceLog.setAfterMoney(afterMoney);
        balanceLog.setTypeTextToPs(eventType, args);
        return balanceLog.insert();
    }
}