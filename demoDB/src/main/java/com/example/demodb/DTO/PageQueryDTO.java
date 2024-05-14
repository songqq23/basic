package com.example.demodb.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class PageQueryDTO implements Serializable {
    //员工姓名
    private String name;

    @JsonProperty("pageRequest")
    private pageRequest p;
}

