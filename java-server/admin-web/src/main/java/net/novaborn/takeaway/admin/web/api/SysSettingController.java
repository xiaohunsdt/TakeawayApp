package net.novaborn.takeaway.admin.web.api;

import lombok.Setter;
import net.novaborn.takeaway.common.tips.SuccessTip;
import net.novaborn.takeaway.common.tips.Tip;
import net.novaborn.takeaway.system.entity.Setting;
import net.novaborn.takeaway.system.service.impl.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author xiaohun
 */
@Controller
@Setter(onMethod_ = {@Autowired})
@RequestMapping("/api/admin/setting")
public class SysSettingController extends BaseController {
    private SettingService settingService;

    @GetMapping("getAllSetting")
    public List<Setting> getAllSetting() {
        return settingService.list();
    }

    @PostMapping("getSettingByName")
    public Setting getSettingByName(@RequestParam String name) {
        return settingService.getById(name);
    }

    @PostMapping("updateSetting")
    public Tip updateSetting(@RequestBody @Valid Setting setting) {
        setting.insertOrUpdate();
        return new SuccessTip();
    }
}
