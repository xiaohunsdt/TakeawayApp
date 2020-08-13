package net.novaborn.takeaway.admin.web.wrapper;

import net.novaborn.takeaway.admin.enums.Level;
import net.novaborn.takeaway.admin.enums.State;
import net.novaborn.takeaway.common.BaseControllerWrapper;
import net.novaborn.takeaway.common.SpringContextHolder;
import net.novaborn.takeaway.user.service.impl.UserService;

import java.util.Map;

/**
 * 分类包装类
 *
 * @author xiaohun
 */
public class AdminWrapper extends BaseControllerWrapper {

    public AdminWrapper(Object element) {
        super(element);
    }

    @Override
    protected void warpTheMap(Map<String, Object> map) {
        switch ((Level)map.get("level")) {
            case SUPER_MANAGER:
                map.put("level","超级管理员");
                break;
            case SHOP_MANAGER:
                map.put("level","店铺管理员");
                break;
            case RECEIVER:
                map.put("level","接单员");
                break;
            case DELIVERER:
                map.put("level","配送员");
                break;
        }

        switch ((State)map.get("state")) {
            case NORMAL:
                map.put("state","正常");
                break;
            case STOP:
                map.put("state","冻结");
                break;
        }
    }
}
