package net.novaborn.takeaway.category.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.novaborn.takeaway.category.entity.Category;
import org.apache.ibatis.annotations.Param;

import java.util.Map;
import java.util.Optional;

/**
 * @author xiaohun
 * @since 2019-09-20
 */
public interface ICategoryDao extends BaseMapper<Category> {
    /**
     * 根据分类名称获取指定分类
     * @param name 分类名称
     * @return 查询到的分类
     */
    Optional<Category> selectByName(@Param("name") String name);

    /**
     * 分页获取分类列表
     * @param page   分页实例
     * @param args   name/categoryId
     * @return 分类列表
     */
    IPage<Category> getCategoryListByPage(Page page, @Param("args") Map args);
}
