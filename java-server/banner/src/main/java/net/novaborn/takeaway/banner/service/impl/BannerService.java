package net.novaborn.takeaway.banner.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.novaborn.takeaway.banner.dao.IBannerDao;
import net.novaborn.takeaway.banner.entity.Banner;
import net.novaborn.takeaway.banner.service.IBannerService;
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
public class BannerService extends ServiceImpl<IBannerDao, Banner> implements IBannerService {
    @Override
    public IPage<Banner> getBannerListByPage(Page page, Map args) {
        return this.baseMapper.getBannerListByPage(page, args);
    }
}
