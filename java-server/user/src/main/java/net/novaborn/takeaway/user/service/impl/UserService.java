package net.novaborn.takeaway.user.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.novaborn.takeaway.user.dao.IUserDao;
import net.novaborn.takeaway.user.entity.User;
import net.novaborn.takeaway.user.service.IUserService;
import org.springframework.stereotype.Service;

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
public class UserService extends ServiceImpl<IUserDao, User> implements IUserService {
    @Override
    public Optional<User> selectByName(String name) {
        return this.baseMapper.selectByName(name);
    }

    @Override
    public Optional<User> selectByOpenId(String openId) {
        return this.baseMapper.selectByOpenId(openId);
    }

    @Override
    public IPage<User> getUserListByPage(Page page, Map args) {
        return this.baseMapper.getUserListByPage(page, args);
    }
}
