package net.novaborn.takeaway.admin.web.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

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

    private PerHourOrderList perHourOrderList;

    @Data
    private class PerHourOrderList {
    }
}
