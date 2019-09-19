package net.novaborn.takeaway.activity.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.novaborn.takeaway.activity.dao.IActivityDao;
import net.novaborn.takeaway.activity.entity.Activity;
import net.novaborn.takeaway.activity.service.IActivityService;
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
public class ActivityService extends ServiceImpl<IActivityDao, Activity> implements IActivityService {

}
