package net.novaborn.takeaway.user.service;


import com.baomidou.mybatisplus.extension.service.IService;
import net.novaborn.takeaway.user.entity.Address;

import java.util.List;
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
    List<Address> selectByUserId(String userId);

    /**
     * 获取指定用户的默认地址
     *
     * @param userId 用户ID
     * @return 查询到的默认地址
     */
    Optional<Address> selectDefaultAddressByUserId(String userId);

    /**
     * 将指定address设置成默认地址
     *
     * @param address 要设置的地址实例
     * @return 是否成功
     */
    Boolean setDefaultAddress(Address address);
}
