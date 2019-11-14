package net.novaborn.takeaway.admin.web.api;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.admin.web.wrapper.OrderDetailWrapper;
import net.novaborn.takeaway.admin.web.wrapper.OrderWrapper;
import net.novaborn.takeaway.category.entity.Category;
import net.novaborn.takeaway.common.exception.SysException;
import net.novaborn.takeaway.common.tips.ErrorTip;
import net.novaborn.takeaway.common.tips.SuccessTip;
import net.novaborn.takeaway.common.tips.Tip;
import net.novaborn.takeaway.order.entity.Order;
import net.novaborn.takeaway.order.exception.OrderExceptionEnum;
import net.novaborn.takeaway.order.service.impl.OrderService;
import net.novaborn.takeaway.user.entity.User;
import net.novaborn.takeaway.user.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author xiaohun
 */
@Slf4j
@Setter(onMethod_ = {@Autowired})
@Controller
@RequestMapping("/api/admin/order")
public class OrderController extends BaseController {

    private UserService userService;

    private OrderService orderService;

    @PostMapping("getOrderListByPage")
    public ResponseEntity getOrderListByPage(@ModelAttribute Page page, @RequestParam Map<String, Object> args) {
        // 根据用户名获取订单
        if (StrUtil.isNotBlank((String) args.get("nickName"))) {
            List<String> ids = userService.getByNickName((String) args.get("nickName")).stream()
                    .map(User::getId)
                    .collect(Collectors.toList());
            if (ids.size() > 0) {
                args.put("userIds", ids);
            } else {
                args.put("userIds", Arrays.asList("-1"));
            }
        }

        page.setOptimizeCountSql(false);
        page = (Page) orderService.getOrderListByPage(page, args);
        page.setRecords((List) new OrderWrapper(page.getRecords()).warp());
        return ResponseEntity.ok(page);
    }

    @PostMapping("getOrderDetail")
    public ResponseEntity getOrderDetail(@RequestParam String orderId) {
        Optional<Order> order = Optional.ofNullable(orderService.getById(orderId));
        order.orElseThrow(() -> new SysException(OrderExceptionEnum.ORDER_NOT_EXIST));
        return ResponseEntity.ok(new OrderDetailWrapper(order.get()).warp());
    }

    @ResponseBody
    @PostMapping("createNewOrder")
    public Tip createNewOrder(Category category) {
        return null;
    }

    @ResponseBody
    @PostMapping("updateOrder")
    public Tip updateOrder(Order order) {
//        Optional<Order> tempCategory = Optional.ofNullable(orderService.getById(order.getId()));
        return null;
    }

    @ResponseBody
    @PostMapping("deleteOrder")
    public Tip deleteOrder(String id) {
        if (orderService.removeById(id)) {
//            orderItemService.removeByOrderId(id);
            return new SuccessTip("删除成功!");
        } else {
            return new ErrorTip(-1, "删除失败!");
        }
    }
}
