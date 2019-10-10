package net.novaborn.takeaway.admin.web.api;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Setter;
import net.novaborn.takeaway.admin.web.wrapper.AddressWrapper;
import net.novaborn.takeaway.user.entity.User;
import net.novaborn.takeaway.user.service.impl.AddressService;
import net.novaborn.takeaway.user.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author xiaohun
 */
@Controller
@Setter(onMethod_ = {@Autowired})
@RequestMapping("/api/admin/address")
public class AddressController extends BaseController {
    private UserService userService;

    private AddressService addressService;

    @PostMapping("getAddressListByPage")
    public ResponseEntity<Page> getAddressListByPage(@ModelAttribute Page page, @RequestParam Map<String, Object> args) {
        if (StrUtil.isNotBlank((String) args.get("name"))) {
            Optional<User> user = userService.selectByName((String) args.get("name"));
            if (user.isPresent()) {
                args.put("userId", user.get().getId());
            } else {
                args.put("userId", "-1");
            }
        }
        page.setOptimizeCountSql(false);
        page = (Page) addressService.getAddressListByPage(page, args);
        page.setRecords((List) new AddressWrapper(page.getRecords()).warp());
        return ResponseEntity.ok(page);
    }
}