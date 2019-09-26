package net.novaborn.takeaway.category.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.novaborn.takeaway.category.dao.ICategoryDao;
import net.novaborn.takeaway.category.entity.Category;
import net.novaborn.takeaway.category.service.ICategoryService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaohun
 * @since 2019-09-20
 */
@Service
public class CategoryService extends ServiceImpl<ICategoryDao, Category> implements ICategoryService {

    @Override
    public IPage<Category> getCategoryListByPage(Page page, Map args) {
        return this.baseMapper.getCategoryListByPage(page, args);
    }
}
