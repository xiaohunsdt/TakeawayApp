package net.novaborn.takeaway.system.service;


import com.baomidou.mybatisplus.extension.service.IService;
import net.novaborn.takeaway.system.entity.Setting;
import net.novaborn.takeaway.system.enums.SettingScope;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xiaohun
 * @since 2019-09-20
 */
public interface ISettingService extends IService<Setting> {
    /**
     * 获取指定的设置项
     * @param key 设置项的名称
     * @param scope 设置项的域
     * @return
     */
    Setting getSettingByName(String key, SettingScope scope);

    /**
     * 获取指定设置域中的所有设置项
     * @param scope
     * @return
     */
    List<Setting> getSettingsByScope(SettingScope scope);
}
