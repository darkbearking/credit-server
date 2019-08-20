package com.zjzy.credit.common.utils;

import java.security.Key;
import java.text.ParseException;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zjzy.credit.common.consts.SecurityConsts;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @description JWT操作工具类 
 * @author zhiwei.han
 * @date 2019-08-13 16:16:41
 */
public class JWTUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(JWTUtils.class);

    private JWTUtils() {
    }

    /**
     * 解析jwt
     * @param jsonWebToken jwt密文字符串
     * @param base64Secret  base63编码之后的secret
     * @return
     */
    public static Claims parseJWT(String jsonWebToken, String base64Secret) {
        try {
            Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(base64Secret))
                    .parseClaimsJws(jsonWebToken).getBody();
            return claims;
        } catch (Exception ex) {
            LOGGER.error("parse jwt exception", ex);
            return null;
        }
    }

    /**
     * 生成子系统的JWT
     * @param name
     * @param TTLMillis
     * @param base64Security
     * @return
     * @throws ParseException 
     */
    public static String createJWT4System(String name, String env, String base64Security) throws ParseException {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        // 生成签名密钥  
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(base64Security);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        // 添加构成JWT的参数  
        JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT").setHeaderParam("alg", "HS256")
                .claim(SecurityConsts.TOKEN_USER, name).claim(SecurityConsts.TOKEN_ENV, env).setIssuer("zjzy")
                .claim(SecurityConsts.TOKEN_SALT, RandomStringUtils.random(3, true, true))
                .signWith(signatureAlgorithm, signingKey);

        // 生成JWT  
        return builder.compact();
    }

}
