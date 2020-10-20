package net.novaborn.takeaway.goods.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.novaborn.takeaway.goods.entity.Goods;
import net.novaborn.takeaway.goods.entity.Produce;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author xiaohun
 * @since 2019-09-20
 */
public interface IProduceDao extends BaseMapper<Produce> {
    /**
     * 根据产品名称获取指定产品
     *
     * @param name 产品名称
     * @return 查询到的产品
     */
    Optional<Produce> selectByName(String name);

    /**
     * 根据分类获取指定产品
     * @param categoryId 分类Id
     * @return 查询到的产品列表
     */
    List<Produce> getListByCategoryId(@Param("categoryId") Long categoryId);

    /**
     * 根据产品标志名称获取指定产品
     *
     * @param flag 产品标志 新品\热卖
     * @return 查询到的产品列表
     */
    List<Produce> getListByFlag(@Param("flag") String flag);

    /**
     * 分页获取产品列表
     *
     * @param page 分页实例
     * @param args name/categoryId/state
     * @return 产品列表
     */
    IPage<Produce> getListByPage(Page page, @Param("args") Map args);
}
