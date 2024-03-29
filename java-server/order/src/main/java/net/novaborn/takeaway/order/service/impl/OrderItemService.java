package net.novaborn.takeaway.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.common.exception.SysException;
import net.novaborn.takeaway.goods.entity.Goods;
import net.novaborn.takeaway.goods.entity.Produce;
import net.novaborn.takeaway.goods.enums.GoodsState;
import net.novaborn.takeaway.goods.exception.GoodsExceptionEnum;
import net.novaborn.takeaway.goods.exception.ProduceExceptionEnum;
import net.novaborn.takeaway.goods.exception.ProduceServiceException;
import net.novaborn.takeaway.goods.service.impl.GoodsService;
import net.novaborn.takeaway.goods.service.impl.GoodsStockService;
import net.novaborn.takeaway.goods.service.impl.ProduceService;
import net.novaborn.takeaway.order.dao.IOrderItemDao;
import net.novaborn.takeaway.order.entity.OrderItem;
import net.novaborn.takeaway.order.enums.OrderType;
import net.novaborn.takeaway.order.service.IOrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
@Setter(onMethod_ = {@Autowired})
@Service
public class OrderItemService extends ServiceImpl<IOrderItemDao, OrderItem> implements IOrderItemService {
    private ProduceService produceService;

    private GoodsService goodsService;

    private GoodsStockService goodsStockService;

    @Override
    public List<OrderItem> selectByOrderId(Long orderId) {
        return this.baseMapper.selectByOrderId(orderId);
    }

    @Override
    public boolean removeByOrderId(Long orderId) {
        return this.baseMapper.removeByOrderId(orderId);
    }

    @Override
    public void checkOrderItems(OrderType orderType, List<OrderItem> orderItemList) {
        orderItemList.parallelStream().forEach(item -> {
            Optional<Goods> goods = Optional.ofNullable(goodsService.getById(item.getGoodsId()));
            goods.orElseThrow(() -> new SysException(GoodsExceptionEnum.GOODS_NOT_FOUND));

            if (orderType.equals(OrderType.EXPRESS)) {
                Produce produce = produceService.getById(goods.get().getProduceId());
                if (!produce.getExpressAble()) {
                    throw new ProduceServiceException(ProduceExceptionEnum.PRODUCE_EXPRESS_UNABLE, produce.getName());
                }
            }

            if (goods.get().getState() == GoodsState.OFF) {
                SysException sysException = new SysException(GoodsExceptionEnum.GOODS_IS_OFF);
                sysException.setMessage(item.getProduceName() + item.getGoodsTitle() + ": 当前不可用,无法下单!请下来刷新菜单!");
                throw sysException;
            } else if (goods.get().getState() == GoodsState.SHORTAGE) {
                SysException sysException = new SysException(GoodsExceptionEnum.GOODS_IS_SHORTAGE);
                sysException.setMessage(item.getProduceName() + item.getGoodsTitle() + ": 当前处于缺货中,无法下单!请下来刷新菜单!");
                throw sysException;
            } else if (!goodsStockService.checkStock(goods.get(), item.getGoodsCount())) {
                SysException sysException = new SysException(GoodsExceptionEnum.GOODS_IS_SHORTAGE);
                sysException.setMessage(item.getProduceName() + item.getGoodsTitle() + ": 当前库存不足,请重试!");
                throw sysException;
            }

            //从新录入价格
            item.setGoodsPrice(goods.get().getPrice());
        });
    }
}
