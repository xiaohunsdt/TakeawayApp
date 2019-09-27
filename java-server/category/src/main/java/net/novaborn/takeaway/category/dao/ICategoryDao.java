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
    Optional<Category> selectByName(@Param("name") String name);

    IPage<Category> getCategoryListByPage(Page page, @Param("args") Map args);
}
