package net.novaborn.takeaway.user.web.dto;

import lombok.Data;
import net.novaborn.takeaway.order.entity.Order;
import net.novaborn.takeaway.order.entity.OrderItem;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class GoodsPageSettingDto {
    private String goodsPageNotice;

    private List<String> goodsPageTags;

    private Boolean disableService;

    private String disableServiceNotice;
}
