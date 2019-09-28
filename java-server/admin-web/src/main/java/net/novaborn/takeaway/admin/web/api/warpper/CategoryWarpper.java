package net.novaborn.takeaway.admin.web.api.warpper;

import net.novaborn.takeaway.common.BaseControllerWarpper;

import java.util.Map;

public class CategoryWarpper extends BaseControllerWarpper {
    public CategoryWarpper(Object obj) {
        super(obj);
    }

    @Override
    protected void warpTheMap(Map<String, Object> map) {
        map.remove("createDate");
        map.remove("updateDate");
        map.remove("deleted");
    }
}
