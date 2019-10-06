package net.novaborn.takeaway.common;

import cn.hutool.core.bean.BeanUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 控制器查询结果的包装类基类
 *
 * @author fengshuonan
 * @date 2017年2月13日 下午10:49:36
 */
public abstract class BaseControllerWrapper {

    private Object element;

    public BaseControllerWrapper(Object element) {
        this.element = element;
    }

    @SuppressWarnings("unchecked")
    public Object warp() {
        if (this.element instanceof List) {
            List elementList = (List) this.element;
            List mapList = new ArrayList();
            for (Object obj : elementList) {
                Map map = BeanUtil.beanToMap(obj);
                warpTheMap(map);
                mapList.add(map);
            }
            return mapList;
        } else if (this.element instanceof Map) {
            Map<String, Object> map = (Map<String, Object>) this.element;
            warpTheMap(map);
            return map;
        } else {
            return this.element;
        }
    }

    protected abstract void warpTheMap(Map<String, Object> map);
}
