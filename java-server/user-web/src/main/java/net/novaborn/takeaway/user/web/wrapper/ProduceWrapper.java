package net.novaborn.takeaway.user.web.wrapper;

import net.novaborn.takeaway.category.entity.Category;
import net.novaborn.takeaway.category.service.impl.CategoryService;
import net.novaborn.takeaway.common.BaseControllerWrapper;
import net.novaborn.takeaway.common.SpringContextHolder;
import net.novaborn.takeaway.goods.entity.Goods;
import net.novaborn.takeaway.goods.enums.ProduceState;
import net.novaborn.takeaway.goods.service.impl.GoodsService;
import net.novaborn.takeaway.user.config.properties.SystemProperties;

import java.util.Map;

/**
 * 商品包装类
 *
 * @author xiaohun
 */
public class ProduceWrapper extends BaseControllerWrapper {
    private Category category;

    public ProduceWrapper(Object element) {
        super(element);
    }

    public ProduceWrapper(Object element, Category category) {
        super(element);
        this.category = category;
    }

    @Override
    protected void warpTheMap(Map<String, Object> map) {
        GoodsService goodsService = SpringContextHolder.getBean(GoodsService.class);
        CategoryService categoryService = SpringContextHolder.getBean(CategoryService.class);
        SystemProperties systemProperties = SpringContextHolder.getBean(SystemProperties.class);

        if(category == null){
            category = categoryService.getById((Long) map.get("categoryId"));
        }

        Goods firstGoods = goodsService.getFirstByProduceId((Long) map.get("id"));
        if(firstGoods!=null){
            map.put("goods", firstGoods);
        }

        if (map.get("thumb") != null){
            map.put("thumb", systemProperties.getUploadServerUrl() + map.get("thumb"));
        }

        if(map.get("state") == ProduceState.PART_SHORTAGE){
            map.put("state","ON");
        }

        map.put("category", category.getName());
        map.put("goodsCount", goodsService.getCountByProduceId((Long) map.get("id")));
        map.remove("categoryId");
        map.remove("createDate");
        map.remove("updateDate");
        map.remove("deleted");
        map.remove("version");
    }
}
