package net.novaborn.takeaway.admin.common.auth.converter.jwt;

import cn.hutool.crypto.SecureUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;

/**
 * jwt测试
 *
 * @author xiaohun
 * @date 2017-08-21 16:34
 */
public class JWTTest {

    public static void main(String[] args) {
        Key key = new SecretKeySpec(SecureUtil.sha256("wy1996").getBytes(StandardCharsets.UTF_8), SignatureAlgorithm.HS256.getJcaName());

        String jws = Jwts.builder()
                .setSubject("Joe")
                .signWith(key)
                .compact();

        System.out.println(jws);

        assert Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jws).getBody().getSubject().equals("Joe");

        try {
            Claims body = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jws).getBody();
            System.out.println(body);
            System.out.println(body.getExpiration());

            System.out.println("trust");
        } catch (ExpiredJwtException e) {
            System.out.println("ExpiredJwtException");
        }
    }
}
