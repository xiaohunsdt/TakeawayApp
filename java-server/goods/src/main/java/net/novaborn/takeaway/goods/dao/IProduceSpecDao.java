package net.novaborn.takeaway.goods.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.novaborn.takeaway.goods.entity.ProduceSpec;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

/**
 * @author xiaohun
 * @since 2019-09-20
 */
public interface IProduceSpecDao extends BaseMapper<ProduceSpec> {
    /**
     * 根据产品id获取指定产品的规格
     * @param produceId 产品Id
     * @return 查询到的产品规格
     */
    Optional<ProduceSpec> selectByProduceId(@Param("produceId") Long produceId);
}
