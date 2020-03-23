package net.novaborn.takeaway.user.web.wrapper;

import net.novaborn.takeaway.category.entity.Category;
import net.novaborn.takeaway.category.service.impl.CategoryService;
import net.novaborn.takeaway.common.BaseControllerWrapper;
import net.novaborn.takeaway.common.SpringContextHolder;
import net.novaborn.takeaway.user.config.properties.SystemProperties;

import java.util.Map;

/**
 * 商品包装类
 *
 * @author xiaohun
 */
public class GoodsWrapperEx extends BaseControllerWrapper {
    private Category category;

    public GoodsWrapperEx(Object element) {
        super(element);
    }

    public GoodsWrapperEx(Object element, Category category) {
        super(element);
        this.category = category;
    }

    @Override
    protected void warpTheMap(Map<String, Object> map) {
        SystemProperties systemProperties = SpringContextHolder.getBean(SystemProperties.class);

        map.put("category", category.getName());
        if (map.get("thumb") != null) {
            map.put("thumb", systemProperties.getUploadServerUrl() + map.get("thumb"));
        }

        map.remove("categoryId");
        map.remove("createDate");
        map.remove("updateDate");
        map.remove("deleted");
        map.remove("version");
    }
}
