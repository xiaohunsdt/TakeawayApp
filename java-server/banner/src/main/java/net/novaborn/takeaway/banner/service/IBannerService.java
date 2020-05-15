package net.novaborn.takeaway.banner.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import net.novaborn.takeaway.banner.entity.Banner;

import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xiaohun
 * @since 2019-09-20
 */
public interface IBannerService extends IService<Banner> {
    /**
     * 分页获取活动列表
     *
     * @param page 分页实例
     * @param args userId 用户ID
     * @return 用户列表
     */
    IPage<Banner> getBannerListByPage(Page page, Map args);
}
