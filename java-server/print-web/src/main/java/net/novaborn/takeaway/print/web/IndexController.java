package net.novaborn.takeaway.print.web;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.github.anastaciocintra.escpos.EscPos;
import com.github.anastaciocintra.escpos.EscPosConst;
import com.github.anastaciocintra.escpos.Style;
import com.github.anastaciocintra.escpos.image.BitImageWrapper;
import com.github.anastaciocintra.escpos.image.Bitonal;
import com.github.anastaciocintra.escpos.image.BitonalThreshold;
import com.github.anastaciocintra.escpos.image.EscPosImage;
import com.github.anastaciocintra.output.PrinterOutputStream;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.common.exception.SysException;
import net.novaborn.takeaway.common.tips.SuccessTip;
import net.novaborn.takeaway.common.tips.Tip;
import net.novaborn.takeaway.order.entity.Order;
import net.novaborn.takeaway.order.entity.OrderItem;
import net.novaborn.takeaway.order.exception.OrderExceptionEnum;
import net.novaborn.takeaway.order.service.impl.OrderItemService;
import net.novaborn.takeaway.order.service.impl.OrderService;
import net.novaborn.takeaway.order.utils.OrderFormatUtil;
import net.novaborn.takeaway.user.entity.Address;
import net.novaborn.takeaway.user.service.impl.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.print.PrintService;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * @author xiaohun
 */
@Slf4j
@Setter(onMethod_ = {@Autowired})
@Controller
public class IndexController extends BaseController {
    private OrderService orderService;

    private OrderItemService orderItemService;

    private AddressService addressService;

    @Setter
    @Value("${sys.printer-name}")
    String printerName;

    @ResponseBody
    @RequestMapping("print")
    public Tip printOrder(@RequestParam String orderId) {
        Optional<Order> order = Optional.ofNullable(orderService.getById(orderId));
        order.orElseThrow(() -> new SysException(OrderExceptionEnum.ORDER_NOT_EXIST));
        this.print(order.get());
        return new SuccessTip();
    }

    @SneakyThrows
    private void print(Order order) {
        Address address = addressService.getById(order.getAddressId());
        List<OrderItem> orderItemList = orderItemService.selectByOrderId(order.getId());

        PrintService printService;
        if(StrUtil.isNotBlank(printerName)){
            printService = PrinterOutputStream.getPrintServiceByName(this.printerName);
        }else {
            printService = PrinterOutputStream.getDefaultPrintService();
        }
        EscPos escpos = new EscPos(new PrinterOutputStream(printService));
        escpos.setCharsetName("gbk");
        Style title = new Style()
                .setFontSize(Style.FontSize._5, Style.FontSize._5)
                .setJustification(EscPosConst.Justification.Center);

        Style subtitle = new Style(escpos.getStyle())
                .setFontSize(Style.FontSize._2, Style.FontSize._2)
                .setJustification(EscPosConst.Justification.Center);

        Style paymentState = new Style(escpos.getStyle())
                .setFontSize(Style.FontSize._2, Style.FontSize._2)
                .setBold(true)
                .setJustification(EscPosConst.Justification.Center);

        Style bold = new Style(escpos.getStyle())
                .setBold(true);

        Bitonal algorithm = new BitonalThreshold(127);
        BitImageWrapper imageWrapper = new BitImageWrapper();
        BufferedImage githubBufferedImage = textToImage(address.getAddress() + " " + address.getDetail());
        EscPosImage escposImage = new EscPosImage(githubBufferedImage, algorithm);

        escpos.writeLF(title, "#" + order.getNumber())
                .feed(1)
                .writeLF(subtitle, "川香苑品牌中餐厅")
                .writeLF(" ")
                .writeLF(
                        paymentState,
                        String.format("%s-%s",
                                OrderFormatUtil.formatPaymentWay(order.getPaymentWay()),
                                OrderFormatUtil.formatPayState(order.getPayState())
                        )
                )
                .writeLF("--------------------------------")
                .writeLF(bold, String.format("下单时间: %s", DateUtil.formatDateTime(order.getCreateDate())))
                .feed(2)
                .writeLF(bold, "--------------菜品--------------");
        for (OrderItem item : orderItemList) {
            escpos.writeLF(" ").writeLF(bold, String.format("%s   x%d", item.getGoodsName(), item.getGoodsCount()));
        }
        escpos.writeLF(bold, "--------------------------------")
                .writeLF(bold, String.format("合计: %d韩元", order.getRealPrice()))
                .writeLF(" ");
        if (StrUtil.isNotBlank(order.getPs())) {
            escpos.writeLF(bold, "--------------------------------")
                    .writeLF(bold, "备注:")
                    .writeLF(order.getPs())
                    .writeLF(" ");
        }
        escpos.write(imageWrapper, escposImage)
//                .writeLF(address.getDetail())
                .writeLF(bold, "联系方式: " + address.getPhone())
                .writeLF(" ")
                .writeLF(bold, "--------------------------------")
                .feed(3);

        Thread.sleep(1000);
        escpos.close();

    }

