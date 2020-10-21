package net.novaborn.takeaway.goods.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.novaborn.takeaway.goods.entity.Specification;
import org.apache.ibatis.annotations.Param;

import java.util.Map;
import java.util.Optional;

/**
 * @author xiaohun
 * @since 2019-09-20
 */
public interface ISpecificationDao extends BaseMapper<Specification> {
    /**
     * 根据key获取指定规格
     *
     * @param key 规格名称
     * @return 查询到的规格
     */
    Optional<Specification> selectByKey(@Param("key") String key, @Param("storeId") Long storeId);

    /**
     * 分页获取列表
     *
     * @param page 分页实例
     * @param args key
     *             store_id
     * @return 结果列表
     */
    IPage<Specification> getListByPage(Page page, @Param("args") Map args);
}
