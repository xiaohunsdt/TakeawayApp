package net.novaborn.takeaway.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.novaborn.takeaway.admin.dao.IActivityDao;
import net.novaborn.takeaway.admin.entity.Activity;
import net.novaborn.takeaway.admin.service.IActivityService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaohun
 * @since 2019-09-20
 */
@Service
public class ActivityService extends ServiceImpl<IActivityDao, Activity> implements IActivityService {

}
