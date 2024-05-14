package com.example.demodb.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtils {
    private static String signKey = "miyao";
    private static Long expire = 43200000L;

    /**
     * 生成JWT
     * @param claims
     * @return
     */
    public static String genJwt(Map<String,Object> claims){
        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256,signKey)//密码算法和密钥
                .addClaims(claims)//自定义载荷
                .setExpiration(new Date(System.currentTimeMillis()+expire))//一小时过期
                .compact();
        return jwt;
    }

    /**
     * 解析JWT
     * @param jwt
     * @return 第二部分负载中存储的内容
     */
    public static Claims parseJwt(String jwt){
        Claims claims = Jwts.parser()
                .setSigningKey(signKey)
                .parseClaimsJws(jwt)
                .getBody();
        return claims;
    }
}
