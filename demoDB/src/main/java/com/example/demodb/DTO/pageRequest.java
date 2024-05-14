package com.example.demodb.DTO;

import lombok.Data;

import java.io.Serializable;

@Data
public class pageRequest implements Serializable {
    //页码
    private int current;

    //每页显示记录数
    private int size;
}
