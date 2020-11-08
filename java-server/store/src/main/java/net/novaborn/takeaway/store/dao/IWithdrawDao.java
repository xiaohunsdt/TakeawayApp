package net.novaborn.takeaway.store.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.novaborn.takeaway.store.entity.BalanceLog;
import net.novaborn.takeaway.store.entity.Withdraw;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @author xiaohun
 * @since 2019-09-20
 */
public interface IWithdrawDao extends BaseMapper<Withdraw> {
    /**
     * 分页获取提现记录列表
     *
     * @param page 分页实例
     * @param args  storeId             店铺Id
     *              state               状态
     *              startDate endDate   范围时间
     * @return 提现记录列表
     */
    IPage<Withdraw> getListByPage(@Param("page") Page page, @Param("args") Map args);
}
