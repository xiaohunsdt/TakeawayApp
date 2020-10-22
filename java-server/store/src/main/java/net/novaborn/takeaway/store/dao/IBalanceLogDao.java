package net.novaborn.takeaway.store.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.novaborn.takeaway.store.entity.BalanceLog;
import net.novaborn.takeaway.store.entity.Store;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @author xiaohun
 * @since 2019-09-20
 */
public interface IBalanceLogDao extends BaseMapper<BalanceLog> {
    /**
     * 分页获取资金记录列表
     *
     * @param page 分页实例
     * @param args  storeId             店铺Id
     *              type                1收入 0支出
     *              startDate endDate   范围时间
     * @return 资金记录列表
     */
    IPage<BalanceLog> getListByPage(@Param("page") Page page, @Param("args") Map args);
}
