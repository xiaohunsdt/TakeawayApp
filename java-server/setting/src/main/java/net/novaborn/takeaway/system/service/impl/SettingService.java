package net.novaborn.takeaway.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.novaborn.takeaway.common.entity.BaseKVO;
import net.novaborn.takeaway.system.dao.ISettingDao;
import net.novaborn.takeaway.system.entity.Setting;
import net.novaborn.takeaway.system.enums.SettingScope;
import net.novaborn.takeaway.system.service.ISettingService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaohun
 * @since 2019-09-20
 */
@Service
public class SettingService extends ServiceImpl<ISettingDao, Setting> implements ISettingService {
    @Override
    public Setting getSettingByName(String key, SettingScope scope) {
        return this.baseMapper.getSettingByName(null, key, scope);
    }

    @Override
    public Setting getSettingByName(Long storeId, String key, SettingScope scope) {
        return this.baseMapper.getSettingByName(storeId, key, scope);
    }

    @Override
    public List<Setting> getSettingsByScope(SettingScope scope) {
        return this.baseMapper.getSettingsByScope(null, scope);
    }

    @Override
    public List<Setting> getSettingsByScope(Long storeId, SettingScope scope) {
        return this.baseMapper.getSettingsByScope(storeId, scope);
    }

    @Override
    public List<BaseKVO<Integer, Integer>> getDistancePriceArr(Long storeId) {
        List result = new ArrayList();
        Setting setting = this.getSettingByName(storeId, "distance_price_arr", SettingScope.DELIVERY);

        if (setting != null) {
            result.addAll(JSON.parseArray(setting.getValue(), BaseKVO.class));
        }
        return result;
    }
}
