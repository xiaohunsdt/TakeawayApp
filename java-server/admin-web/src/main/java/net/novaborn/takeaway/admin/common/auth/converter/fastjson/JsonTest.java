package net.novaborn.takeaway.admin.common.auth.converter.fastjson;

import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson.JSON;
import net.novaborn.takeaway.admin.common.auth.converter.BaseTransferEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * json测试
 *
 * @author xiaohun
 * @date 2017-08-25 16:11
 */


public class JsonTest {

    public static void main(String[] args) {
        String randomKey = "1xm7hw";

        BaseTransferEntity baseTransferEntity = new BaseTransferEntity();
        Map<String, Object> simpleObject = new HashMap<>();
        simpleObject.put("user", "aaaaa");
        String json = JSON.toJSONString(simpleObject);
        baseTransferEntity.setObject(json);

        //md5签名
        String encrypt = SecureUtil.md5(json + randomKey);
        baseTransferEntity.setSign(encrypt);

        System.out.println(JSON.toJSONString(baseTransferEntity));
    }
}
