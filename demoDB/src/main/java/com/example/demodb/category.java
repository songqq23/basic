package com.example.demodb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class category {
    private Integer id;
    private Integer type;
    private String name;
    private Short sort;
    private Short status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Short createUser;
    private Short updateUser;


}
