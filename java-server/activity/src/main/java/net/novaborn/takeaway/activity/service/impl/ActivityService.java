package net.novaborn.takeaway.activity.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.novaborn.takeaway.activity.dao.IActivityDao;
import net.novaborn.takeaway.activity.entity.Activity;
import net.novaborn.takeaway.activity.service.IActivityService;
import org.springframework.stereotype.Service;

import java.util.Map;

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
    @Override
    public IPage<Activity> getActivityListByPage(Page page, Map args) {
        return this.baseMapper.getActivityListByPage(page, args);
    }
}
