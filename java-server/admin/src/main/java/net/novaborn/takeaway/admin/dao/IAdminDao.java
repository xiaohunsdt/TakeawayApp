package net.novaborn.takeaway.admin.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.novaborn.takeaway.admin.entity.Admin;
import org.apache.ibatis.annotations.Param;

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
}
