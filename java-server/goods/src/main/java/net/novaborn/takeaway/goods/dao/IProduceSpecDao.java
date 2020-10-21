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
     * 返回包含specId的产品规格
     * @param specId
     * @return
     */
    List<ProduceSpec> getByspecId(@Param("specId") Long specId);

    /**
     * 返回包含specId的产品规格数量
     * @param specId
     * @return
     */
    int getCountByspecId(@Param("specId") Long specId);
}
