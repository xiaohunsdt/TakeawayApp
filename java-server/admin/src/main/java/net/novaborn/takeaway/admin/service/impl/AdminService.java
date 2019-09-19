package net.novaborn.takeaway.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.novaborn.takeaway.admin.dao.IAdminDao;
import net.novaborn.takeaway.admin.entity.Admin;
import net.novaborn.takeaway.admin.service.IAdminService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaohun
 * @since 2019-09-20
 */
@Service
public class AdminService extends ServiceImpl<IAdminDao, Admin> implements IAdminService {

}
