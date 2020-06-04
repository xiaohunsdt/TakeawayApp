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
import net.novaborn.takeaway.quartz.constant.ScheduleConstants;
import net.novaborn.takeaway.quartz.entity.SysJob;
import net.novaborn.takeaway.quartz.service.impl.SysJobServiceImpl;
import net.novaborn.takeaway.system.entity.Setting;
import net.novaborn.takeaway.system.enums.SettingScope;
import net.novaborn.takeaway.system.service.impl.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class WechatAutoTask {
    private final String jobGroup = "wechat-auto";
    @Autowired
    SysJobServiceImpl sysJobService;
    @Autowired
    GoodsService goodsService;
    @Autowired
    SettingService settingService;
    @Autowired
    WechatAutoSender wechatAutoSender;
    @Autowired
    SystemProperties systemProperties;
    String store_open_date;
    DateTime storeOpenTime;
    DateTime storeCloseTime;
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
        sysJob.setCronExpression(StrUtil.format("0 0/8 {}-{} * * ?", storeOpenTime.getField(DateField.HOUR_OF_DAY), storeCloseTime.getField(DateField.HOUR_OF_DAY) + 1));
        sysJob.setInvokeTarget("wechatAutoTask.goodsShow()");
        sysJob.setStatus(ScheduleConstants.Status.NORMAL);
        sysJob.setConcurrent(false);
        sysJobService.insertJob(sysJob);
    }

    public void goodsShow() {
        Date currentDate = DateUtil.date();
        Setting service_running = settingService.getSettingByName("service_running", SettingScope.SYSTEM);
        String store_open_date = settingService.getSettingByName("store_open_date", SettingScope.STORE).getValue();

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
            } else if (index - i < goodsList.size()) {
                selectedGoods.add(goodsList.remove(index - i));
            }
        }

        if (selectedGoods.size() > 0) {
            sendAutoMessage(selectedGoods);
        }
    }

    public void sendAutoMessage(List<Goods> selectedGoods) {
        String names = selectedGoods.stream().map(Goods::getName).collect(Collectors.joining(", "));
        String desc = selectedGoods.stream()
                .filter(goods -> StrUtil.isNotBlank(goods.getDesc()))
                .map(goods -> StrUtil.format("{}, {}韩币, {}", goods.getName(), goods.getPrice(), goods.getDesc()))
                .collect(Collectors.joining("\r\n"));
        List<String> imgs = selectedGoods.stream()
                .filter(goods -> StrUtil.isNotBlank(goods.getThumb()))
                .map(goods -> systemProperties.getUploadServerUrl() + goods.getThumb())
                .collect(Collectors.toList());

        AutoMessage autoMessage = new AutoMessage();
        autoMessage.setMessage(StrUtil.format("{}\r\n\r\n{}\r\n{}", names, desc, "现在点餐30-40分钟送达"));
        autoMessage.setImgUrlList(imgs);
        wechatAutoSender.send(autoMessage);

        log.info("WechatAuto: 已发送给队列 {}", autoMessage);
    }
}
