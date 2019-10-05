package net.novaborn.takeaway.user.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import net.novaborn.takeaway.user.entity.User;

import java.util.Map;
import java.util.Optional;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xiaohun
 * @since 2019-09-20
 */
public interface IUserService extends IService<User> {

    /**
     * 根据 name 获取一个用户
     * @param name   用户名称
     * @return 查询到的用户
     */
    Optional<User> selectByName(String name);

    /**
     * 分页获取用户列表
     * @param page   分页实例
     * @param args   name 用户名称
     * @return 用户列表
     */
    IPage<User> getUserListByPage(Page page, Map args);
}
