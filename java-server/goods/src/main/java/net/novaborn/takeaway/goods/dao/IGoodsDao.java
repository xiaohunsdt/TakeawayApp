package net.novaborn.takeaway.goods.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.novaborn.takeaway.goods.entity.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.Map;
import java.util.Optional;

/**
 * @author xiaohun
 * @since 2019-09-20
 */
public interface IGoodsDao extends BaseMapper<Goods> {

    /**
     * 根据产品名称获取指定产品
     * @param name 产品名称
     * @return 查询到的产品
     */
    Optional<Goods> selectByName(String name);

    /**
     * 分页获取产品列表
     * @param page   分页实例
     * @param args   name/categoryId
     * @return 产品列表
     */
    IPage<Goods> getGoodsListByPage(Page page, @Param("args") Map args);
}
