package net.novaborn.takeaway.admin.task;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.admin.config.properties.SystemProperties;
import net.novaborn.takeaway.common.entity.SysContext;
import net.novaborn.takeaway.goods.entity.Produce;
import net.novaborn.takeaway.goods.enums.ProduceState;
import net.novaborn.takeaway.goods.service.impl.GoodsService;
import net.novaborn.takeaway.goods.service.impl.ProduceService;
import net.novaborn.takeaway.mq.dto.AutoMessage;
import net.novaborn.takeaway.mq.sender.WechatAutoSender;
import net.novaborn.takeaway.order.entity.Order;
import net.novaborn.takeaway.order.entity.OrderItem;
import net.novaborn.takeaway.order.service.impl.OrderItemService;
import net.novaborn.takeaway.quartz.constant.ScheduleConstants;
import net.novaborn.takeaway.quartz.entity.SysJob;
import net.novaborn.takeaway.quartz.service.impl.SysJobServiceImpl;
import net.novaborn.takeaway.system.entity.Setting;
import net.novaborn.takeaway.system.enums.SettingScope;
import net.novaborn.takeaway.system.service.impl.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
public class WechatAutoTask {
    private final String jobGroup = "wechat-auto";

    @Autowired
    SysContext sysContext;

    @Autowired
    SysJobServiceImpl sysJobService;

    @Autowired
    GoodsService goodsService;

    @Autowired
    ProduceService produceService;

    @Autowired
    SettingService settingService;

    @Autowired
    OrderItemService orderItemService;

    @Autowired
    WechatAutoSender wechatAutoSender;

    @Autowired
    SystemProperties systemProperties;

    String store_open_date;

    DateTime storeOpenTime;

    DateTime storeCloseTime;

    private List<Produce> produceList = new ArrayList<>();

    @SneakyThrows
    @PostConstruct
    public void init() {
        sysContext.setCurrentStoreId(1302193963869949953L);
        String store_open_time = settingService.getSettingByName("store_open_time", SettingScope.STORE).getValue();
        String store_close_time = settingService.getSettingByName("store_close_time", SettingScope.STORE).getValue();

        storeOpenTime = DateUtil.parseDateTime(store_open_time);
        storeCloseTime = DateUtil.parseDateTime(store_close_time);

        SysJob sysJob = new SysJob();
        sysJob.setJobId(1L);
        sysJob.setJobGroup(jobGroup);
        sysJob.setCronExpression(StrUtil.format("0 0/10 {}-{} * * ?", DateUtil.offsetHour(storeOpenTime, -1).getField(DateField.HOUR_OF_DAY), storeCloseTime.getField(DateField.HOUR_OF_DAY) + 1));
        sysJob.setInvokeTarget("wechatAutoTask.goodsShow()");
        sysJob.setStatus(ScheduleConstants.Status.NORMAL);
        sysJob.setConcurrent(false);
        sysJobService.insertJob(sysJob);


        int startHours = storeOpenTime.getField(DateField.HOUR_OF_DAY) - 4;
        startHours = Math.max(startHours, 0);

        sysJob = new SysJob();
        sysJob.setJobId(2L);
        sysJob.setJobGroup(jobGroup);
        sysJob.setCronExpression(StrUtil.format("0 0 {}-{} * * ?", startHours, storeOpenTime.getField(DateField.HOUR_OF_DAY)));
        sysJob.setInvokeTarget("wechatAutoTask.appointmentShow()");
        sysJob.setStatus(ScheduleConstants.Status.NORMAL);
        sysJob.setConcurrent(false);
        sysJobService.insertJob(sysJob);

        sysJob = new SysJob();
        sysJob.setJobId(3L);
        sysJob.setJobGroup(jobGroup);
        sysJob.setCronExpression(StrUtil.format("0 0 {}-{} * * ?", DateUtil.offsetHour(storeOpenTime, -1).getField(DateField.HOUR_OF_DAY), storeCloseTime.getField(DateField.HOUR_OF_DAY) + 1));
        sysJob.setInvokeTarget("wechatAutoTask.activityShow()");
        sysJob.setStatus(ScheduleConstants.Status.NORMAL);
        sysJob.setConcurrent(false);
        sysJobService.insertJob(sysJob);
    }

