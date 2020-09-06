package net.novaborn.takeaway.store.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.novaborn.takeaway.store.entity.Store;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @author xiaohun
 * @since 2019-09-20
 */
public interface IStoreDao extends BaseMapper<Store> {
    /**
     * 分页获取店铺列表
     *
     * @param page 分页实例
     * @param args  name        名称
     * @return 店铺列表
     */
    IPage<Store> getListByPage(@Param("page") Page page, @Param("args") Map args);
}
