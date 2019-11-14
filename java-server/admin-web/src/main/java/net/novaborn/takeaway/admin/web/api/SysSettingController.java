package net.novaborn.takeaway.admin.web.api;

import lombok.Setter;
import net.novaborn.takeaway.common.exception.SysException;
import net.novaborn.takeaway.common.tips.SuccessTip;
import net.novaborn.takeaway.common.tips.Tip;
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
public class SysSettingController extends BaseController {
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
            setting.setKey(key);
            setting.setScope(settingScope);
            setting.setValue(value);
            setting.insertOrUpdate();
        });

        return new SuccessTip();
    }
}
