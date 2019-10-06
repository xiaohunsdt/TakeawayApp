package net.novaborn.takeaway.admin.web.api;

import net.novaborn.takeaway.user.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author xiaohun
 */
@Controller
@RequestMapping("/api/admin/address")
public class AddressController extends BaseController{

    @Autowired
    UserService userService;
}
