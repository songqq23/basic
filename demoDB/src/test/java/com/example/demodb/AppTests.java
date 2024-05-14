package com.example.demodb;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

//@SpringBootTest
public class AppTests {

    @Test
    public void testGenJwt(){
        Map<String, Object> claims = new HashMap<>();
        claims.put("id",1);
        claims.put("name","li");
        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256,"miyao")//密码算法和密钥
                .setClaims(claims)//自定义载荷
                .setExpiration(new Date(System.currentTimeMillis()+3600*1000))//一小时过期
                .compact();
        System.out.println(jwt);
    }

    @Test
    public void testParseJwt(){
        Claims claims = Jwts.parser()
                .setSigningKey("miyao")
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoibGkiLCJpZCI6MSwiZXhwIjoxNzE0NzE1MTIzfQ.diNfXbvwZNTd8K3HhApviO0sP45E_SExXxVpor1ccf4")
                .getBody();
        System.out.println(claims);
    }
}
