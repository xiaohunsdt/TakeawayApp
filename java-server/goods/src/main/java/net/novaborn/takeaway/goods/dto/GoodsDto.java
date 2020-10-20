package net.novaborn.takeaway.goods.dto;

import lombok.Data;
import net.novaborn.takeaway.goods.entity.Goods;
import net.novaborn.takeaway.goods.entity.Produce;
import net.novaborn.takeaway.goods.entity.ProduceSpec;

import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.List;

@Data
public class GoodsDto extends Goods implements Serializable {

    @Min(value = 1,message = "金额不能为0")
    private Integer price;

    private Integer stock;
}
