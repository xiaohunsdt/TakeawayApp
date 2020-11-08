package net.novaborn.takeaway.store.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.common.exception.SysException;
import net.novaborn.takeaway.store.dao.IWithdrawDao;
import net.novaborn.takeaway.store.entity.Balance;
import net.novaborn.takeaway.store.entity.Withdraw;
import net.novaborn.takeaway.store.exception.StoreBalanceExceptionEnum;
import net.novaborn.takeaway.store.service.IWithdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class WithdrawService extends ServiceImpl<IWithdrawDao, Withdraw> implements IWithdrawService {

    private BalanceService balanceService;

    private BalanceLogService balanceLogService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void apply(Withdraw withdraw) {
        Balance balance = balanceService.getById(withdraw.getStoreId());
        if (balance.getMoney() < withdraw.getMoney()) {
            throw new SysException(StoreBalanceExceptionEnum.HAVE_NO_ENOUGH_BALANCE);
        }

        withdraw.setFee((long) (withdraw.getMoney() * 0.02));

        if (withdraw.insert()) {
            balance.setMoney(balance.getMoney() - withdraw.getMoney());
            balanceService.updateById(balance);

            balanceLogService.setMoneyLog(withdraw.getStoreId(), withdraw.getMoney(), balance.getMoney(), 3, withdraw.getMoney(), withdraw.getFee());
        }
    }

    @Override
    public IPage<Withdraw> getListByPage(Page page, Map args) {
        return this.baseMapper.getListByPage(page, args);
    }
}
