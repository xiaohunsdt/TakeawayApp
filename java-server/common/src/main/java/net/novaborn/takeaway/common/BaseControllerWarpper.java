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
public abstract class BaseControllerWarpper {

    public Object obj = null;

    public BaseControllerWarpper(Object obj) {
        this.obj = obj;
    }

    @SuppressWarnings("unchecked")
    public Object warp() {
        if (this.obj instanceof List) {
            List<Map<String, Object>> list = (List<Map<String, Object>>) this.obj;
            for (Map<String, Object> map : list) {
                warpTheMap(map);
            }
            return list;
        } else if (this.obj instanceof Map) {
            Map<String, Object> map = (Map<String, Object>) this.obj;
            warpTheMap(map);
            return map;
        } else {
            return this.obj;
        }
    }

    public <T> List warpObj(){
        List<T> list = (List<T>) this.obj;
        List<Map<String, Object>> result = new ArrayList<>();
        for (T obj : list) {
            Map<String, Object> data = BeanUtil.beanToMap(obj);
            warpTheMap(data);
            result.add(data);
        }
        return result;
    }

    protected abstract void warpTheMap(Map<String, Object> map);
}
