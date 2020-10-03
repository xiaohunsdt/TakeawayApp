package net.novaborn.takeaway.admin.web.api;

import cn.hutool.core.date.DateUtil;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.admin.utils.PrinterUtil;
import net.novaborn.takeaway.common.exception.SysException;
import net.novaborn.takeaway.common.tips.SuccessTip;
import net.novaborn.takeaway.common.tips.Tip;
import net.novaborn.takeaway.store.entity.Store;
import net.novaborn.takeaway.store.service.impl.StoreService;
import net.novaborn.takeaway.system.entity.Setting;
import net.novaborn.takeaway.system.enums.SettingScope;
import net.novaborn.takeaway.system.exception.SettingExceptionEnum;
import net.novaborn.takeaway.system.service.impl.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

/**
 * @author xiaohun
 */
@Slf4j
@Controller
@Setter(onMethod_ = {@Autowired})
@RequestMapping("/api/admin/setting")
public class SettingController extends BaseController {
    private SettingService settingService;

    private StoreService storeService;

    private PrinterUtil printerUtil;

    @GetMapping("getAllSetting")
    @ResponseBody
    public List<Setting> getAllSetting() {
        return settingService.list();
    }

    @PostMapping("getSettingByKey")
    @ResponseBody
    public Setting getSettingByKey(@RequestParam String key, @RequestParam SettingScope scope) {
        return settingService.getSettingByName(key, scope);
    }

    @PostMapping("getSettingsByScope")
    @ResponseBody
    public List<Setting> getSettingsByScope(@RequestParam SettingScope scope) {
        return settingService.getSettingsByScope(scope);
    }

    @PostMapping("updateSetting")
    @ResponseBody
    public Tip updateSetting(@RequestParam Map<String, Object> args) {
        if (!args.containsKey("scope")) {
            throw new SysException(SettingExceptionEnum.SCOPE_NOT_EXIST);
        }

        SettingScope settingScope = SettingScope.valueOf((String) args.remove("scope"));

        args.forEach((key, value) -> {
            Setting setting = settingService.getSettingByName(key, settingScope);
            if (setting == null) {
                setting = new Setting();
            }

            value = ((String) value).trim();
            //如果是设置店的地址，那么店地址的坐标也要设置
//            if ("store_address".equals(key)) {
//                // 没有坐标/地址更新的情况，需要设置坐标
//                Setting coordinate_x = settingService.getSettingByName("store_address_x", settingScope);
//                Setting coordinate_y = settingService.getSettingByName("store_address_y", settingScope);
//
//                if (coordinate_x == null || coordinate_y == null || !setting.getValue().equals(value)) {
//                    Coordinate coordinate = naverMapService.getGeocode((String) value);
//                    coordinate_x = new Setting();
//                    coordinate_x.setKey("store_address_x");
//                    coordinate_x.setScope(settingScope);
//                    coordinate_x.setValue(coordinate.getX().toString());
//                    coordinate_x.insertOrUpdate();
//
//                    coordinate_y = new Setting();
//                    coordinate_y.setKey("store_address_y");
//                    coordinate_y.setScope(settingScope);
//                    coordinate_y.setValue(coordinate.getY().toString());
//                    coordinate_y.insertOrUpdate();
//                }
//            }

            if ("store_open_time".equals(key) || "store_close_time".equals(key)) {
                // 转义从前端传来的日期字符串
                SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault());
                sf.setTimeZone(TimeZone.getTimeZone("UTC"));
                try {
                    value = DateUtil.formatDateTime(sf.parse((String) value));
                } catch (ParseException e) {
                    log.error(null, e);
                    throw new SysException(SettingExceptionEnum.UPDATE_ERROR);
                }
            }

            if ("sn".equals(key)) {
                Setting sn = settingService.getSettingByName(key, settingScope);
                if (sn == null || !sn.getValue().equals(value)) {
                    printerUtil.addPrinter("test", (String) value);
                }
            }

            if ("voiceType".equals(key)) {
                Setting sn = settingService.getSettingByName("sn", SettingScope.PRINTER);
                Setting voiceType = settingService.getSettingByName(key, settingScope);
                if (voiceType == null || !voiceType.getValue().equals(value)) {
                    printerUtil.setVoiceType(sn.getValue(), Integer.valueOf((String) value));
                }
            }

            if("max_delivery_distance".equals(key)){
                Store store = storeService.getById(sysContext.getCurrentStoreId());
                store.setMaxDeliveryDistance(Integer.valueOf((String) value));
                store.updateById();
            }

            setting.setKey(key);
            setting.setScope(settingScope);
            setting.setValue(value.toString());
            setting.insertOrUpdate();
        });

        return new SuccessTip();
    }
}
