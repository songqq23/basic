package com.example.demodb.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeChatUserVO {
    private String id;
    private String openid;
    private String token;

}
