package net.novaborn.takeaway.activity.signin.service;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import net.novaborn.takeaway.activity.signin.entity.SignIn;
import org.elasticsearch.common.rounding.DateTimeUnit;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

/**
 * 签到相关的服务类
 */
public interface ISignInService {

    /**
     * 获取指定用户的签到实例
     * @param userId 用户Id
     * @param date 指定时间(所在月)
     * @return 用户的签到实例
     */
    Optional<SignIn> getSignIn(String userId, Date date);

    /**
     *
     * @param userId    用户Id
     * @param date      指定时间(所在月)
     * @param signIn    签到实例
     */
    void saveSignIn(String userId, Date date, SignIn signIn);

    /**
     * 检查是否签到
     * @param userId        用户Id
     * @param date          指定时间(所在月)
     * @param dateUnit      要检查的日期类型
     *                      DAY_OF_YEAR(某一天)
     *                      WEEK_OF_MONTH（某月的第几周）
     *                      MONTH（某月）
     * @return
     */
    boolean checkSignIn(String userId, Date date, Calendar dateUnit);

    /**
     * 检查是否签到
     * @param signIn        指定的签到实例
     * @param date          指定时间(所在月)
     * @param dateUnit      要检查的日期类型
     *                      DAY_OF_YEAR(某一天)
     *                      WEEK_OF_MONTH（某月的第几周）
     *                      MONTH（某月）
     * @return
     */
    boolean checkSignIn(SignIn signIn, Date date, Calendar dateUnit);

    default String getKeyStr(String userId, Date date) {
        return String.format("signin:%d%d:%s", DateUtil.year(date), DateUtil.month(date), userId);
    }
}
