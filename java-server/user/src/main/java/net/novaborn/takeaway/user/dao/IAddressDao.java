package net.novaborn.takeaway.user.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.novaborn.takeaway.user.entity.Address;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
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
    List<Address> selectByUserId(@Param("userId") String userId);

    /**
     * 分页获取地址列表
     * @param page   分页实例
     * @param args   userId 用户ID
     * @return 用户列表
     */
    IPage<Address> getAddressListByPage(@Param("page") Page page, @Param("args") Map args);

    /**
     * 获取指定用户的默认地址
     *
     * @param userId 用户ID
     * @return 查询到的默认地址
     */
    Optional<Address> selectDefaultAddressByUserId(@Param("userId") String userId);
}
