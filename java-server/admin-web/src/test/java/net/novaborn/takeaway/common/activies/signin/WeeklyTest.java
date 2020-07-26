package net.novaborn.takeaway.common.activies.signin;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import net.novaborn.takeaway.activity.signin.entity.SignIn;
import net.novaborn.takeaway.activity.signin.service.impl.SignInService;
import net.novaborn.takeaway.admin.AdminApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AdminApplication.class})
public class WeeklyTest {

    @Autowired
    private SignInService signInService;

    @Test
    public void getTest() {
        System.out.println(signInService.getSignIn("bbb", new Date()));
    }

    @Test
    public void signInTest() {
        signInService.signIn("bbb", DateUtil.parseDate("2020-07-21"));
    }

    @Test
    public void checkTest() {
        System.out.println(signInService.checkSignIn("bbb", DateUtil.parseDate("2020-07-24"), Calendar.DAY_OF_MONTH));
    }

    @Test
    public void signInedCountTest() {
        System.out.println(signInService.getSignInedCount("bbb", DateUtil.parseDate("2020-07-27"), Calendar.WEEK_OF_MONTH));
    }

    @Test
    public void dateTest() {
        Date date = DateUtil.parseDate("2020-07-02");
        int day0fWeek = DateUtil.dayOfWeek(date) - 1 == 0 ? 7 : DateUtil.dayOfWeek(date) - 1;
        int day0fMonth = DateUtil.dayOfMonth(date);
        int dayOfWeekStart = day0fMonth - day0fWeek + 1;
        int dayOfWeekEnd = day0fMonth - day0fWeek + 7;
        int dayCountOfMonth = DateUtil.endOfMonth(date).getField(DateField.DAY_OF_MONTH);
        for (int i = dayOfWeekStart; i <= dayOfWeekEnd; i++) {
            if (i <= 0) {
                System.out.println(DateUtil.beginOfMonth(date).offset(DateField.DAY_OF_YEAR, i - 1));
            } else if (i > dayCountOfMonth) {
                System.out.println(DateUtil.endOfMonth(date).offset(DateField.DAY_OF_YEAR, i - dayCountOfMonth));
            } else {
                System.out.println(i);
            }
        }
    }

    @Test
    public void signTest() {
        Date date = DateUtil.parseDate("2020-07-07");

        SignIn signIn = new SignIn();
        signIn.setRecord(0b1111111111111111111111111111111);

        int record = 1;
        record |= 1 << (DateUtil.dayOfMonth(date) - 1);
        System.out.println(record);
        System.out.println(signInService.checkSignIn(signIn, date, Calendar.MONTH));
    }

}
