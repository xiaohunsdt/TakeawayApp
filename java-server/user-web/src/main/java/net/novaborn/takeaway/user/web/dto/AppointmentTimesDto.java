package net.novaborn.takeaway.user.web.dto;

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
        List<DateTime> times = new ArrayList();
        timePairs.forEach(item -> {
            DateTime start = DateTime.of(item.get("start"));
            DateTime end = DateTime.of(item.get("end"));

            start = DateUtil.ceiling(start, DateField.MINUTE)
                .setField(DateField.MINUTE, (start.getField(DateField.MINUTE) / 10 + 1) * 10);
            do {
                times.add(start);
                start = DateUtil.offsetMinute(start, 10);
            } while (start.before(end));
        });

        Set<Integer> days = times.stream()
            .map((time) -> time.getField(DateField.DAY_OF_YEAR))
            .collect(Collectors.toSet());

        days.forEach(day -> {
            Map<String, List<Integer>> timeMap = new LinkedHashMap<>();
            List<DateTime> currentDates = times.stream().filter(time -> time.getField(DateField.DAY_OF_YEAR) == day).collect(Collectors.toList());
            List<Integer> hours = currentDates.stream().map(time -> time.getField(DateField.HOUR_OF_DAY)).collect(Collectors.toList());
            hours.forEach(hour -> {
                List<Integer> minutes = currentDates.parallelStream()
                    .filter((tempTime) -> tempTime.getField(DateField.HOUR_OF_DAY) == hour)
                    .map((tempTime) -> tempTime.getField(DateField.MINUTE))
                    .collect(Collectors.toList());
                timeMap.put(String.valueOf(hour), minutes);
            });
            appointmentTimes.put(getDayFormatStr(day), timeMap);
        });
    }

    private String getDayFormatStr(int day) {
        String dateStr;
        DateTime start = DateTime.now().setField(DateField.DAY_OF_YEAR, day);
        DateTime currentDate = DateTime.now();

        if (DateUtil.isSameDay(start, currentDate)) {
            dateStr = "今天";
        } else if (DateUtil.betweenDay(currentDate, start, true) == 1) {
            dateStr = "明天";
        } else if (DateUtil.betweenDay(currentDate, start, true) == 2) {
            dateStr = "后天";
        } else {
            dateStr = DateUtil.format(start, "MM-dd");
        }

        return dateStr;
    }
}