    public void goodsShow() {
        sysContext.setCurrentStoreId(1302193963869949953L);
        DateTime currentDate = DateUtil.date();
        Setting service_running = settingService.getSettingByName("service_running", SettingScope.SYSTEM);
        store_open_date = settingService.getSettingByName("store_open_date", SettingScope.STORE).getValue();

        storeOpenTime = storeOpenTime
                .setField(DateField.YEAR, currentDate.getField(DateField.YEAR))
                .setField(DateField.MONTH, currentDate.getField(DateField.MONTH))
                .setField(DateField.DAY_OF_MONTH, currentDate.getField(DateField.DAY_OF_MONTH));
        storeCloseTime = storeCloseTime
                .setField(DateField.YEAR, currentDate.getField(DateField.YEAR))
                .setField(DateField.MONTH, currentDate.getField(DateField.MONTH))
                .setField(DateField.DAY_OF_MONTH, currentDate.getField(DateField.DAY_OF_MONTH));
        if (storeOpenTime.isAfter(storeCloseTime)) {
            if (currentDate.isBefore(storeCloseTime)) {
                storeOpenTime.offset(DateField.DAY_OF_YEAR, -1);
            } else {
                storeCloseTime.offset(DateField.DAY_OF_YEAR, 1);
            }
        }

        if (!Boolean.parseBoolean(service_running.getValue())) {
            return;
        }

        if (!store_open_date.contains(String.valueOf(DateUtil.dayOfWeek(currentDate)))) {
            return;
        }

        if (!DateUtil.isIn(currentDate, DateUtil.offsetHour(storeOpenTime, -1), storeCloseTime)) {
            return;
        }

        List<Produce> selectedProduces = new ArrayList<>();
        if (produceList.size() == 0) {
            LambdaQueryWrapper<Produce> query = Wrappers.lambdaQuery();
            query.eq(Produce::getStoreId, sysContext.getCurrentStoreId());

            produceList = produceService.list(query).parallelStream()
                    .filter(produce -> produce.getState() == ProduceState.ON || produce.getState() == ProduceState.PART_SHORTAGE)
                    .sorted(Comparator.comparing(Produce::getCategoryId))
                    .collect(Collectors.toList());
        }

        int index = RandomUtil.randomInt(produceList.size());
        selectedProduces.add(produceList.remove(index));

        for (int i = 1; i <= RandomUtil.randomInt(2, 4); i++) {
            if (index + i < produceList.size()) {
                selectedProduces.add(produceList.remove(index + i));
            } else if (index - i >= 0 && index - i < produceList.size()) {
                selectedProduces.add(produceList.remove(index - i));
            }
        }

        if (selectedProduces.size() > 0) {
            sendAutoMessage(selectedProduces);
        }
    }

    public void orderShow(Order order) {
        if (sysContext.getCurrentStoreId() != 1302193963869949953L) {
            return;
        }

        if (order.getRealPrice() < 18000) {
            return;
        }

        sendOrderMessage(orderItemService.selectByOrderId(order.getId()));
    }

    public void activityShow() {
        sysContext.setCurrentStoreId(1302193963869949953L);

        DateTime currentDate = DateUtil.date();
        Setting service_running = settingService.getSettingByName("service_running", SettingScope.SYSTEM);
        store_open_date = settingService.getSettingByName("store_open_date", SettingScope.STORE).getValue();

        storeOpenTime = storeOpenTime
                .setField(DateField.YEAR, currentDate.getField(DateField.YEAR))
                .setField(DateField.MONTH, currentDate.getField(DateField.MONTH))
                .setField(DateField.DAY_OF_MONTH, currentDate.getField(DateField.DAY_OF_MONTH));
        storeCloseTime = storeCloseTime
                .setField(DateField.YEAR, currentDate.getField(DateField.YEAR))
                .setField(DateField.MONTH, currentDate.getField(DateField.MONTH))
                .setField(DateField.DAY_OF_MONTH, currentDate.getField(DateField.DAY_OF_MONTH));
        if (storeOpenTime.isAfter(storeCloseTime)) {
            if (currentDate.isBefore(storeCloseTime)) {
                storeOpenTime.offset(DateField.DAY_OF_YEAR, -1);
            } else {
                storeCloseTime.offset(DateField.DAY_OF_YEAR, 1);
            }
        }

        if (!Boolean.parseBoolean(service_running.getValue())) {
            return;
        }

        if (!store_open_date.contains(String.valueOf(DateUtil.dayOfWeek(currentDate)))) {
            return;
        }

        if (!DateUtil.isIn(currentDate, DateUtil.offsetHour(storeOpenTime, -1), storeCloseTime)) {
            return;
        }

        AutoMessage autoMessage = new AutoMessage();
        autoMessage.setMessage(
                "川香苑双旦活动来啦!!![社会社会][社会社会]\n" +
                        "超值套餐~满赠福利~应有尽有！！[拥抱][拥抱][拥抱]\n" +
                        "点餐的时候千万别忘记把海报保存，发到自己的朋友圈哦，还可以再拿一枚卤蛋~~~[哇][哇][哇]\n"
        );
        autoMessage.setImgUrlList(
                Arrays.asList(
                        "https://admin.cxy.novaborn.net//upload/images/activity/6ad326e8bac74168bbe3e9c2555a1f5e.png",
                        "https://admin.cxy.novaborn.net/upload/images/81a03db04e174b02baec78e577fa8faa.jpeg",
                        "https://admin.cxy.novaborn.net/upload/images/6115d8e486864d0ebb7ab39a90c15b46.jpg",
                        "https://admin.cxy.novaborn.net/upload/images/f35fbd7789cc457887491a27b81bd3b0.jpeg",
                        "https://admin.cxy.novaborn.net/upload/images/22da6d92c1c34ce8ad5b441714aa0b7a.jpg",
                        "https://admin.cxy.novaborn.net/upload/images/761c3384008549edbf90e0c59692b5b3.jpeg",
                        "https://admin.cxy.novaborn.net/upload/images/1a013c1bc8ed4de8a326c84b1b4a7bc6.jpg",
                        "https://admin.cxy.novaborn.net/upload/images/18d01f6bfca34a1686177bea22f0cef3.jpeg"
                )
        );

        wechatAutoSender.send(autoMessage);
        log.info("WechatAuto: 已发送给队列 {}", autoMessage);
    }

