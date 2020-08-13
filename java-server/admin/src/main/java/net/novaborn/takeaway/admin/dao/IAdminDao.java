package net.novaborn.takeaway.admin.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.novaborn.takeaway.admin.entity.Admin;
import org.apache.ibatis.annotations.Param;

import java.util.Map;
import java.util.Optional;

/**
 * @author xiaohun
 * @since 2019-09-20
 */
public interface IAdminDao extends BaseMapper<Admin> {
    /**
     * 根据 name 获取管理员
     * @param name
     * @return 管理员对象
     */
    Optional<Admin> selectByName(@Param("name") String name);

    /**
     * 分页获取子管理员列表
     *
     * @param page 分页实例
     * @param args  parentId    父Id
     *              level       等级
     *              state       状态
     * @return 用户列表
     */
    IPage<Admin> getSubAdminListByPage(@Param("page") Page page, @Param("args") Map args);
}
