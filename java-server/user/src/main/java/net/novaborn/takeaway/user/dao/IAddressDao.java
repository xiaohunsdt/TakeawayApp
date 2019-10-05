package net.novaborn.takeaway.user.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.novaborn.takeaway.user.entity.Address;

import java.util.List;
import java.util.Optional;

/**
 * @author xiaohun
 * @since 2019-09-20
 */
public interface IAddressDao extends BaseMapper<Address> {

    /**
     * 根据 用户ID 获取地址
     *
     * @param userId 用户ID
     * @return 查询到的地址列表
     */
    List<Address> selectByUserId(String userId);

    /**
     * 获取指定用户的默认地址
     *
     * @param userId 用户ID
     * @return 查询到的默认地址
     */
    Optional<Address> selectDefaultAddressByUserId(String userId);
}
