package net.novaborn.takeaway.goods.service;

import com.baomidou.mybatisplus.extension.service.IService;
import net.novaborn.takeaway.goods.entity.Produce;
import net.novaborn.takeaway.goods.entity.ProduceSpec;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xiaohun
 * @since 2019-09-20
 */
public interface IProduceSpecService extends IService<ProduceSpec> {
    /**
     * 根据产品id获取指定产品的规格
     * @param produceId 产品Id
     * @return 查询到的产品规格
     */
    Optional<ProduceSpec> selectByProduceId(@Param("produceId") Long produceId);
}
