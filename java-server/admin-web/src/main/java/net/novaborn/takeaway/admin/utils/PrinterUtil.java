package net.novaborn.takeaway.admin.utils;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.img.ImgUtil;
import lombok.SneakyThrows;
import net.novaborn.takeaway.order.entity.Order;
import net.novaborn.takeaway.order.entity.OrderItem;
import net.novaborn.takeaway.order.service.impl.OrderItemService;
import net.novaborn.takeaway.order.utils.OrderFormatUtil;
import net.novaborn.takeaway.user.entity.Address;
import net.novaborn.takeaway.user.service.impl.AddressService;
import net.xpyun.platform.opensdk.service.PrintService;
import net.xpyun.platform.opensdk.util.HashSignUtil;
import net.xpyun.platform.opensdk.vo.ObjectRestResponse;
import net.xpyun.platform.opensdk.vo.PrintRequest;
import net.xpyun.platform.opensdk.vo.RestRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Component
public class PrinterUtil {
    // 芯烨云注册用户帐号，注意不是开发者ID
    private final String USER_NAME = "825292796@qq.com";
    // 开发者密钥
    private final String USER_KEY = "c8ae174868634260982bff1e56e66ebb";
    // 打印机编号
    private final String OK_PRINTER_SN = "05SBD7NDCE6834B";

    private PrintService service = new PrintService();

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private AddressService addressService;

    void print(Order order) {
        List<OrderItem> orderItemList = orderItemService.selectByOrderId(order.getId());
        Address address = addressService.getById(order.getAddressId());

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
        sf.append(String.format("<L><LOGO>%s</LOGO>", textToImage(address.getAddress() + " " + address.getDetail())));
        sf.append(String.format("<BOLD><L>联系方式: %s", address.getPhone()));
        sf.append("<BR>");

        PrintRequest request = new PrintRequest();
        createRequestHeader(request);
        request.setSn(OK_PRINTER_SN);
        request.setContent(sf.toString());
        request.setCopies(1);
//        request.setPayType(41);
//        request.setPayMode(60);
//        request.setMoney(20.15);

        ObjectRestResponse<String> resp = service.print(request);
        System.out.println(resp);
    }

    private void createRequestHeader(RestRequest request) {
        request.setUser(USER_NAME);
        request.setTimestamp(System.currentTimeMillis() + "");
        request.setSign(HashSignUtil.sign(request.getUser() + USER_KEY + request.getTimestamp()));
        request.setDebug("0");
    }

    /**
     * base64 字符串
     * @param str
     * @return
     * @throws IOException
     */
    @SneakyThrows
    private String textToImage(String str){
        int width = 184;
        int height = 377;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Font font = new Font(null, Font.BOLD, 14);
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

        ImageIO.write(image, "png", new File("C:\\Users\\Xiaohun's PC\\Desktop\\1.png"));
        return ImgUtil.toBase64(image,"png");
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

    public static byte[] imageToBytes(BufferedImage bImage, String format) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            ImageIO.write(bImage, format, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toByteArray();
    }
}
