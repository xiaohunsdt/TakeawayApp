package net.novaborn.takeaway.admin.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.order.entity.Order;
import net.novaborn.takeaway.order.entity.OrderItem;
import net.novaborn.takeaway.order.service.impl.OrderItemService;
import net.novaborn.takeaway.order.utils.OrderFormatUtil;
import net.novaborn.takeaway.system.entity.Setting;
import net.novaborn.takeaway.system.enums.SettingScope;
import net.novaborn.takeaway.system.service.impl.SettingService;
import net.novaborn.takeaway.user.entity.Address;
import net.novaborn.takeaway.user.service.impl.AddressService;
import net.xpyun.platform.opensdk.service.PrintService;
import net.xpyun.platform.opensdk.util.HashSignUtil;
import net.xpyun.platform.opensdk.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class PrinterUtil {
    // 芯烨云注册用户帐号，注意不是开发者ID
    private final String USER_NAME = "825292796@qq.com";
    // 开发者密钥
    private final String USER_KEY = "bf5e349900b44e928fd5e63cceb4e136";
    // 打印机编号

    private PrintService service = new PrintService();

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private SettingService settingService;

    public void print(Order order) {
        List<OrderItem> orderItemList = orderItemService.selectByOrderId(order.getId());
        Address address = addressService.getById(order.getAddressId());
        Setting sn = settingService.getSettingByName("sn", SettingScope.PRINTER);
        if (sn == null || StrUtil.isBlank(sn.getValue())) {
            log.warn("没有设置打印机设备");
            return;
        }
        Setting temperature1 = settingService.getSettingByName("temperature1", SettingScope.PRINTER);

        StringBuffer sf = new StringBuffer();
        sf.append(String.format("<BOLD><B2><C>#%d\n", order.getNumber()));
        sf.append("<BR>");
        sf.append("<B><C>川香苑-新村店\n");
        sf.append("<BR>");
        if (order.getAppointmentDate() != null) {
            sf.append("<B><C>预约订单\n");
        }
        sf.append(String.format("<B><BOLD><C>%s-%s\n", OrderFormatUtil.formatPaymentWay(order.getPaymentWay()), OrderFormatUtil.formatPayState(order.getPayState())));
        sf.append("<N><BOLD>--------------------------------\n");
        sf.append(String.format("<N>下单时间: %s\n", DateUtil.formatDateTime(order.getCreateDate())));
        if (order.getAppointmentDate() != null) {
            sf.append(String.format("<N>送达时间: %s\n", DateUtil.formatDateTime(order.getAppointmentDate())));
        }
        sf.append("<BR>");
        sf.append("<BOLD>--------------菜品--------------\n");
        for (OrderItem item : orderItemList) {
            sf.append("<BR>");
            sf.append(String.format("<BOLD><L>%s  x%d\n", item.getGoodsName(), item.getGoodsCount()));
        }
        sf.append("<BR><BOLD>--------------------------------\n");
        sf.append(String.format("<R><BOLD>合计: %d 韩元\n", order.getRealPrice()));
        sf.append("<BR>");
        if (StrUtil.isNotBlank(order.getPs())) {
            sf.append("<BOLD>--------------------------------\n");
            sf.append("<B><L><BOLD>备注:\n");
            sf.append(String.format("<B><L><BOLD>%s\n", order.getPs()));
            sf.append("<BR>");
        }
        sf.append(String.format("<N><BOLD><L>联系方式: %s", address.getPhone()));

        if(temperature1!=null && StrUtil.isNotBlank(temperature1.getValue())){
            Setting temperature2 = settingService.getSettingByName("temperature2", SettingScope.PRINTER);
            Setting temperature3 = settingService.getSettingByName("temperature3", SettingScope.PRINTER);
            Setting temperature4 = settingService.getSettingByName("temperature4", SettingScope.PRINTER);

            sf.append("<BR><BR><B><C><BOLD>防疫安心卡\n");
            sf.append("<N><BOLD>--------------------------------<BR><BR>");
            sf.append(String.format("<N><BOLD><L>%s\t   \t%s℃\n", "厨师", temperature1.getValue()));

            if(temperature2!=null && StrUtil.isNotBlank(temperature2.getValue())){
                sf.append(String.format("<N><BOLD><L>%s\t   \t%s℃\n", "外卖员", temperature2.getValue()));
            }

            if(temperature3!=null && StrUtil.isNotBlank(temperature3.getValue())) {
                sf.append(String.format("<N><BOLD><L>%s\t   \t%s℃\n", "老板", temperature3.getValue()));
            }

            if(temperature4!=null && StrUtil.isNotBlank(temperature4.getValue())) {
                sf.append(String.format("<N><BOLD><L>%s\t   \t%s℃\n", "接单员", temperature4.getValue()));
            }

            sf.append("<BR><N><BOLD>川香苑提示您!疫情期间请尽量待在家中,出门或与外卖员接触请佩戴口罩!川香苑与您一起共度难关!");
        }

        PrintRequest request = new PrintRequest();
        createRequestHeader(request);
        request.setSn(sn.getValue());
        request.setContent(sf.toString());
        request.setCopies(1);
//        request.setPayType(41);
//        request.setPayMode(60);
//        request.setMoney(20.15);

        ObjectRestResponse<String> resp = service.print(request);
        log.debug(resp.toString());
    }

    public void setVoiceType(String sn, int type) {
        SetVoiceTypeRequest request = new SetVoiceTypeRequest();
        createRequestHeader(request);
        // 声音类型： 0真人语音（大） 1真人语音（中） 2真人语音（小） 3 嘀嘀声  4 静音
        request.setSn(sn);
        request.setVoiceType(type);
        ObjectRestResponse<Boolean> resp = service.setPrinterVoiceType(request);
        log.debug(resp.toString());
    }

    public void addPrinter(String name, String sn) {
        AddPrinterRequest request = new AddPrinterRequest();
        createRequestHeader(request);
        List<AddPrinterRequestItem> itemList = new ArrayList<>();

        AddPrinterRequestItem item = new AddPrinterRequestItem();
        // 真实打印机编号
        item.setSn(sn);
        item.setName(name);
        itemList.add(item);

        AddPrinterRequestItem[] items = new AddPrinterRequestItem[itemList.size()];
        itemList.toArray(items);
        request.setItems(items);
        ObjectRestResponse<PrinterResult> resp = service.addPrinters(request);
        log.debug(resp.toString());
    }

    private void createRequestHeader(RestRequest request) {
        request.setUser(USER_NAME);
        request.setTimestamp(String.valueOf(System.currentTimeMillis()));
        request.setSign(HashSignUtil.sign(request.getUser() + USER_KEY + request.getTimestamp()));
        request.setDebug("0");
    }
}
