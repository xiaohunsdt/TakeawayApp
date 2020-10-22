package net.novaborn.takeaway.store.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import net.novaborn.takeaway.store.entity.BalanceLog;
import net.novaborn.takeaway.store.entity.Store;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xiaohun
 * @since 2019-09-20
 */
public interface IBalanceLogService extends IService<BalanceLog> {
    /**
     * 分页获取资金记录列表
     *
     * @param page 分页实例
     * @param args  storeId             店铺Id
     *              startDate endDate   范围时间
     * @return 资金记录列表
     */
    IPage<BalanceLog> getListByPage(Page page, Map args);

    /**
     * 添加一条记录
     * @param storeId
     * @param money
     * @param afterMoney
     * @param eventType
     * @param args
     * @return
     */
    boolean setMoneyLog(Long storeId, Long money,Long afterMoney, int eventType, Object... args);
}
