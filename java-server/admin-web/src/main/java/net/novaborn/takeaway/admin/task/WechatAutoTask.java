package net.novaborn.takeaway.admin.task;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.admin.config.properties.SystemProperties;
import net.novaborn.takeaway.common.utils.TimeUtil;
import net.novaborn.takeaway.goods.entity.Goods;
import net.novaborn.takeaway.goods.enums.GoodsState;
import net.novaborn.takeaway.goods.service.impl.GoodsService;
import net.novaborn.takeaway.mq.dto.AutoMessage;
import net.novaborn.takeaway.mq.sender.WechatAutoSender;
import net.novaborn.takeaway.order.entity.Order;
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
    @Autowired
    SysJobServiceImpl sysJobService;

    @Autowired
    GoodsService goodsService;

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

    private final String jobGroup = "wechat-auto";

    private List<Goods> goodsList = new ArrayList<>();

    @SneakyThrows
    @PostConstruct
    public void init() {
        String store_open_time = settingService.getSettingByName("store_open_time", SettingScope.STORE).getValue();
        String store_close_time = settingService.getSettingByName("store_close_time", SettingScope.STORE).getValue();
        storeOpenTime = DateUtil.parseDateTime(store_open_time);
        storeCloseTime = DateUtil.parseDateTime(store_close_time);

        SysJob sysJob = new SysJob();
        sysJob.setJobId(1L);
        sysJob.setJobGroup(jobGroup);
        sysJob.setCronExpression(StrUtil.format("0 0/10 {}-{} * * ?", storeOpenTime.getField(DateField.HOUR_OF_DAY), storeCloseTime.getField(DateField.HOUR_OF_DAY) + 1));
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
    }

    public void goodsShow() {
        Date currentDate = DateUtil.date();
        Setting service_running = settingService.getSettingByName("service_running", SettingScope.SYSTEM);
        store_open_date = settingService.getSettingByName("store_open_date", SettingScope.STORE).getValue();

        if (!Boolean.parseBoolean(service_running.getValue())) {
            return;
        }

        if (!store_open_date.contains(String.valueOf(DateUtil.dayOfWeek(currentDate)))) {
            return;
        }

        if (!TimeUtil.isBetween(currentDate, storeOpenTime, storeCloseTime)) {
            return;
        }

        List<Goods> selectedGoods = new ArrayList<>();
        if (goodsList.size() == 0) {
            goodsList = goodsService.list().parallelStream()
                    .filter(goods -> goods.getState() == GoodsState.ON)
                    .sorted(Comparator.comparing(Goods::getCategoryId))
                    .collect(Collectors.toList());
        }

        int index = RandomUtil.randomInt(goodsList.size());
        selectedGoods.add(goodsList.remove(index));

        for (int i = 1; i <= RandomUtil.randomInt(2, 4); i++) {
            if (index + i < goodsList.size()) {
                selectedGoods.add(goodsList.remove(index + i));
            } else if (index - i >= 0 && index - i < goodsList.size()) {
                selectedGoods.add(goodsList.remove(index - i));
            }
        }

        if (selectedGoods.size() > 0) {
            sendAutoMessage(selectedGoods, false);
        }
    }

    public void orderShow(Order order) {
        if (order.getRealPrice() < 18000) {
            return;
        }

        List<Goods> goodsList = orderItemService.selectByOrderId(order.getId()).stream()
                .map(orderItem -> goodsService.getById(orderItem.getGoodsId()))
                .collect(Collectors.toList());

        sendAutoMessage(goodsList, true);
    }

    public void appointmentShow() {
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
        autoMessage.setMessage("祝大家端午节快乐！粽子可以安排，数量不多了，请大家提前预约！先到先得！！！");
        autoMessage.setImgUrlList(
                Arrays.asList(
                        "https://admin.cxy.novaborn.net/upload/images/banner/75cb5085875f41a68430ed3117ad5786.jpg",
                        "https://admin.cxy.novaborn.net/upload/images/activity/a106a0d932c647e4875b9d2faa871dae.png",
                        "https://admin.cxy.novaborn.net/upload/images/activity/c59fee8c3be64f8b89a63d13a15db4ce.png",
                        "https://admin.cxy.novaborn.net/upload/images/cac232b8fb2f452898f40bd5ded1c3c2.jpeg",
                        "https://admin.cxy.novaborn.net/upload/images/719e17c77a024d8c9c033d063758bf0f.jpeg"
                )
        );

        wechatAutoSender.send(autoMessage);
        log.info("WechatAuto: 已发送给队列 {}", autoMessage);
    }

    public void sendAutoMessage(List<Goods> selectedGoods, boolean isOrderShow) {
        String names = selectedGoods.stream().map(Goods::getName).collect(Collectors.joining(", "));
        String desc = selectedGoods.stream()
                .map(goods -> {
                    if (StrUtil.isBlank(goods.getDesc())) {
                        return StrUtil.format("{}, {}\uD83D\uDCB0", goods.getName(), goods.getPrice());
                    } else {
                        return StrUtil.format("{}, {}\uD83D\uDCB0, {}", goods.getName(), goods.getPrice(), goods.getDesc());
                    }
                })
                .collect(Collectors.joining("\r\n"));
        List<String> imgs = selectedGoods.stream()
                .filter(goods -> StrUtil.isNotBlank(goods.getThumb()))
                .map(goods -> systemProperties.getUploadServerUrl() + goods.getThumb())
                .collect(Collectors.toList());

        String message;
        if (!isOrderShow) {
            message = StrUtil.format("{}\r\n{}\r\n{}", names, desc, "\n今天开始2位外卖员＋第三方配送公司为大家配送！请小伙伴们放心点餐[社会社会][社会社会],现在点餐30-40分钟送达[哇][哇][哇]");
        } else {
            message = StrUtil.format("{}\r\n超级\uD83D\uDD25的人气菜品安排走单！！\uD83D\uDE0B\r\n{}\r\n{}", names, desc, "同款\uD83C\uDE51安排哦, 今天开始2位外卖员＋第三方配送公司为大家配送！请小伙伴们放心点餐[社会社会][社会社会],现在点餐30-40分钟送达[哇][哇][哇]");
        }

        long offend = TimeUtil.between(new Date(), storeCloseTime);
        if (offend < 60 * 60 && offend > 0) {
            message += StrUtil.format("\r\n最后接单{}分钟, 接单到{}, 还没吃饭的宝宝们抓紧啦！！！", offend / 60, TimeUtil.toString(storeCloseTime));
        }

        if (selectedGoods.contains(new Goods("f30d90927885aa5a19b339db8f08f910")) || selectedGoods.contains(new Goods("2f014dd8475feb73cd4d0f6f9b52a9de"))) {
            imgs.add(0,"https://admin.cxy.novaborn.net/upload/images/activity/c59fee8c3be64f8b89a63d13a15db4ce.png");
        }

        AutoMessage autoMessage = new AutoMessage();
        autoMessage.setMessage(message);
        autoMessage.setImgUrlList(imgs);
        wechatAutoSender.send(autoMessage);

        log.info("WechatAuto: 已发送给队列 {}", autoMessage);
    }
}
