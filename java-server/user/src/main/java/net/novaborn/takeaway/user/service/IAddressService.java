package net.novaborn.takeaway.user.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import net.novaborn.takeaway.user.entity.Address;

import java.util.List;
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
public interface IAddressService extends IService<Address> {

    /**
     * 根据 用户ID 获取地址
     *
     * @param userId 用户ID
     * @return 查询到的地址列表
     */
    List<Address> selectByUserId(Long userId);

    /**
     * 获取指定用户的默认地址
     *
     * @param userId 用户ID
     * @return 查询到的默认地址
     */
    Optional<Address> selectDefaultAddressByUserId(Long userId);

    /**
     * 分页获取地址列表
     *
     * @param page 分页实例
     * @param args userId 用户ID
     * @return 用户列表
     */
    IPage<Address> getAddressListByPage(Page page, Map args);

    /**
     * 将指定address设置成默认地址
     *
     * @param addressId 要设置的地址Id
     * @param userId    要设置的地址的用户Id
     * @return 是否成功
     */
    Boolean setDefaultAddress(Long addressId, Long userId);

    /**
     * 获取指定地址与店的直线距离
     * @param addressId
     * @return
     */
    Double getDistanceWithStore(Long addressId);
}
