package net.novaborn.takeaway.admin.web.wrapper;

import net.novaborn.takeaway.common.BaseControllerWrapper;
import net.novaborn.takeaway.common.SpringContextHolder;
import net.novaborn.takeaway.goods.entity.GoodsStock;
import net.novaborn.takeaway.goods.service.impl.GoodsStockService;

import java.util.Map;
import java.util.Optional;

/**
 * 商品包装类
 *
 * @author xiaohun
 */
public class GoodsWrapperEx extends BaseControllerWrapper {

    public GoodsWrapperEx(Object element) {
        super(element);
    }

    @Override
    protected void warpTheMap(Map<String, Object> map) {
        GoodsStockService goodsStockService = SpringContextHolder.getBean(GoodsStockService.class);
        Optional<GoodsStock> goodsStock = goodsStockService.getByGoodsId((Long)map.get("id"));
        if (goodsStock.isPresent()) {
            map.put("stock", goodsStock.get().getStock());
        } else {
            map.put("stock", -1);
        }
        map.remove("createDate");
        map.remove("updateDate");
        map.remove("deleted");
        map.remove("version");
    }
}
