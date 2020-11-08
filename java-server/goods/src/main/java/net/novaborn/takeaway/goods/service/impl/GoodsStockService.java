package net.novaborn.takeaway.goods.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.common.exception.SysException;
import net.novaborn.takeaway.goods.dao.IGoodsStockDao;
import net.novaborn.takeaway.goods.entity.Goods;
import net.novaborn.takeaway.goods.entity.GoodsStock;
import net.novaborn.takeaway.goods.enums.GoodsState;
import net.novaborn.takeaway.goods.exception.GoodsStockException;
import net.novaborn.takeaway.goods.exception.GoodsStockExceptionEnum;
import net.novaborn.takeaway.goods.service.IGoodsStockService;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaohun
 * @since 2019-09-20
 */
@Slf4j
@Service
@Setter(onMethod_ = {@Autowired})
public class GoodsStockService extends ServiceImpl<IGoodsStockDao, GoodsStock> implements IGoodsStockService {
    private GoodsService goodsService;

    private ProduceService produceService;

    @Override
    public Optional<GoodsStock> getByGoodsId(Long goodsId) {
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

    @Override
    public boolean checkStock(Goods goods, int count) {
        Optional<GoodsStock> targetGoodsStock = this.getByGoodsId(goods.getId());
        if (targetGoodsStock.get().getStock() == -1) {
            return true;
        }
        return targetGoodsStock.get().getStock() >= count;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void reduceStock(Goods goods, int count) {
        Optional<GoodsStock> targetGoodsStock = this.getByGoodsId(goods.getId());

        if (targetGoodsStock.get().getStock() == -1) {
            return;
        }

        if (targetGoodsStock.get().getStock() == 0 || targetGoodsStock.get().getStock() - count < 0) {
            throw new GoodsStockException(GoodsStockExceptionEnum.STOCK_HAD_NONE);
        }

        targetGoodsStock.get().setStock(targetGoodsStock.get().getStock() - count);
        ((GoodsStockService) AopContext.currentProxy()).updateById(goods, targetGoodsStock.get());
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void recoverStock(Goods goods, int count) {
        Optional<GoodsStock> targetGoodsStock = this.getByGoodsId(goods.getId());

        if (targetGoodsStock.get().getStock() == -1) {
            return;
        }

        targetGoodsStock.get().setStock(targetGoodsStock.get().getStock() + count);
        ((GoodsStockService) AopContext.currentProxy()).updateById(goods, targetGoodsStock.get());
    }

    @Override
    public boolean updateById(Goods goods, GoodsStock stock) {
        boolean result = ((GoodsStockService) AopContext.currentProxy()).updateById(stock);

        //检查商品库存是否充足
        if (stock.getStock() != 0 && goods.getState().equals(GoodsState.SHORTAGE)) {
            goods = goodsService.getById(goods.getId());
            goods.setState(GoodsState.ON);
            goodsService.updateById(goods);
        }

        if (stock.getStock() == 0 && goods.getState().equals(GoodsState.ON)) {
            goods = goodsService.getById(goods.getId());
            goods.setState(GoodsState.SHORTAGE);
            goodsService.updateById(goods);
        }

        //检查产品库存是否充足
        produceService.updateProduceState(goods.getProduceId());
        return result;
    }

    @Override
    public boolean updateById(GoodsStock entity) {
        if (!super.updateById(entity)) {
            throw new GoodsStockException(GoodsStockExceptionEnum.UPDATE_FAILED);
        }

        return true;
    }
}
