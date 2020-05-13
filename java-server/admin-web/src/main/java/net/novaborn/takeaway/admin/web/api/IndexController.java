package net.novaborn.takeaway.admin.web.api;

import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.util.IdUtil;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.admin.common.auth.util.JwtTokenUtil;
import net.novaborn.takeaway.admin.entity.Admin;
import net.novaborn.takeaway.admin.service.impl.AdminService;
import net.novaborn.takeaway.admin.web.dto.DashboardDto;
import net.novaborn.takeaway.common.exception.SysException;
import net.novaborn.takeaway.common.exception.SysExceptionEnum;
import net.novaborn.takeaway.common.tips.SuccessTip;
import net.novaborn.takeaway.common.tips.Tip;
import net.novaborn.takeaway.order.entity.Order;
import net.novaborn.takeaway.order.enums.OrderState;
import net.novaborn.takeaway.order.enums.PayState;
import net.novaborn.takeaway.order.enums.PaymentWay;
import net.novaborn.takeaway.order.service.impl.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author xiaohun
 */
@Slf4j
@Setter(onMethod_ = {@Autowired})
@Controller
public class IndexController extends BaseController {

    private AdminService adminService;

    private OrderService orderService;

    private JwtTokenUtil jwtTokenUtil;

    @GetMapping("index")
    @ResponseBody
    public SuccessTip index() {
        return new SuccessTip();
    }

    @GetMapping("getDashboardData")
    @ResponseBody
    public DashboardDto getDashboardData() {
        List<Order> orderList = orderService.getTodayOrderByStateU(null,null).stream()
                .filter(order -> order.getOrderState() != OrderState.REFUND && order.getPayState() != PayState.UN_PAY)
                .collect(Collectors.toList());

        List<Order> wechatOrderList = orderList.stream().filter(order -> order.getPaymentWay()== PaymentWay.WEIXIN_PAY).collect(Collectors.toList());
        List<Order> alipayOrderList = orderList.stream().filter(order -> order.getPaymentWay()== PaymentWay.ALI_PAY).collect(Collectors.toList());
        List<Order> transferOrderList = orderList.stream().filter(order -> order.getPaymentWay()== PaymentWay.TRANSFER).collect(Collectors.toList());
        List<Order> creditOrderList = orderList.stream().filter(order -> order.getPaymentWay()== PaymentWay.CREDIT_CARD).collect(Collectors.toList());
        List<Order> cashOrderList = orderList.stream().filter(order -> order.getPaymentWay()== PaymentWay.CASH).collect(Collectors.toList());
        List<Order> waitDeliveryOrderList = orderList.stream().filter(order -> order.getOrderState()==OrderState.WAITING_RECEIVE || order.getOrderState()==OrderState.PRODUCING).collect(Collectors.toList());
        List<Order> deliveringOrderList = orderList.stream().filter(order -> order.getOrderState()==OrderState.DELIVERING).collect(Collectors.toList());
        List<Order> finishOrderList = orderList.stream().filter(order -> order.getOrderState()==OrderState.FINISHED).collect(Collectors.toList());
        List<Order> refundOrderList = orderList.stream().filter(order -> order.getOrderState()==OrderState.REFUND).collect(Collectors.toList());


        DashboardDto dashboardDto = new DashboardDto();
        dashboardDto.setWechatOrderAllCount(wechatOrderList.size());
        dashboardDto.setWechatOrderAllPrice(wechatOrderList.stream().mapToInt(Order::getRealPrice).sum());

        dashboardDto.setAlipayOrderAllCount(alipayOrderList.size());
        dashboardDto.setAlipayOrderAllPrice(alipayOrderList.stream().mapToInt(Order::getRealPrice).sum());

        dashboardDto.setTransferOrderAllCount(transferOrderList.size());
        dashboardDto.setTransferOrderAllPrice(transferOrderList.stream().mapToInt(Order::getRealPrice).sum());

        dashboardDto.setCreditOrderAllCount(creditOrderList.size());
        dashboardDto.setCashOrderAllPrice(creditOrderList.stream().mapToInt(Order::getRealPrice).sum());

        dashboardDto.setCashOrderAllCount(cashOrderList.size());
        dashboardDto.setCashOrderAllPrice(cashOrderList.stream().mapToInt(Order::getRealPrice).sum());

        dashboardDto.setAllCount(orderList.size());
        dashboardDto.setAllPrice(orderList.stream().mapToInt(Order::getRealPrice).sum());


        dashboardDto.setWaitDeliveryCount(waitDeliveryOrderList.size());
        dashboardDto.setDeliveringCount(deliveringOrderList.size());
        dashboardDto.setFinishCount(finishOrderList.size());
        dashboardDto.setRefundCount(refundOrderList.size());

        return dashboardDto;
    }

    @GetMapping("getUserInfo")
    @ResponseBody
    public Admin getUserInfo(HttpServletRequest request) {
        String userName = jwtTokenUtil.getUsernameFromToken(request);
        Optional<Admin> admin = adminService.getBaseMapper().selectByName(userName);
        admin.orElseThrow(() -> new SysException(SysExceptionEnum.AUTH_HAVE_NO_USER));

        return admin.get();
    }

    @RequestMapping("/uploadImg")
    @ResponseBody
    public Tip uploadImg(@RequestParam MultipartFile file) {
        String imgName;

        if (!file.isEmpty()) {
            try {
                String prefix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
                imgName = IdUtil.fastSimpleUUID() + prefix;
                // 文件保存路径
//                String filePath = new File(request.getSession().getServletContext().getRealPath("/")).getParent() + "/upload/leaguePics/";
                String filePath = new File(System.getProperty("user.dir")) + "/upload/images/";

                // 转存文件
                File dir = new File(filePath);
                if (!dir.exists() && !dir.isDirectory()) {
                    dir.mkdirs();
                }

                File target = new File(filePath + imgName);

                //保存图片到本地
                file.transferTo(target);
                //图片压缩
                ImgUtil.compress(target, target, 0.1f);
            } catch (Exception e) {
                log.error(null, e);
                throw new SysException(SysExceptionEnum.UPLOAD_IMAGE_FAILED);
            }
        } else {
            throw new SysException(SysExceptionEnum.UPLOAD_IMAGE_FAILED);
        }
        return new SuccessTip(imgName);
    }
}
