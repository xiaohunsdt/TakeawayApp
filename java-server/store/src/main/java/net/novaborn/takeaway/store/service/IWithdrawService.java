package net.novaborn.takeaway.store.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import net.novaborn.takeaway.store.entity.Store;
import net.novaborn.takeaway.store.entity.Withdraw;

import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xiaohun
 * @since 2019-09-20
 */
public interface IWithdrawService extends IService<Withdraw> {

    /**
     * 申请提现
     *
     * @param money
     * @param storeId
     */
    void apply(int money, long storeId);

    /**
     * 分页获取提现记录列表
     *
     * @param page 分页实例
     * @param args  storeId             店铺Id
     *              state               状态
     *              startDate endDate   范围时间
     * @return 提现记录列表
     */
    IPage<Withdraw> getListByPage(Page page, Map args);
}
