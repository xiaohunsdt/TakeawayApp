package net.novaborn.takeaway.store.service;


import com.baomidou.mybatisplus.extension.service.IService;
import net.novaborn.takeaway.store.entity.Balance;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xiaohun
 * @since 2019-09-20
 */
public interface IBalanceService extends IService<Balance> {

    /**
     * 添加资金
     * @param storeId
     * @param money
     * @return 更新后的金额
     */
    long add(Long storeId, Long money);

    /**
     * 减少资金
     * @param storeId
     * @param money
     * @return 更新后的金额
     */
    long sub(Long storeId, Long money);
}
