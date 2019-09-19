package net.novaborn.takeaway.category.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.novaborn.takeaway.category.dao.ICategoryDao;
import net.novaborn.takeaway.category.entity.Category;
import net.novaborn.takeaway.category.service.ICategoryService;
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
public class CategoryService extends ServiceImpl<ICategoryDao, Category> implements ICategoryService {

}
