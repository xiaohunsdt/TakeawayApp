package net.novaborn.takeaway.admin.web.wrapper;

import net.novaborn.takeaway.common.BaseControllerWrapper;

import java.util.Map;

/**
 * 分类包装类
 * @author xiaohun
 */
public class CategoryWrapper extends BaseControllerWrapper {

    public CategoryWrapper(Object element) {
        super(element);
    }

    @Override
    protected void warpTheMap(Map<String, Object> map) {
        map.remove("createDate");
        map.remove("updateDate");
        map.remove("deleted");
    }
}
