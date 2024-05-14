package com.example.demodb.Service;

import com.example.demodb.DTO.*;
import com.example.demodb.Result.PageResult;

public interface UserService {

    void save(UserDTO userDTO);

    PageResult pagequery(PageQueryDTO pageQueryDTO);

    void delete(DelUserDTO delUserDTO);

    User login(User user);

}
