package net.novaborn.takeaway.goods.service;


import com.baomidou.mybatisplus.extension.service.IService;
import net.novaborn.takeaway.goods.entity.Goods;
import net.novaborn.takeaway.goods.entity.GoodsStock;

import java.util.Optional;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xiaohun
 * @since 2019-09-20
 */
public interface IGoodsStockService extends IService<GoodsStock> {
    /**
     * 根据产品ID获取指定库存
     *
     * @param goodsId 产品ID
     * @return 查询到的产品库存
     */
    Optional<GoodsStock> getByGoodsId(String goodsId);

    /**
     * 创建一个商品库存
     * @param goods 所属商品
     * @param stock 库存数量,可NULL
     */
    boolean createGoodStock(Goods goods, Integer stock);
}
