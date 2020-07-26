package net.novaborn.takeaway.user.web.wrapper;

import cn.hutool.core.date.DateUtil;
import net.novaborn.takeaway.activity.signin.service.impl.SignInService;
import net.novaborn.takeaway.common.BaseControllerWrapper;
import net.novaborn.takeaway.common.SpringContextHolder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 测试用
 *
 * @author xiaohun
 */
public class SignInWrapper extends BaseControllerWrapper {

    public SignInWrapper(Object element) {
        super(element);
    }

    @Override
    protected void warpTheMap(Map<String, Object> map) {
        SignInService signInService = SpringContextHolder.getBean(SignInService.class);
        int[] signIned = signInService.getAllSignInedDays((int) map.get("record"));

        List<Long> signInedDay = new ArrayList();
        Date beginOfMonth = DateUtil.beginOfMonth((Date) map.get("createDate"));
        Date endOfMonth = DateUtil.endOfMonth((Date) map.get("createDate"));
        for (int i = 0; i < signIned.length; i++) {
            if (signIned[i] == 1) {
                signInedDay.add(DateUtil.offsetDay(beginOfMonth, i).getTime());
            }
        }

        map.put("signInedDay", signInedDay);
        map.put("beginOfMonth", beginOfMonth.getTime());
        map.put("endOfMonth", endOfMonth.getTime());
    }
}
