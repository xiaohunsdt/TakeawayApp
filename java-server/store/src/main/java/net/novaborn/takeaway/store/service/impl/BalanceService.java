package net.novaborn.takeaway.store.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.store.dao.IBalanceDao;
import net.novaborn.takeaway.store.entity.Balance;
import net.novaborn.takeaway.store.exception.StoreBalanceException;
import net.novaborn.takeaway.store.exception.StoreBalanceExceptionEnum;
import net.novaborn.takeaway.store.service.IBalanceService;
import org.springframework.aop.framework.AopContext;
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
public class BalanceService extends ServiceImpl<IBalanceDao, Balance> implements IBalanceService {
    @Override
    public boolean updateById(Balance entity) {
        if (!super.updateById(entity)) {
            throw new StoreBalanceException(StoreBalanceExceptionEnum.UPDATE_FAILED);
        }

        return true;
    }

    @Override
    public long add(Long storeId, Long money) {
        Balance balance = this.getById(storeId);
        if (balance == null) {
            throw new StoreBalanceException(StoreBalanceExceptionEnum.NOT_EXIST);
        }

        balance.setMoney(balance.getMoney() + money);
        ((BalanceService) AopContext.currentProxy()).updateById(balance);
        return balance.getMoney();
    }

    @Override
    public long sub(Long storeId, Long money) {
        Balance balance = this.getById(storeId);
        if (balance == null) {
            throw new StoreBalanceException(StoreBalanceExceptionEnum.NOT_EXIST);
        }

        balance.setMoney(balance.getMoney() - money);
        ((BalanceService) AopContext.currentProxy()).updateById(balance);
        return balance.getMoney();
    }
}
