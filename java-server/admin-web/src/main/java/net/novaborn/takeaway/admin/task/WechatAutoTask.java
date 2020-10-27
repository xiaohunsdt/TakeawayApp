package net.novaborn.takeaway.admin.task;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.admin.common.SysContext;
import net.novaborn.takeaway.admin.config.properties.SystemProperties;
import net.novaborn.takeaway.common.utils.TimeUtil;
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

//        sysJob = new SysJob();
//        sysJob.setJobId(3L);
//        sysJob.setJobGroup(jobGroup);
//        sysJob.setCronExpression(StrUtil.format("0 0 {}-{} * * ?", DateUtil.offsetHour(storeOpenTime, -1).getField(DateField.HOUR_OF_DAY), storeCloseTime.getField(DateField.HOUR_OF_DAY) + 1));
//        sysJob.setInvokeTarget("wechatAutoTask.activityShow()");
//        sysJob.setStatus(ScheduleConstants.Status.NORMAL);
//        sysJob.setConcurrent(false);
//        sysJobService.insertJob(sysJob);
    }

    public void goodsShow() {
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

        if (!TimeUtil.isBetween(currentDate, DateUtil.offsetHour(storeOpenTime, -1), storeCloseTime)) {
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
        if (order.getStoreId() != 1302193963869949953L) {
            return;
        }
        sysContext.setCurrentStoreId(order.getStoreId());

        if (order.getRealPrice() < 18000) {
            return;
        }

        sendOrderMessage(orderItemService.selectByOrderId(order.getId()));
    }

    public void activityShow() {
        Date currentDate = DateUtil.date();
        Setting service_running = settingService.getSettingByName("service_running", SettingScope.SYSTEM);
        store_open_date = settingService.getSettingByName("store_open_date", SettingScope.STORE).getValue();

        if (!Boolean.parseBoolean(service_running.getValue())) {
            return;
        }

        if (!store_open_date.contains(String.valueOf(DateUtil.dayOfWeek(currentDate)))) {
            return;
        }

        if (!TimeUtil.isBetween(currentDate, DateUtil.offsetHour(storeOpenTime, -1), storeCloseTime)) {
            return;
        }

        AutoMessage autoMessage = new AutoMessage();
        autoMessage.setMessage(
            "月饼还剩一点五仁哦！[拥抱][拥抱][拥抱]\n" +
                "今天点餐继续送一块月饼哦！！小伙伴们抓紧时间点餐!\n"
        );
        autoMessage.setImgUrlList(
            Arrays.asList(
                "https://admin.cxy.novaborn.net/upload/images/activity/ae4e1ef3057a4752993184c7ca296650.png",
                "https://admin.cxy.novaborn.net/upload/images/ebd8dedc55d246a7ba04dd931dcaa113.jpeg"
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
        autoMessage.setMessage(StrUtil.format("今天正常营业哦～[社会社会][社会社会][社会社会]\r\n小程序架构更新!!有任何问题请联系我们微信客服哦~~[拥抱][拥抱]", TimeUtil.toString(storeOpenTime)));
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
        long offend = TimeUtil.between(new Date(), storeCloseTime);
        if (offend < 60 * 60 && offend > 0) {
            message += StrUtil.format("\r\n最后接单{}分钟, 接单到{}, 还没吃饭的宝宝们抓紧啦！！！", offend / 60, TimeUtil.toString(storeCloseTime));
        }

        if (names.contains("暑假特惠")) {
            imgs.add(0, "https://admin.cxy.novaborn.net/upload/images/activity/083f0f4e9e244601b740620f933cb061.png");
        }

        AutoMessage autoMessage = new AutoMessage();
        autoMessage.setMessage(message);
        autoMessage.setImgUrlList(imgs);
        wechatAutoSender.send(autoMessage);

        log.info("WechatAuto: 已发送给队列 {}", autoMessage);
    }

    public void sendAutoMessage(List<Produce> selectedProduces) {
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

        long offend = TimeUtil.between(new Date(), storeCloseTime);
        if (offend < 60 * 60 && offend > 0) {
            message += StrUtil.format("\r\n最后接单{}分钟, 接单到{}, 还没吃饭的宝宝们抓紧啦！！！", offend / 60, TimeUtil.toString(storeCloseTime));
        }

        if (names.contains("暑假特惠")) {
            imgs.add(0, "https://admin.cxy.novaborn.net/upload/images/activity/083f0f4e9e244601b740620f933cb061.png");
        }

        AutoMessage autoMessage = new AutoMessage();
        autoMessage.setMessage(message);
        autoMessage.setImgUrlList(imgs);
        wechatAutoSender.send(autoMessage);

        log.info("WechatAuto: 已发送给队列 {}", autoMessage);
    }
}
