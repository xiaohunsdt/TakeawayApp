package net.novaborn.takeaway.store.dto;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;

@Data
public class AppointmentTimesDto {
    private Boolean canDeliveryNow;
    private Map<String, Map<String, List<Integer>>> appointmentTimes = new LinkedHashMap<>(3);

    public AppointmentTimesDto(List<Map<String, Date>> timePairs, Boolean canDeliveryNow) {
        this.canDeliveryNow = canDeliveryNow;
        timePairs.forEach(item -> {
            DateTime currentDate = DateTime.now();
            List<DateTime> times = new ArrayList();
            DateTime start = DateTime.of(item.get("start"));
            DateTime end = DateTime.of(item.get("end"));
            String dateStr;

            if (DateUtil.isSameDay(start, currentDate)) {
                dateStr = "今天";
            } else if (DateUtil.betweenDay(currentDate, start, true) == 1) {
                dateStr = "明天";
            } else if (DateUtil.betweenDay(currentDate, start, true) == 2) {
                dateStr = "后天";
            } else {
                dateStr = DateUtil.format(start, "MM-dd");
            }

            start = DateUtil.ceiling(start, DateField.MINUTE)
                    .setField(DateField.MINUTE, (start.getField(DateField.MINUTE) / 10 + 1) * 10);
            do {
                times.add(start);
                start = DateUtil.offsetMinute(start, 10);
            } while (start.before(end));

            Map<String, List<Integer>> timeMap = new LinkedHashMap<>();
            times.stream()
                    .map((time) -> time.getField(DateField.HOUR_OF_DAY))
                    .forEach(hour -> {
                        List<Integer> minutes = times.parallelStream()
                                .filter((tempTime) -> tempTime.getField(DateField.HOUR_OF_DAY) == hour)
                                .map((tempTime) -> tempTime.getField(DateField.MINUTE))
                                .collect(Collectors.toList());
                        timeMap.put(String.valueOf(hour), minutes);
                    });
            appointmentTimes.put(dateStr, timeMap);
        });
    }
}
