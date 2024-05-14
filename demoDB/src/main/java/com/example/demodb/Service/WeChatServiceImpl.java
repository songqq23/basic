package com.example.demodb.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demodb.DTO.User;
import com.example.demodb.DTO.WeChat;
import com.example.demodb.DTO.WeChatUserDTO;
import com.example.demodb.Mapper.UserMapper;
import com.sky.utils.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class WeChatServiceImpl implements WeChatService{
    public static final String wxapi = "https://api.weixin.qq.com/sns/jscode2session";

    @Autowired
    private WeChat weChat;

    @Autowired
    private UserMapper userMapper;
    public User wechatlogin(WeChatUserDTO weChatUserDTO){
        //调用微信接口服务，获取微信用户的openid
        Map<String, String> map=new HashMap<>();
        map.put("appid",weChat.getAppid());
        map.put("secret",weChat.getSecret());
        map.put("js_code",weChatUserDTO.getCode());
        map.put("grant_type","authorization_code");

        String json = HttpClientUtil.doGet(wxapi,map);
        JSONObject jsonObject = JSON.parseObject(json);
        String openid = jsonObject.getString("openid");

        //判断openid是否为空
        if(openid==null){
            return null;
        }

        //是否是新用户
        User user = userMapper.getByName(openid);

        //是新用户就自动存在数据库里
        if(user==null){
            user = User.builder()
                    .id("3")
                    .loginId(openid.substring(0,5))
                    .name(openid)
                    .password(openid.substring(0,5))
                    .type(1)
                    .createTime(LocalDateTime.now())
                    .updateTime(LocalDateTime.now())
                    .build();
            userMapper.insert(user);
        }
        return user;

    }
}
