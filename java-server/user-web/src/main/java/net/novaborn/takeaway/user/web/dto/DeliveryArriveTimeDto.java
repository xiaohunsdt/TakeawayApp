package net.novaborn.takeaway.user.web.dto;

import cn.hutool.core.date.DateUtil;
import lombok.Data;

import java.util.Date;

/**
 * 预计到达时间
 *
 * @author xiaohun
 */
@Data
public class DeliveryArriveTimeDto {
    private String date;
    private String time;

    public DeliveryArriveTimeDto(Date date) {
        Date currentDate = new Date();
        if (DateUtil.isSameDay(date, currentDate)) {
            this.date = "今天";
        } else if (DateUtil.betweenDay(currentDate, date, true) == 1) {
            this.date = "明天";
        } else if (DateUtil.betweenDay(currentDate, date, true) == 2) {
            this.date = "后天";
        } else {
            this.date = DateUtil.format(date, "MM-dd");
        }

        this.time = DateUtil.format(date, "HH:mm");
    }
}
