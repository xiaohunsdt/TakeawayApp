package net.novaborn.takeaway.admin.web.dto;

import lombok.Data;
import net.novaborn.takeaway.goods.entity.Produce;
import net.novaborn.takeaway.goods.entity.ProduceSpec;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
public class ProduceDto implements Serializable {
    private Produce produce;

    private ProduceSpec specs;

    @Valid
    @NotNull(message = "商品列表必须设置")
    private List<GoodsDto> goodsList;
}
