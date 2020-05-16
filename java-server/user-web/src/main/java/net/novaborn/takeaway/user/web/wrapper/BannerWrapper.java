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
public class BannerWrapper extends BaseControllerWrapper {

    public BannerWrapper(Object element) {
        super(element);
    }

    @Override
    protected void warpTheMap(Map<String, Object> map) {
        SystemProperties systemProperties = SpringContextHolder.getBean(SystemProperties.class);

        map.put("img", systemProperties.getUploadServerUrl() + map.get("img"));
        map.remove("deleted");
    }
}
