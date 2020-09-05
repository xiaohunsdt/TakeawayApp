package net.novaborn.takeaway.activity.signin.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class SignIn {

    Long userId;

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
     * 已经兑换周列表
     */
    List<Integer> weekExchangedList;

    Date createDate;

    public SignIn(Long userId) {
        this.userId = userId;
        this.setRecord(0);
        this.setCreateDate(new Date());
        this.setWeekExchangedList(new ArrayList<>());
    }
}
