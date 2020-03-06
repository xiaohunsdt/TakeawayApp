package net.novaborn.takeaway.user.web.api;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.common.utils.TimeUtil;
import net.novaborn.takeaway.system.entity.Setting;
import net.novaborn.takeaway.system.enums.SettingScope;
import net.novaborn.takeaway.system.service.impl.SettingService;
import net.novaborn.takeaway.user.web.dto.GoodsPageSettingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author xiaohun
 */
@Slf4j
@Controller
@Setter(onMethod_ = {@Autowired})
@RequestMapping("/api/user/setting")
public class SettingController extends BaseController {
    private SettingService settingService;

    @GetMapping("getAllSetting")
    @ResponseBody
    public List<Setting> getAllSetting() {
        return settingService.list();
    }

    @PostMapping("getSettingByName")
    @ResponseBody
    public Setting getSettingByName(@RequestParam String key, @RequestParam SettingScope scope) {
        return settingService.getSettingByName(key, scope);
    }

    @PostMapping("getSettingsByScope")
    @ResponseBody
    public List<Setting> getSettingsByScope(@RequestParam SettingScope scope) {
        return settingService.getSettingsByScope(scope);
    }

    @GetMapping("getGoodsPageSettings")
    @ResponseBody
    public GoodsPageSettingDto getGoodsPageSetting() {
        GoodsPageSettingDto dto = new GoodsPageSettingDto();
        dto.setDisableService(false);

        // system
        String goods_page_notice = this.getSettingByName("goods_page_notice", SettingScope.SYSTEM).getValue();
        List<String> goods_page_tags = Arrays.asList(this.getSettingByName("goods_page_tags", SettingScope.SYSTEM).getValue().split(","));
        Boolean service_running = Boolean.valueOf(this.getSettingByName("service_running", SettingScope.SYSTEM).getValue());

        // store
        String store_open_date = this.getSettingByName("store_open_date", SettingScope.STORE).getValue();

        String store_open_time;
        String store_close_time;
        store_open_time = this.getSettingByName("store_open_time", SettingScope.STORE).getValue();
        store_close_time = this.getSettingByName("store_close_time", SettingScope.STORE).getValue();

        // 服务是否在正常运行!
        if (!service_running) {
            String service_close_notice = this.getSettingByName("service_close_notice", SettingScope.SYSTEM).getValue();
            dto.setDisableService(true);
            dto.setDisableServiceNotice(service_close_notice);
        }

        Date now = new Date();
        if (!store_open_date.contains(String.valueOf(DateUtil.dayOfWeek(now)))) {
            // 今天是否可以下单
            dto.setDisableService(true);
            dto.setDisableServiceNotice("今天是休息日,不营业!!");
        } else if (!TimeUtil.isBetween(store_open_time, store_close_time)) {
            // 现在是否可以下单
            dto.setDisableService(true);
            dto.setDisableServiceNotice("当前时间不在营业时间段!!!");
        }

        dto.setGoodsPageNotice(goods_page_notice);
        dto.setGoodsPageTags(goods_page_tags);

        return dto;
    }
}
