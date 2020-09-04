package net.novaborn.takeaway.admin.web.wrapper;

import net.novaborn.takeaway.category.entity.Category;
import net.novaborn.takeaway.category.service.impl.CategoryService;
import net.novaborn.takeaway.common.BaseControllerWrapper;
import net.novaborn.takeaway.common.SpringContextHolder;

import java.util.Map;

/**
 * 商品包装类
 * @author xiaohun
 */
public class GoodsWrapper extends BaseControllerWrapper {

    public GoodsWrapper(Object element) {
        super(element);
    }

    @Override
    protected void warpTheMap(Map<String, Object> map) {
        CategoryService categoryService = SpringContextHolder.getBean(CategoryService.class);
        Category category = categoryService.getById((Long) map.get("categoryId"));
        map.put("category", category.getName());

        map.remove("categoryId");
        map.remove("createDate");
        map.remove("updateDate");
        map.remove("deleted");
        map.remove("version");
    }
}
