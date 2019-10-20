package net.novaborn.takeaway.user.web.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.common.exception.SysException;
import net.novaborn.takeaway.common.exception.SysExceptionEnum;
import net.novaborn.takeaway.common.tips.ErrorTip;
import net.novaborn.takeaway.common.tips.SuccessTip;
import net.novaborn.takeaway.common.tips.Tip;
import net.novaborn.takeaway.order.entity.Order;
import net.novaborn.takeaway.order.entity.OrderItem;
import net.novaborn.takeaway.order.enums.OrderState;
import net.novaborn.takeaway.order.exception.OrderExceptionEnum;
import net.novaborn.takeaway.order.service.impl.OrderService;
import net.novaborn.takeaway.user.common.auth.util.JwtTokenUtil;
import net.novaborn.takeaway.user.entity.User;
import net.novaborn.takeaway.user.service.impl.UserService;
import net.novaborn.takeaway.user.web.dto.OrderDto;
import net.novaborn.takeaway.user.web.wrapper.OrderDetailWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author xiaohun
 */
@Slf4j
@Setter(onMethod_ = {@Autowired})
@Controller
@RequestMapping("/api/user/order")
public class OrderController extends BaseController {
    private UserService userService;

    private OrderService orderService;

    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("getOrderById")
    public ResponseEntity getOrderById(@RequestParam String orderId) {
        Optional<Order> order = Optional.ofNullable(orderService.getById(orderId));
        order.orElseThrow(() -> new SysException(OrderExceptionEnum.ORDER_NOT_EXIST));
        return ResponseEntity.ok(new OrderDetailWrapper(order.get()).warp());
    }

    @PostMapping("getOrderListByPage")
    public ResponseEntity getOrderListByPage(
            @ModelAttribute Page page,
            @RequestParam(required = false) OrderState state) {
        String openId = jwtTokenUtil.getUsernameFromToken(request);
        Optional<User> user = userService.selectByOpenId(openId);
        user.orElseThrow(() -> new SysException(SysExceptionEnum.AUTH_HAVE_NO_USER));

        Map<String, Object> args = new HashMap<>();
        args.put("userId", user.get().getId());
        if (state != null) {
            args.put("orderState", state);
        }

        page.setOptimizeCountSql(false);
        page = (Page) orderService.getOrderListByPage(page, args);
        page.setRecords((List) new OrderDetailWrapper(page.getRecords()).warp());
        return ResponseEntity.ok(page);
    }

    @ResponseBody
    @Transactional(rollbackFor = RuntimeException.class)
    @PostMapping("createOrder")
    public Tip createOrder(@RequestBody @Validated OrderDto orderDto) {
        Order order = orderDto.getOrder();
        List<OrderItem> orderItems = orderDto.getOrderItems();

        if (orderItems.size() == 0) {
            throw new SysException(OrderExceptionEnum.ORDER_NOT_EXIST);
        }

        String openId = jwtTokenUtil.getUsernameFromToken(request);
        Optional<User> user = userService.selectByOpenId(openId);
        user.orElseThrow(() -> new SysException(SysExceptionEnum.AUTH_HAVE_NO_USER));

        int number = orderService.getOrderCountToday() + 1;

        //填写订单信息
        order.setNumber(number);
        order.setUserId(user.get().getId());

        //先生成订单，在生成订单产品详情
        if (order.insert()) {
            orderItems.parallelStream().forEach(item -> {
                item.setOrderId(order.getId());
                item.insert();
            });
        }

        //将订单id返回
        return new SuccessTip(order.getId());
    }

    @ResponseBody
    @PostMapping("updateOrder")
    public Tip updateOrder(@ModelAttribute @Validated Order order) {
        order.updateById();
        return new SuccessTip("删除成功!");
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
