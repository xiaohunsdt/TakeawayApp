package net.novaborn.takeaway.activity.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import net.novaborn.takeaway.activity.entity.Activity;

import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xiaohun
 * @since 2019-09-20
 */
public interface IActivityService extends IService<Activity> {
    /**
     * 分页获取活动列表
     *
     * @param page 分页实例
     * @param args userId 用户ID
     * @return 用户列表
     */
    IPage<Activity> getActivityListByPage(Page page, Map args);
}
