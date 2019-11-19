package net.novaborn.takeaway.impl;

import com.github.anastaciocintra.escpos.EscPos;
import com.github.anastaciocintra.output.PrinterOutputStream;
import org.junit.Test;

import javax.print.PrintService;
import java.io.IOException;

public class EscPosTest {

    @Test
    public void testPrint() throws IOException, InterruptedException {
        PrintService printService = PrinterOutputStream.getPrintServiceByName("xp-58");
        PrinterOutputStream printerOutputStream = new PrinterOutputStream(printService);
        EscPos escpos = new EscPos(printerOutputStream);
        escpos.setCharsetName("gbk");
        escpos.writeLF("你好,世界!");
        escpos.feed(5);
        escpos.cut(EscPos.CutMode.FULL);
        Thread.sleep(1000);
        escpos.close();
    }
}
