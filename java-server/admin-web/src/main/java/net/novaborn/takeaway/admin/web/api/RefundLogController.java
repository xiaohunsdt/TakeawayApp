package net.novaborn.takeaway.admin.web.api;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Setter;
import net.novaborn.takeaway.admin.web.wrapper.RefundLogWrapper;
import net.novaborn.takeaway.common.tips.SuccessTip;
import net.novaborn.takeaway.common.tips.Tip;
import net.novaborn.takeaway.order.entity.RefundLog;
import net.novaborn.takeaway.order.enums.PaymentWay;
import net.novaborn.takeaway.order.enums.RefundState;
import net.novaborn.takeaway.order.service.impl.RefundLogService;
import net.novaborn.takeaway.user.entity.User;
import net.novaborn.takeaway.user.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author xiaohun
 */
@Controller
@Setter(onMethod_ = {@Autowired})
@RequestMapping("/api/admin/refund/log")
public class RefundLogController extends BaseController {
    private UserService userService;

    private RefundLogService refundLogService;

    @PostMapping("getListByPage")
    public ResponseEntity getListByPage(@ModelAttribute Page page, @RequestParam Map<String, Object> args) {
        // 根据昵称获取订单
        if (StrUtil.isNotBlank((String) args.get("nickName"))) {
            List<Long> ids = userService.getByNickName((String) args.get("nickName")).stream()
                .map(User::getId)
                .collect(Collectors.toList());
            if (ids.size() > 0) {
                args.put("userIds", ids);
            } else {
                args.put("userIds", Arrays.asList("-1"));
            }
        }

        if (StrUtil.isNotBlank((String) args.get("paymentWay"))) {
            args.put("paymentWay", PaymentWay.valueOf((String) args.get("paymentWay")));
        } else {
            args.remove("paymentWay");
        }

        if (StrUtil.isNotBlank((String) args.get("state"))) {
            args.put("state", RefundState.valueOf((String) args.get("state")));
        } else {
            args.remove("state");
        }

        page = (Page) refundLogService.getListByPage(page, args);
        page.setRecords((List) new RefundLogWrapper(page.getRecords()).warp());
        return ResponseEntity.ok(page);
    }

    @ResponseBody
    @PostMapping("getAllRefundMoneyByOrderId")
    public Tip getAllRefundMoneyByOrderId(Long orderId) {
        return new SuccessTip(refundLogService.getAllRefundMoneyByOrderId(orderId));
    }

    @ResponseBody
    @PostMapping("getLogCountByOrderId")
    public Tip getLogCountByOrderId(Long orderId) {
        return new SuccessTip(refundLogService.getRefundLogCountByOrderId(orderId));
    }

    @ResponseBody
    @PostMapping("doneRefund")
    public Tip doneRefund(Long refundId) {
        RefundLog refundLog = refundLogService.getById(refundId);
        refundLog.setState(RefundState.DONE);
        refundLogService.updateById(refundLog);
        return new SuccessTip();
    }

    @ResponseBody
    @PostMapping("rejectRefund")
    public Tip rejectRefund(Long refundId, String rejectMsg) {
        RefundLog refundLog = refundLogService.getById(refundId);
        refundLog.setState(RefundState.FAILED);
        refundLog.setRejectMsg(rejectMsg);
        refundLogService.updateById(refundLog);
        return new SuccessTip();
    }
}
