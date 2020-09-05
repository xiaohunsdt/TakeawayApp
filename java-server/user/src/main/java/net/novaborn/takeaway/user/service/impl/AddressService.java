package net.novaborn.takeaway.user.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.novaborn.takeaway.common.exception.SysException;
import net.novaborn.takeaway.common.utils.MapDistanceUtil;
import net.novaborn.takeaway.system.entity.Setting;
import net.novaborn.takeaway.system.enums.SettingScope;
import net.novaborn.takeaway.system.service.impl.SettingService;
import net.novaborn.takeaway.user.dao.IAddressDao;
import net.novaborn.takeaway.user.entity.Address;
import net.novaborn.takeaway.user.exception.AddressExceptionEnum;
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

    @Autowired
    SettingService settingService;

    @Override
    public List<Address> selectByUserId(Long userId) {
        return this.baseMapper.selectByUserId(userId);
    }

    @Override
    public Optional<Address> selectDefaultAddressByUserId(Long userId) {
        return this.baseMapper.selectDefaultAddressByUserId(userId);
    }

    @Override
    public IPage<Address> getAddressListByPage(Page page, Map args) {
        return this.baseMapper.getAddressListByPage(page, args);
    }

    @Override
    public Boolean setDefaultAddress(Long addressId, Long userId) {
        // 首先取消之前的默认地址
        Optional<Address> defaultAddress = addressService.selectDefaultAddressByUserId(userId);

        //如果数据库中的默认地址不等于当前地址，将数据库中的默认地址设置成一般地址
        if (!defaultAddress.get().getId().equals(addressId)) {
            defaultAddress.get().setIsDefault(false);
            defaultAddress.get().updateById();
        }

        // 把参数实例设置成默认地址
        Address target = addressService.getById(addressId);
        target.setIsDefault(true);
        return target.updateById();
    }

    @Override
    public Double getDistanceWithStore(Long addressId) {
        Optional<Address> address = Optional.ofNullable(addressService.getById(addressId));

        address.orElseThrow(() -> new SysException(AddressExceptionEnum.NO_ADDRESS_ERROR));

        if (address.get().getX() == null) {
            throw new SysException(AddressExceptionEnum.ADDRESS_NO_COORDINATE_ERROR);
        }

        Setting store_coordinate_x = settingService.getSettingByName("store_address_x", SettingScope.STORE);
        Setting store_coordinate_y = settingService.getSettingByName("store_address_y", SettingScope.STORE);

        if (store_coordinate_x == null || store_coordinate_y == null) {
            throw new SysException(AddressExceptionEnum.STORE_ADDRESS_NO_COORDINATE_ERROR);
        }

        return MapDistanceUtil.getDistance(
                address.get().getX(),
                address.get().getY(),
                Double.parseDouble(store_coordinate_x.getValue()),
                Double.parseDouble(store_coordinate_y.getValue())
        );
    }
}
