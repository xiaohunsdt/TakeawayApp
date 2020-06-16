package net.novaborn.takeaway.goods.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.novaborn.takeaway.goods.entity.Goods;
import net.novaborn.takeaway.goods.entity.GoodsStock;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author xiaohun
 * @since 2019-09-20
 */
public interface IGoodsStockDao extends BaseMapper<GoodsStock> {
    /**
     * 根据产品ID获取指定库存
     * @param goodsId 产品ID
     * @return 查询到的产品库存
     */
    Optional<GoodsStock> getByGoodsId(String goodsId);
}
