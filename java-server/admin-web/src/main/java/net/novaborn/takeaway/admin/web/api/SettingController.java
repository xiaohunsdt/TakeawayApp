package net.novaborn.takeaway.admin.web.api;

import lombok.Setter;
import net.novaborn.takeaway.common.exception.SysException;
import net.novaborn.takeaway.common.tips.SuccessTip;
import net.novaborn.takeaway.common.tips.Tip;
import net.novaborn.takeaway.common.utils.NaverMapUtil;
import net.novaborn.takeaway.common.utils.entity.Coordinate;
import net.novaborn.takeaway.system.entity.Setting;
import net.novaborn.takeaway.system.enums.SettingScope;
import net.novaborn.takeaway.system.exception.SettingExceptionEnum;
import net.novaborn.takeaway.system.service.impl.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author xiaohun
 */
@Controller
@Setter(onMethod_ = {@Autowired})
@RequestMapping("/api/admin/setting")
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

            //如果是设置店的地址，那么店地址的坐标也要设置
            if ("store_address".equals(key)) {
                // 没有坐标，地址更新的情况，需要设置坐标
                Setting coordinate_x = settingService.getSettingByName("store_address_x", settingScope);
                Setting coordinate_y = settingService.getSettingByName("store_address_y", settingScope);

                if (coordinate_x  == null || coordinate_y  == null || !setting.getValue().equals(value)) {
                    Coordinate coordinate = NaverMapUtil.getGeocode((String) value);
                    coordinate_x = new Setting();
                    coordinate_x.setKey("store_address_x");
                    coordinate_x.setScope(settingScope);
                    coordinate_x.setValue(coordinate.getX());
                    coordinate_x.insertOrUpdate();

                    coordinate_y = new Setting();
                    coordinate_y.setKey("store_address_y");
                    coordinate_y.setScope(settingScope);
                    coordinate_y.setValue(coordinate.getY());
                    coordinate_y.insertOrUpdate();
                }
            }

            setting.setKey(key);
            setting.setScope(settingScope);
            setting.setValue(value);
            setting.insertOrUpdate();
        });


        return new SuccessTip();
    }
}