    public void appointmentShow() {
        sysContext.setCurrentStoreId(1302193963869949953L);

        Date currentDate = DateUtil.date();
        Setting service_running = settingService.getSettingByName("service_running", SettingScope.SYSTEM);
        store_open_date = settingService.getSettingByName("store_open_date", SettingScope.STORE).getValue();

        if (!Boolean.parseBoolean(service_running.getValue())) {
            return;
        }

        if (!store_open_date.contains(String.valueOf(DateUtil.dayOfWeek(currentDate)))) {
            return;
        }

        AutoMessage autoMessage = new AutoMessage();
//        autoMessage.setMessage(StrUtil.format("今天正常营业哦～[社会社会][社会社会][社会社会]\r\n小伙伴们现在就可以下预约单!![机智][机智]{}开始接单配送～～\r\n优先准时配送！！再也不用担心下课吃不到饭啦！！[拥抱][拥抱]", TimeUtil.toString(storeOpenTime)));
        autoMessage.setMessage(StrUtil.format("今天正常营业哦～[社会社会][社会社会][社会社会]\r\n小程序架构更新!!有任何问题请联系我们微信客服哦~~[拥抱][拥抱]", DateUtil.format(storeOpenTime, "HH:mm")));
        autoMessage.setImgUrlList(
                Arrays.asList(
                        "https://admin.cxy.novaborn.net/upload/images/banner/75e8d7a1f82346a58ae9ff164e4ca5ac.jpg",
                        "https://admin.cxy.novaborn.net/upload/images/banner/75cb5085875f41a68430ed3117ad5786.jpg"
                )
        );

        wechatAutoSender.send(autoMessage);
        log.info("WechatAuto: 已发送给队列 {}", autoMessage);
    }

    public void sendOrderMessage(List<OrderItem> selectedOrderItems) {
        DateTime currentDate = DateUtil.date();
        storeOpenTime = storeOpenTime
                .setField(DateField.YEAR, currentDate.getField(DateField.YEAR))
                .setField(DateField.MONTH, currentDate.getField(DateField.MONTH))
                .setField(DateField.DAY_OF_MONTH, currentDate.getField(DateField.DAY_OF_MONTH));
        storeCloseTime = storeCloseTime
                .setField(DateField.YEAR, currentDate.getField(DateField.YEAR))
                .setField(DateField.MONTH, currentDate.getField(DateField.MONTH))
                .setField(DateField.DAY_OF_MONTH, currentDate.getField(DateField.DAY_OF_MONTH));
        if (storeOpenTime.isAfter(storeCloseTime)) {
            if (currentDate.isBefore(storeCloseTime)) {
                storeOpenTime.offset(DateField.DAY_OF_YEAR, -1);
            } else {
                storeCloseTime.offset(DateField.DAY_OF_YEAR, 1);
            }
        }

        String names = selectedOrderItems.stream().map(OrderItem::getProduceName).collect(Collectors.joining(", "));
        String desc = selectedOrderItems.stream()
                .map(orderItem -> {
                    Produce produce = produceService.getById(orderItem.getProduceId());
                    if (StrUtil.isBlank(produce.getDesc())) {
//                        return StrUtil.format("{}, {}\uD83D\uDCB0", produce.getName(), goods.getPrice());
                        return StrUtil.format("{}", produce.getName());
                    } else {
//                        return StrUtil.format("{}, {}\uD83D\uDCB0, {}", produce.getName(), goods.getPrice(), produce.getDesc());
                        return StrUtil.format("{}, {}", produce.getName(), produce.getDesc());
                    }
                })
                .collect(Collectors.joining("\r\n"));
        List<String> imgs = selectedOrderItems.stream()
                .filter(item -> StrUtil.isNotBlank(item.getGoodsThumb()))
                .map(item -> systemProperties.getUploadServerUrl() + item.getGoodsThumb())
                .collect(Collectors.toList());

        String message;
        message = StrUtil.format("{}\r\n超级\uD83D\uDD25的人气菜品安排走单！！\uD83D\uDE0B\r\n{}\r\n同款\uD83C\uDE51安排哦,现在点餐30-40分钟送达[哇][哇][哇]", names, desc);
        long offend = DateUtil.between(currentDate, storeCloseTime, DateUnit.SECOND);
        if (offend < 60 * 60 && offend > 0) {
            message += StrUtil.format("\r\n最后接单{}分钟, 接单到{}, 还没吃饭的宝宝们抓紧啦！！！", offend / 60, DateUtil.format(storeCloseTime, "HH:mm"));
        }
//
//        if (names.contains("暑假特惠")) {
//            imgs.add(0, "https://admin.cxy.novaborn.net/upload/images/activity/083f0f4e9e244601b740620f933cb061.png");
//        }

        AutoMessage autoMessage = new AutoMessage();
        autoMessage.setMessage(message);
        autoMessage.setImgUrlList(imgs);
        wechatAutoSender.send(autoMessage);

        log.info("WechatAuto: 已发送给队列 {}", autoMessage);
    }

