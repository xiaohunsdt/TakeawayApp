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
     * 检查指定spec是否被使用
     * @param specId
     * @return
     */
    boolean checkSpecBeUsed(Long specId);
}