    private BufferedImage textToImage(String str) throws IOException {
        int width = 368;
        int height = 377;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Font font = new Font(null, Font.BOLD, 25);
        Graphics2D g = image.createGraphics();
        g.setColor(Color.white);
        g.fillRect(0, 0, width, height);
        g.setFont(font);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(Color.BLACK);
        int fontHeight = (int) font.getSize2D();

        // 得到当前的font metrics
        FontMetrics metrics = g.getFontMetrics();
        // 字符串长度（像素） str要打印的字符串
        int StrPixelWidth = metrics.stringWidth(str);
        // 算出行数
        int lineSize = (int) Math.ceil(StrPixelWidth * 1.0 / width);
        System.out.println(StrPixelWidth + "---:");
        // 页面宽度（width）小于 字符串长度
        if (width < StrPixelWidth) {
            // 存储每一行的字符串
            StringBuilder sb = new StringBuilder();
            int j = 0;
            int tempStart = 0;
            // 存储换行之后每一行的字符串
            String tempStrs[] = new String[lineSize];
            for (int i1 = 0; i1 < str.length(); i1++) {
                char ch = str.charAt(i1);
                sb.append(ch);
                Rectangle2D bounds2 = metrics.getStringBounds(sb.toString(), null);
                int tempStrPi1exlWi1dth = (int) bounds2.getWidth();
                if (tempStrPi1exlWi1dth > width) {
                    tempStrs[j++] = str.substring(tempStart, i1);
                    tempStart = i1;
                    sb.delete(0, sb.length());
                    sb.append(ch);
                }
                // 最后一行
                if (i1 == str.length() - 1) {
                    tempStrs[j] = str.substring(tempStart);
                }
            }
            for (int i = 0; i < tempStrs.length; i++) {
                g.drawString(tempStrs[i], 0, (fontHeight + 5) * (i + 1));
            }
            image = cropImage(image, -1, -1, -1, (fontHeight + 5) * tempStrs.length + 5);
        } else {
            g.drawString(str, 0, fontHeight);
            image = cropImage(image, -1, -1, -1, (fontHeight + 10));
        }
        g.dispose();
//        ImageIO.write(image, "png", new File("C:\\Users\\Administrator\\Desktop\\1.png"));
        return image;
    }

    private BufferedImage cropImage(BufferedImage bufferedImage, int startX, int startY, int endX, int endY) {
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        if (startX == -1) {
            startX = 0;
        }
        if (startY == -1) {
            startY = 0;
        }
        if (endX == -1) {
            endX = width - 1;
        }
        if (endY == -1) {
            endY = height - 1;
        }
        BufferedImage result = new BufferedImage(endX - startX, endY - startY, 4);
        for (int x = startX; x < endX; ++x) {
            for (int y = startY; y < endY; ++y) {
                int rgb = bufferedImage.getRGB(x, y);
                result.setRGB(x - startX, y - startY, rgb);
            }
        }
        return result;
    }
}