    public void sendAutoMessage(List<Produce> selectedProduces) {
        DateTime currentDate = DateUtil.date();
        storeOpenTime = storeOpenTime
                .setField(DateField.YEAR, currentDate.getField(DateField.YEAR))
                .setField(DateField.MONTH, currentDate.getField(DateField.MONTH))
                .setField(DateField.DAY_OF_MONTH, currentDate.getField(DateField.DAY_OF_MONTH));
        storeCloseTime = storeCloseTime
                .setField(DateField.YEAR, currentDate.getField(DateField.YEAR))
                .setField(DateField.MONTH, currentDate.getField(DateField.MONTH))
                .setField(DateField.DAY_OF_MONTH, currentDate.getField(DateField.DAY_OF_MONTH));
        if (storeOpenTime.isAfter(storeCloseTime)) {
            if (currentDate.isBefore(storeCloseTime)) {
                storeOpenTime.offset(DateField.DAY_OF_YEAR, -1);
            } else {
                storeCloseTime.offset(DateField.DAY_OF_YEAR, 1);
            }
        }

        String names = selectedProduces.stream().map(Produce::getName).collect(Collectors.joining(", "));
        String desc = selectedProduces.stream()
                .map(goods -> {
                    if (StrUtil.isBlank(goods.getDesc())) {
//                        return StrUtil.format("{}, {}\uD83D\uDCB0", goods.getName(), goods.getPrice());
                        return StrUtil.format("{}", goods.getName());
                    } else {
                        return StrUtil.format("{}, {}", goods.getName(), goods.getDesc());
//                        return StrUtil.format("{}, {}\uD83D\uDCB0, {}", goods.getName(), goods.getPrice(), goods.getDesc());
                    }
                })
                .collect(Collectors.joining("\r\n"));
        List<String> imgs = selectedProduces.stream()
                .filter(goods -> StrUtil.isNotBlank(goods.getThumb()))
                .map(goods -> systemProperties.getUploadServerUrl() + goods.getThumb())
                .collect(Collectors.toList());

        String message;
        message = StrUtil.format("{}\r\n{}\r\n{}", names, desc, "\n现在点餐30-40分钟送达[哇][哇][哇]");

        long offend = DateUtil.between(currentDate, storeCloseTime, DateUnit.SECOND);
        if (offend < 60 * 60 && offend > 0) {
            message += StrUtil.format("\r\n最后接单{}分钟, 接单到{}, 还没吃饭的宝宝们抓紧啦！！！", offend / 60, DateUtil.format(storeCloseTime, "HH:mm"));
        }
//
//        if (names.contains("暑假特惠")) {
//            imgs.add(0, "https://admin.cxy.novaborn.net/upload/images/activity/083f0f4e9e244601b740620f933cb061.png");
//        }

        AutoMessage autoMessage = new AutoMessage();
        autoMessage.setMessage(message);
        autoMessage.setImgUrlList(imgs);
        wechatAutoSender.send(autoMessage);

        log.info("WechatAuto: 已发送给队列 {}", autoMessage);
    }
}
