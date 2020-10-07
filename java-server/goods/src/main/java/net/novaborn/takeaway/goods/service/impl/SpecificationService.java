package net.novaborn.takeaway.goods.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.goods.dao.ISpecificationDao;
import net.novaborn.takeaway.goods.entity.Specification;
import net.novaborn.takeaway.goods.service.ISpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaohun
 * @since 2019-09-20
 */
@Slf4j
@Service
@Setter(onMethod_ = {@Autowired})
public class SpecificationService extends ServiceImpl<ISpecificationDao, Specification> implements ISpecificationService {
}
