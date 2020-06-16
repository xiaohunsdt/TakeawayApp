package net.novaborn.takeaway.goods.service;


import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.service.IService;
import net.novaborn.takeaway.goods.entity.Goods;
import net.novaborn.takeaway.goods.entity.GoodsStock;

import java.util.List;
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
     *
     * @param goods 所属商品
     * @param stock 库存数量,可NULL
     */
    boolean createGoodStock(Goods goods, Integer stock);

    /**
     * 检查库存是否足够
     * @param goods
     * @param count
     * @return ture 足够    false不足够
     */
    boolean checkStock(Goods goods, int count);

    /**
     * 减少库存
     *
     * @param goods
     * @param count
     */
    void reduceStock(Goods goods, int count);

    /**
     * 恢复库存
     *
     * @param goods
     * @param count
     */
    void recoverStock(Goods goods, int count);
}
