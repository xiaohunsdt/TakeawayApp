package net.novaborn.takeaway.user.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.novaborn.takeaway.user.dao.IAddressDao;
import net.novaborn.takeaway.user.entity.Address;
import net.novaborn.takeaway.user.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaohun
 * @since 2019-09-20
 */
@Service
public class AddressService extends ServiceImpl<IAddressDao, Address> implements IAddressService {
    @Autowired
    AddressService addressService;

    @Override
    public List<Address> selectByUserId(String userId) {
        return this.baseMapper.selectByUserId(userId);
    }

    @Override
    public Optional<Address> selectDefaultAddressByUserId(String userId) {
        return this.baseMapper.selectDefaultAddressByUserId(userId);
    }

    @Override
    public IPage<Address> getAddressListByPage(Page page, Map args) {
        return this.baseMapper.getAddressListByPage(page, args);
    }

    @Override
    public Boolean setDefaultAddress(Address address) {
        // 首先取消之前的默认地址
        this.selectByUserId(address.getUserId()).stream()
                .filter(Address::getIsDefault)
                .forEach(e -> {
                    e.setIsDefault(false);
                    e.updateById();
                });

        // 吧参数实例设置成默认地址
        Address address1 = addressService.getById(address.getId());
        address1.setIsDefault(true);
        return address1.updateById();
    }
}
