package net.novaborn.takeaway.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.Setter;
import net.novaborn.takeaway.admin.dao.IAdminDao;
import net.novaborn.takeaway.admin.entity.Admin;
import net.novaborn.takeaway.admin.service.IAdminService;
import net.novaborn.takeaway.common.exception.SysException;
import net.novaborn.takeaway.common.exception.SysExceptionEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
@Setter(onMethod_ = {@Autowired})
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
        admin.orElseThrow(() -> new SysException(SysExceptionEnum.AUTH_HAVE_NO_USER));

        if (!admin.get().getPassword().equals(password)) {
            throw new SysException(SysExceptionEnum.AUTH_REQUEST_ERROR);
        }

        admin.get().setLoginDate(new Date());
        admin.get().updateById();
        return true;
    }
}
