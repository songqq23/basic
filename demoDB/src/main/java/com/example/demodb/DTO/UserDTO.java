package com.example.demodb.DTO;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * |主键	|类型|备注|
 * |--|--|--|
 * |id|string|索引（数据库自动生成）|
 * |loginId|string|账户id|
 * |name|string|用户名|
 * |password|string|密码|
 * |type|int|用户类型  |
 * |create_time|datetime|创建时间|
 * |update_time|datetime|更新时间|
 */
@Data
public class UserDTO implements Serializable {

    private String loginId;
    private String name;
    private String password;
    private String password1;
    private Integer type;

}
