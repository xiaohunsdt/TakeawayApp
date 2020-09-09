package net.novaborn.takeaway.system.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.novaborn.takeaway.system.entity.Setting;
import net.novaborn.takeaway.system.enums.SettingScope;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xiaohun
 * @since 2019-09-20
 */
public interface ISettingDao extends BaseMapper<Setting> {
    /**
     * 获取指定的设置项
     *
     * @param storeId   店铺Id
     * @param key   设置项的名称
     * @param scope 设置项的域
     * @return
     */
    Setting getSettingByName(@Param("storeId") Long storeId, @Param("key") String key, @Param("scope") SettingScope scope);

    /**
     * 获取指定设置域中的所有设置项
     *
     * @param storeId   店铺Id
     * @param scope
     * @return
     */
    List<Setting> getSettingsByScope(@Param("storeId") Long storeId, @Param("scope") SettingScope scope);
}
