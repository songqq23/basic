package com.example.demodb.controller;

import com.example.demodb.DTO.User;
import com.example.demodb.Result.Result;
import com.example.demodb.Service.UserService;
import com.example.demodb.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody User user){
        log.info("登录：{}",user);
        User u = userService.login(user);
        //登录成功，生成令牌下发
        if(u!=null){
            Map<String,Object> claims = new HashMap<>();
            claims.put("loginId",u.getLoginId());
            claims.put("name",u.getName());
            String jwt = JwtUtils.genJwt(claims);//包含当前员工的信息
            return Result.success(jwt);
        }
        //登录失败
        return Result.error("用户名或密码错误");

    }
}
