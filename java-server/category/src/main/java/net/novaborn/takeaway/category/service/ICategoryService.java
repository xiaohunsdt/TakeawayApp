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
    /**
     * 根据分类名称获取指定分类
     * @param name 分类名称
     * @return 查询到的分类
     */
    Optional<Category> selectByName(String name);

    /**
     * 分页获取分类列表
     * @param page   分页实例
     * @param args   name/categoryId
     * @return 分类列表
     */
    IPage<Category> getCategoryListByPage(Page page, Map args);
}
