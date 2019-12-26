package net.novaborn.takeaway.user.web.wrapper;

import net.novaborn.takeaway.common.BaseControllerWrapper;
import net.novaborn.takeaway.common.SpringContextHolder;
import net.novaborn.takeaway.user.config.properties.SystemProperties;

import java.util.Map;

/**
 * 分类包装类
 *
 * @author xiaohun
 */
public class ActivityWrapper extends BaseControllerWrapper {

    public ActivityWrapper(Object element) {
        super(element);
    }

    @Override
    protected void warpTheMap(Map<String, Object> map) {
        SystemProperties systemProperties = SpringContextHolder.getBean(SystemProperties.class);

        if (map.get("mainImg") != null){
            map.put("mainImg", systemProperties.getUploadServerUrl() + map.get("mainImg"));
        }

        map.remove("deleted");
    }
}
