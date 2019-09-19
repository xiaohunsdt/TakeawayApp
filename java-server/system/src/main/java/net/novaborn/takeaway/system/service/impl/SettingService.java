package net.novaborn.takeaway.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.novaborn.takeaway.system.dao.ISettingDao;
import net.novaborn.takeaway.system.entity.Setting;
import net.novaborn.takeaway.system.service.ISettingService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaohun
 * @since 2018-07-12
 */
@Service
public class SettingService extends ServiceImpl<ISettingDao, Setting> implements ISettingService {

}
