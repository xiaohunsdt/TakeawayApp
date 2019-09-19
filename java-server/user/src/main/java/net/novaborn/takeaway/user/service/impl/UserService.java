package net.novaborn.takeaway.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.novaborn.takeaway.user.dao.IUserDao;
import net.novaborn.takeaway.user.entity.User;
import net.novaborn.takeaway.user.service.IUserService;
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
public class UserService extends ServiceImpl<IUserDao, User> implements IUserService {

}
