package net.novaborn.takeaway.admin.web.api;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Setter;
import net.novaborn.takeaway.admin.web.wrapper.AddressWrapper;
import net.novaborn.takeaway.system.entity.Setting;
import net.novaborn.takeaway.system.service.impl.SettingService;
import net.novaborn.takeaway.user.entity.User;
import net.novaborn.takeaway.user.service.impl.AddressService;
import net.novaborn.takeaway.user.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    public List<Setting> getSettingByName(@RequestParam String name) {
        return settingService.list();
    }

    @PostMapping("updateSetting")
    public List<Setting> updateSetting(@RequestBody String name) {
        return settingService.list();
    }
}
