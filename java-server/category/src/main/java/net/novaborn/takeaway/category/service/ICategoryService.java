package net.novaborn.takeaway.category.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import net.novaborn.takeaway.category.entity.Category;

import java.util.Map;
import java.util.Optional;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xiaohun
 * @since 2019-09-20
 */
public interface ICategoryService extends IService<Category> {
    Optional<Category> selectByName(String name);

    IPage<Category> getCategoryListByPage(Page page, Map args);
}
