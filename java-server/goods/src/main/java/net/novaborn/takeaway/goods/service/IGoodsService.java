package net.novaborn.takeaway.goods.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import net.novaborn.takeaway.goods.entity.Goods;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xiaohun
 * @since 2019-09-20
 */
public interface IGoodsService extends IService<Goods> {
    /**
     * 根据产品名称获取指定产品
     * @param name 产品名称
     * @return 查询到的产品
     */
    Optional<Goods> selectByName(String name);

    /**
     * 根据分类获取指定产品
     * @param categoryId 分类Id
     * @return 查询到的产品列表
     */
    List<Goods> getGoodsListByCategoryId(Long categoryId);

    /**
     * 根据产品标志名称获取指定产品
     * @param flag 产品标志 新品\热卖
     * @return 查询到的产品列表
     */
    List<Goods> getGoodsListByFlag(String flag);

    /**
     * 分页获取产品列表
     * @param page   分页实例
     * @param args   name/categoryId/state
     * @return 产品列表
     */
    IPage<Goods> getGoodsListByPage(Page page, Map args);
}
