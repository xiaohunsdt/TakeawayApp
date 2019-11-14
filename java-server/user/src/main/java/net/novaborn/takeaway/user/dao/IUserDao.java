package net.novaborn.takeaway.user.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.novaborn.takeaway.user.entity.Address;
import net.novaborn.takeaway.user.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author xiaohun
 * @since 2019-09-20
 */
public interface IUserDao extends BaseMapper<User> {

    /**
     * 根据 name 获取一个用户
     * @param name   用户名称
     * @return 查询到的用户
     */
    Optional<User> selectByName(@Param("name") String name);

    /**
     * 根据 微信 OpenId 获取一个用户
     * @param openId   微信 OpenId
     * @return 查询到的用户
     */
    Optional<User> selectByOpenId(@Param("openId") String openId);

    /**
     * 根据 昵称 获取用户
     * @param nickName
     * @return
     */
    List<User> getByNickName(@Param("nickName") String nickName);

    /**
     * 分页获取用户列表
     * @param page   分页实例
     * @param args   name 用户名称
     * @return 用户列表
     */
    IPage<User> getUserListByPage(Page page, @Param("args") Map args);
}
