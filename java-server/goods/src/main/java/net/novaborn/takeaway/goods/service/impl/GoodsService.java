package net.novaborn.takeaway.goods.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.goods.dao.IGoodsDao;
import net.novaborn.takeaway.goods.dto.GoodsDto;
import net.novaborn.takeaway.goods.entity.Goods;
import net.novaborn.takeaway.goods.entity.GoodsStock;
import net.novaborn.takeaway.goods.exception.GoodsExceptionEnum;
import net.novaborn.takeaway.goods.exception.GoodsServiceException;
import net.novaborn.takeaway.goods.service.IGoodsService;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
public class GoodsService extends ServiceImpl<IGoodsDao, Goods> implements IGoodsService {
    private GoodsStockService goodsStockService;

    @Override
    public boolean updateById(Goods entity) {
        if (!super.updateById(entity)) {
            throw new GoodsServiceException(GoodsExceptionEnum.UPDATE_FAILED);
        }

        return true;
    }

    @Override
    public List<Goods> getByProduceId(Long produceId) {
        return this.baseMapper.getByProduceId(produceId);
    }

    @Override
    public int getCountByProduceId(Long produceId) {
        return this.baseMapper.getCountByProduceId(produceId);
    }

    @Override
    public int getAvailableCountByProduceId(Long produceId) {
        return this.baseMapper.getAvailableCountByProduceId(produceId);
    }

    @Override
    public Goods getFirstByProduceId(Long produceId) {
        return this.baseMapper.getFirstByProduceId(produceId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveByProduceId(Long produceId, List<GoodsDto> goodsDtoList) {
        goodsDtoList.forEach(item -> {
            item.setProduceId(produceId);
            ((GoodsService) AopContext.currentProxy()).save(item);
            goodsStockService.createGoodStock(item, item.getStock());
        });
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteByProduceId(Long produceId) {
        List<Long> goodsIdList = this.getByProduceId(produceId).stream().map(Goods::getId).collect(Collectors.toList());

        LambdaQueryWrapper<GoodsStock> wrapper = Wrappers.lambdaQuery();
        wrapper.in(GoodsStock::getGoodsId, goodsIdList);
        List<Long> stockIdList = goodsStockService.list(wrapper).stream().map(GoodsStock::getId).collect(Collectors.toList());

        return this.removeByIds(goodsIdList) && goodsStockService.removeByIds(stockIdList);
    }
}
