package com.example.demodb.DTO;

import lombok.Data;

import java.io.Serializable;

@Data
public class DelUserDTO implements Serializable {
    private String loginId;
}
