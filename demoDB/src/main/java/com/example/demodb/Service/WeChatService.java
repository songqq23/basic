package com.example.demodb.Service;

import com.example.demodb.DTO.User;
import com.example.demodb.DTO.WeChatUserDTO;

public interface WeChatService {
    User wechatlogin(WeChatUserDTO weChatUserDTO);
}
