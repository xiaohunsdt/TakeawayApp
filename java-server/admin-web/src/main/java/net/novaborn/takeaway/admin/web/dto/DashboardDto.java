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

    private List<Map.Entry<String, Integer>> topSaleGoodsList;

    public void setTopSaleGoodsList(List<Map.Entry<String, Integer>> topSaleGoodsList) {
        if (topSaleGoodsList.size() > 14) {
            this.topSaleGoodsList = topSaleGoodsList.subList(0, 14);
        } else {
            this.topSaleGoodsList = topSaleGoodsList;
        }
    }

    @Data
    public class PerHourOrderDto {
        private List hours;

        private List<Integer> preHourOrderCount;
    }
}
