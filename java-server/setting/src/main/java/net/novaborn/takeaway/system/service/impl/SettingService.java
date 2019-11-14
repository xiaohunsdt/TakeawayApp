package net.novaborn.takeaway.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.novaborn.takeaway.system.dao.ISettingDao;
import net.novaborn.takeaway.system.entity.Setting;
import net.novaborn.takeaway.system.enums.SettingScope;
import net.novaborn.takeaway.system.service.ISettingService;
import org.springframework.stereotype.Service;

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
        return this.baseMapper.getSettingByName(key,scope);
    }

    @Override
    public List<Setting> getSettingsByScope(SettingScope scope) {
        return  this.baseMapper.getSettingsByScope(scope);
    }
}
