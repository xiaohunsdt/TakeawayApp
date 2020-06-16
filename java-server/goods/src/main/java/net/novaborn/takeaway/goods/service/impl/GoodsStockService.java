package net.novaborn.takeaway.goods.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.novaborn.takeaway.goods.dao.IGoodsStockDao;
import net.novaborn.takeaway.goods.entity.Goods;
import net.novaborn.takeaway.goods.entity.GoodsStock;
import net.novaborn.takeaway.goods.service.IGoodsStockService;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaohun
 * @since 2019-09-20
 */
@Service
public class GoodsStockService extends ServiceImpl<IGoodsStockDao, GoodsStock> implements IGoodsStockService {

    @Override
    public Optional<GoodsStock> getByGoodsId(String goodsId) {
        return this.baseMapper.getByGoodsId(goodsId);
    }

    @Override
    public boolean createGoodStock(Goods goods, Integer stock) {
        stock = stock == null ? -1 : stock;

        GoodsStock stockTemp = new GoodsStock();
        stockTemp.setGoodsId(goods.getId());
        stockTemp.setStock(stock);
        return stockTemp.insert();
    }
}
