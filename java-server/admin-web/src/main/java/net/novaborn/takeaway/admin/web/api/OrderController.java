package net.novaborn.takeaway.admin.web.api;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.user.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author xiaohun
 */
@Slf4j
@Setter(onMethod_ = {@Autowired})
@Controller
@RequestMapping("/api/admin/order")
public class OrderController extends BaseController{

    UserService userService;
}
