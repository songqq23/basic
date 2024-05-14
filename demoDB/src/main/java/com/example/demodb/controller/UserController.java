package com.example.demodb.controller;

import com.example.demodb.DTO.DelUserDTO;
import com.example.demodb.DTO.PageQueryDTO;
import com.example.demodb.DTO.UserDTO;
import com.example.demodb.Result.PageResult;
import com.example.demodb.Result.Result;
import com.example.demodb.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/management/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    public Result save(@RequestBody UserDTO userDTO){
        log.info("新增员工：{}",userDTO);
        userService.save(userDTO);
        return Result.success();
    }

    @PostMapping("/getUserList")
    public Result<PageResult> page(@RequestBody PageQueryDTO pageQueryDTO){
        log.info("员工分页查询，参数为：{}",pageQueryDTO);
        PageResult pageResult = userService.pagequery(pageQueryDTO);
        return Result.success(pageResult);
    }

    @GetMapping("/delUser")
    public Result delete(@RequestBody DelUserDTO delUserDTO){
        log.info("删除用户：{}",delUserDTO);
        userService.delete(delUserDTO);
        return Result.success();
    }

    @GetMapping("/status")
    public Result<Integer> getStatus(){
        Integer status = 1;
        return Result.success(status);
    }


}
