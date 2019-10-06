package net.novaborn.takeaway.admin.web.warpper;

import net.novaborn.takeaway.category.entity.Category;
import net.novaborn.takeaway.category.service.impl.CategoryService;
import net.novaborn.takeaway.common.BaseControllerWarpper;
import net.novaborn.takeaway.common.SpringContextHolder;

import java.util.Map;

public class GoodsWarpper extends BaseControllerWarpper {
    public GoodsWarpper(Object obj) {
        super(obj);
    }

    @Override
    protected void warpTheMap(Map<String, Object> map) {
        CategoryService categoryService = SpringContextHolder.getBean(CategoryService.class);
        Category category = categoryService.getById((String) map.get("categoryId"));
        map.put("category", category.getName());

        map.remove("categoryId");
        map.remove("createDate");
        map.remove("updateDate");
        map.remove("deleted");
        map.remove("version");
    }
}
