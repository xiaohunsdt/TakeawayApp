package net.novaborn.takeaway.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.novaborn.takeaway.admin.dao.IAdminDao;
import net.novaborn.takeaway.admin.entity.Admin;
import net.novaborn.takeaway.admin.service.IAdminService;
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
public class AdminService extends ServiceImpl<IAdminDao, Admin> implements IAdminService {
    /**
     * 管理员登陆
     *
     * @param userName
     * @param password
     * @return
     */
    @Override
    public boolean login(String userName, String password) {
        Optional<Admin> admin = this.baseMapper.selectByName(userName);

        return false;
    }
}
