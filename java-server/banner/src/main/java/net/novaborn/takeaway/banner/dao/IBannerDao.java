package net.novaborn.takeaway.banner.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.novaborn.takeaway.banner.entity.Banner;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @author xiaohun
 * @since 2019-09-20
 */
public interface IBannerDao extends BaseMapper<Banner> {
    /**
     * 分页获取活动列表
     *
     * @param page 分页实例
     * @param args userId 用户ID
     * @return 用户列表
     */
    IPage<Banner> getBannerListByPage(@Param("page") Page page, @Param("args") Map args);

}
