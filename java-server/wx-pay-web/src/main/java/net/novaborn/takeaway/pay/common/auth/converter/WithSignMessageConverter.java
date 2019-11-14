package net.novaborn.takeaway.pay.common.auth.converter;

import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.common.exception.SysException;
import net.novaborn.takeaway.common.exception.SysExceptionEnum;
import net.novaborn.takeaway.pay.common.auth.security.DataSecurityAction;
import net.novaborn.takeaway.pay.common.auth.util.HttpKit;
import net.novaborn.takeaway.pay.common.auth.util.JwtTokenUtil;
import net.novaborn.takeaway.pay.config.properties.JwtProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageNotReadableException;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;

/**
 * 带签名的http信息转化器
 *
 * @author xiaohun
 * @date 2017-08-25 15:42
 */
@Slf4j
public class WithSignMessageConverter extends FastJsonHttpMessageConverter {

    @Autowired
    JwtProperties jwtProperties;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    DataSecurityAction dataSecurityAction;

    @Override
    public Object read(Type type, Class<?> contextClass, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {

        InputStream in = inputMessage.getBody();
        Object o = JSON.parseObject(in, super.getFastJsonConfig().getCharset(), BaseTransferEntity.class, super.getFastJsonConfig().getFeatures());

        //先转化成原始的对象
        BaseTransferEntity baseTransferEntity = (BaseTransferEntity) o;

        //校验签名
        String token = HttpKit.getRequest().getHeader(jwtProperties.getHeader()).substring(7);
        String md5KeyFromToken = jwtTokenUtil.getMd5KeyFromToken(token);

        String object = baseTransferEntity.getObject();
        String json = dataSecurityAction.unlock(object);
        String encrypt = SecureUtil.md5(object + md5KeyFromToken);

        if (encrypt.equals(baseTransferEntity.getSign())) {
            log.debug("签名校验成功!");
        } else {
            log.error("签名校验失败,数据被改动过!");
            throw new SysException(SysExceptionEnum.SIGN_ERROR);
        }

        //校验签名后再转化成应该的对象
        return JSON.parseObject(json, type);
    }
}
