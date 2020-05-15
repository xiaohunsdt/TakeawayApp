package net.novaborn.takeaway.admin.web.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class DashboardDto {
    private int wechatOrderAllCount;

    private int wechatOrderAllPrice;

    private int alipayOrderAllCount;

    private int alipayOrderAllPrice;

    private int transferOrderAllCount;

    private int transferOrderAllPrice;

    private int creditOrderAllCount;

    private int creditOrderAllPrice;

    private int cashOrderAllCount;

    private int cashOrderAllPrice;

    private int allCount;

    private int allPrice;

    private int waitDeliveryCount;

    private int deliveringCount;

    private int finishCount;

    private int refundCount;

    private PerHourOrderDto perHourOrderCount = new PerHourOrderDto();

    @Data
    public class PerHourOrderDto {
        private List hours;

        private List<Integer> preHourOrderCount;
    }
}
