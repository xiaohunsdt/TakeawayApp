package net.novaborn.takeaway.goods.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.novaborn.takeaway.goods.entity.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author xiaohun
 * @since 2019-09-20
 */
public interface IGoodsDao extends BaseMapper<Goods> {
    /**
     * 根据产品id获取指定产品的商品
     * @param produceId 产品Id
     * @return 查询到的产品商品
     */
    List<Goods> getByProduceId(@Param("produceId") Long produceId);

    /**
     * 根据产品id获取指定产品的商品数量
     * @param produceId 产品Id
     * @return 查询到的商品数量
     */
    int getCountByProduceId(@Param("produceId") Long produceId);
}
