package net.novaborn.takeaway.activity.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.novaborn.takeaway.activity.dao.IGoodsDao;
import net.novaborn.takeaway.activity.entity.Goods;
import net.novaborn.takeaway.activity.service.IGoodsService;
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
public class GoodsService extends ServiceImpl<IGoodsDao, Goods> implements IGoodsService {

}
