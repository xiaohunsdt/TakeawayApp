package net.novaborn.takeaway.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.novaborn.takeaway.user.dao.IAddressDao;
import net.novaborn.takeaway.user.entity.Address;
import net.novaborn.takeaway.user.service.IAddressService;
import org.springframework.stereotype.Service;

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

}
