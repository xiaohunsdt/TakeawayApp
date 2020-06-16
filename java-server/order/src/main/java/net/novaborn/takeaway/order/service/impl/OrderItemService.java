package net.novaborn.takeaway.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.common.exception.SysException;
import net.novaborn.takeaway.goods.entity.Goods;
import net.novaborn.takeaway.goods.enums.GoodsState;
import net.novaborn.takeaway.goods.exception.GoodsExceptionEnum;
import net.novaborn.takeaway.goods.service.impl.GoodsService;
import net.novaborn.takeaway.goods.service.impl.GoodsStockService;
import net.novaborn.takeaway.order.dao.IOrderItemDao;
import net.novaborn.takeaway.order.entity.OrderItem;
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
    private GoodsService goodsService;

    private GoodsStockService goodsStockService;

    @Override
    public List<OrderItem> selectByOrderId(String orderId) {
        return this.baseMapper.selectByOrderId(orderId);
    }

    @Override
    public boolean removeByOrderId(String orderId) {
        return this.baseMapper.removeByOrderId(orderId);
    }

    @Override
    public void checkOrderItems(List<OrderItem> orderItemList) {
        orderItemList.parallelStream().forEach(item -> {
            Optional<Goods> goods = Optional.ofNullable(goodsService.getById(item.getGoodsId()));
            goods.orElseThrow(() -> new SysException(GoodsExceptionEnum.GOODS_NOT_FOUND));

            if (goods.get().getState() == GoodsState.SHORTAGE) {
                SysException sysException = new SysException(GoodsExceptionEnum.GOODS_IS_SHORTAGE);
                sysException.setMessage(goods.get().getName() + ": 当前处于缺货中,无法下单!请下来刷新菜单!");
                throw sysException;
            } else if (!goodsStockService.checkStock(goods.get(), item.getGoodsCount())) {
                SysException sysException = new SysException(GoodsExceptionEnum.GOODS_IS_SHORTAGE);
                sysException.setMessage(goods.get().getName() + ": 当前库存不足,请重试!");
                throw sysException;
            }

            //从新录入价格
            item.setGoodsPrice(goods.get().getPrice());
        });
    }
}
