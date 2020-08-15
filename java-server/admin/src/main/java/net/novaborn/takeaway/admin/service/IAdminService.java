package net.novaborn.takeaway.admin.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import net.novaborn.takeaway.admin.entity.Admin;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xiaohun
 * @since 2019-09-20
 */
public interface IAdminService extends IService<Admin> {

    /**
     * 管理员登陆
     *
     * @param userName
     * @param password
     * @return
     */
    boolean login(String userName, String password);

    /**
     * 分页获取子管理员列表
     *
     * @param page 分页实例
     * @param args  parentId    父Id
     *              level       等级
     *              state       状态
     * @return 用户列表
     */
    IPage<Admin> getSubAdminListByPage(Page page, Map args);
}
