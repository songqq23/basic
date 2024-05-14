package com.example.demodb.controller;

import com.example.demodb.DTO.User;
import com.example.demodb.DTO.WeChatUserDTO;
import com.example.demodb.DTO.WeChatUserVO;
import com.example.demodb.Result.Result;
import com.example.demodb.Service.UserService;
import com.example.demodb.Service.WeChatService;
import com.example.demodb.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user/user")
@Slf4j
public class WeChatUserController {

    @Autowired
    private WeChatService weChatService;


    @PostMapping("/login")
    public Result<WeChatUserVO> login(@RequestBody WeChatUserDTO weChatUserDTO){
        log.info("微信用户登录：{}",weChatUserDTO.getCode());
        User user = weChatService.wechatlogin(weChatUserDTO);

        Map<String,Object> claims = new HashMap<>();
        claims.put("loginId",user.getLoginId());
        claims.put("name",user.getName());
        String jwt = JwtUtils.genJwt(claims);//包含当前员工的信息

        WeChatUserVO weChatUserVO = new WeChatUserVO(user.getId(),user.getName(),jwt);
        return Result.success(weChatUserVO);

    }
}
