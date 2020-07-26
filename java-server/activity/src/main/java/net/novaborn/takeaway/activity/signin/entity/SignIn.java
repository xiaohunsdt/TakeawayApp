package net.novaborn.takeaway.activity.signin.entity;

import lombok.Data;

@Data
public class SignIn {
    String userId;

    /**
     * 记录一个月里的签到次数
     * Int类型一共有32位,签到了就用1表示，未签到就用0表示，而且每个月份最多31天，所以一个int可以完整保存一个月的签到记录
     */
    Integer record;

    /**
     * 本月是否已兑换
     */
    Boolean monthIsExchanged;

    /**
     * 第1周是否已兑换
     */
    Boolean week1IsExchanged;

    /**
     * 第2周是否已兑换
     */
    Boolean week2IsExchanged;

    /**
     * 第3周是否已兑换
     */
    Boolean week3IsExchanged;

    /**
     * 第4周是否已兑换
     */
    Boolean week4IsExchanged;
}
