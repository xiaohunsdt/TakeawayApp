package net.novaborn.takeaway.goods.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import net.novaborn.takeaway.goods.entity.Goods;
import net.novaborn.takeaway.goods.entity.ProduceSpec;
import net.novaborn.takeaway.goods.entity.Specification;
import org.apache.ibatis.annotations.Param;

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
public interface ISpecificationService extends IService<Specification> {
    /**
     * 根据key获取指定规格
     *
     * @param key 规格名称
     * @return 查询到的规格
     */
    Optional<Specification> selectByKey(String key);

    /**
     * 分页获取列表
     * @param page   分页实例
     * @param args   key
     * @return 结果列表
     */
    IPage<Specification> getListByPage(Page page, Map args);
}
